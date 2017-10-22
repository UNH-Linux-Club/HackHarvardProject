package hackharvard.io.apploud;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

import java.nio.charset.StandardCharsets;
import java.util.Timer;
import java.util.TimerTask;
import java.net.*;
import java.io.*;

/**
 * Created by theresiatjen on 10/21/17.
 */

public class Clap {
    Context context;

    MediaPlayer lowMediaPlayer[] = new MediaPlayer[10];
    MediaPlayer mediumMediaPlayer[] = new MediaPlayer[1];
    MediaPlayer longMediaPlayer[] = new MediaPlayer[1];

    int lowCountPlayer = 0;
    int mediumCountPlayer = 0;
    int longCountPlayer = 0;

    String name = "";

    int timers = 0;
    int totalTaps = 0;

    int tapAmount = 0;
    int time = 1000;
    int frequency = 0;
    Timer timer;

    public Clap(Context c){
        context = c;

        try {
            FileReader fileReader =
                    new FileReader("saveName");
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);
            name = bufferedReader.readLine();
        }
        catch (Exception e){
            Log.d("File Open", "Failure");
        }



        for (int i = 0; i < 10; i++){
            lowMediaPlayer[i] = MediaPlayer.create(context, R.raw.clap1);
        }
        for (int i = 0; i < 1; i++){
            mediumMediaPlayer[i] = MediaPlayer.create(context, R.raw.applause);
        }
        for (int i = 0; i < 1; i++){
            longMediaPlayer[i] = MediaPlayer.create(context, R.raw.applause);
        }
        //mplayer = MediaPlayer.create(context, R.raw.clap1);

        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                frequency = tapAmount;
                timers++;

                Log.d("time: ", Integer.toString(frequency));

                totalTaps += frequency;
                tapAmount = 0;

            }
        };
        TimerTask setScore = new TimerTask() {
            @Override
            public void run() {
                try{
                    Log.d("Score", "Writing score to server");

                    String urlParameters  = "name="+name+"&"+"score="+Integer.toString(totalTaps);
                    byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
                    int    postDataLength = postData.length;
                    String request        = "http://34.212.28.238:5000/scores";
                    URL    url            = new URL( request );
                    HttpURLConnection conn= (HttpURLConnection) url.openConnection();
                    conn.setDoOutput( true );
                    conn.setInstanceFollowRedirects( false );
                    conn.setRequestMethod( "POST" );
                    conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
                    conn.setRequestProperty( "charset", "utf-8");
                    conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
                    conn.setUseCaches( false );
                    try( DataOutputStream wr = new DataOutputStream( conn.getOutputStream())) {
                        wr.write( postData );
                    }
                }
                catch (Exception e){
                    Log.d("Error", e.toString());
                    //Log.d("Error: ", "name=FileReaderBroken"+name+"&"+"score="+Integer.toString(totalTaps));
                }
                totalTaps = 0;
            }

        };
        //timer.schedule(setScore,time*10,time*10);
        timer.schedule(timerTask, time*3, time*3);
    }

    public void doClap(){
        //Log.d("Log", "Apploud");
        //Log.d("Log", ""+frequency);
        //Log.d("timer: ", timer);
        tapAmount++;
        Log.d("Log", "Tap amount = " + tapAmount);

        try{
            if(frequency >= 0 && frequency <= 4){

                lowMediaPlayer[lowCountPlayer].stop();
                lowMediaPlayer[lowCountPlayer].release();
                lowMediaPlayer[lowCountPlayer] = MediaPlayer.create(context, R.raw.clap1);
                //Log.d("Log", "here1");
                lowMediaPlayer[lowCountPlayer].start();
                lowCountPlayer = lowCountPlayer++ % 9;
            }
            else if(frequency >= 5 && frequency <= 6){
                if (mediumMediaPlayer[mediumCountPlayer].isPlaying()) return; /* no-op */
                mediumMediaPlayer[mediumCountPlayer].stop();
                mediumMediaPlayer[mediumCountPlayer].release();
                mediumMediaPlayer[mediumCountPlayer] = MediaPlayer.create(context, R.raw.applause);
                //Log.d("Log", "here2");
                mediumMediaPlayer[mediumCountPlayer].start();
                mediumCountPlayer = mediumCountPlayer++ % 1;
            }
            else if(frequency >= 7){
                if (longMediaPlayer[longCountPlayer].isPlaying()) return; /* no-op */
                longMediaPlayer[longCountPlayer].stop();
                longMediaPlayer[longCountPlayer].release();
                longMediaPlayer[longCountPlayer] = MediaPlayer.create(context, R.raw.applause);
                //Log.d("Log", "here3");
                longMediaPlayer[longCountPlayer].start();
                longCountPlayer = longCountPlayer++ % 1;
            }

        } catch (Exception e){
            //Log.d("", "can't play audio.");
        }
    }


}
