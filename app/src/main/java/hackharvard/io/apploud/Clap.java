package hackharvard.io.apploud;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by theresiatjen on 10/21/17.
 */

public class Clap {
    Context context;

    MediaPlayer lowMediaPlayer[] = new MediaPlayer[10];
    MediaPlayer mediumMediaPlayer[] = new MediaPlayer[10];
    MediaPlayer longMediaPlayer[] = new MediaPlayer[10];

    int lowCountPlayer = 0;
    int mediumCountPlayer = 0;
    int longCountPlayer = 0;

    int tapAmount = 0;
    int time = 1000;
    int frequency = 0;
    Timer timer;

    public Clap(Context c){
        context = c;

        for (int i = 0; i < 10; i++){
            lowMediaPlayer[i] = MediaPlayer.create(context, R.raw.clap1);
        }
        for (int i = 0; i < 10; i++){
            mediumMediaPlayer[i] = MediaPlayer.create(context, R.raw.applause);
        }
        for (int i = 0; i < 10; i++){
            longMediaPlayer[i] = MediaPlayer.create(context, R.raw.applause);
        }
        //mplayer = MediaPlayer.create(context, R.raw.clap1);

        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                frequency = tapAmount;
                tapAmount = 0;
                Log.d("Frequency", ""+frequency);
            }
        };
        timer.schedule(timerTask, time, time);
    }

    public void doClap(){
        //Log.d("Log", "Apploud");
        Log.d("Log", ""+frequency);

        tapAmount++;

        try{
            if(frequency >= 0 && frequency <= 4){

                lowMediaPlayer[lowCountPlayer].stop();
                lowMediaPlayer[lowCountPlayer].release();
                lowMediaPlayer[lowCountPlayer] = MediaPlayer.create(context, R.raw.clap1);
                Log.d("Log", "here1");
                lowMediaPlayer[lowCountPlayer].start();
                lowCountPlayer = lowCountPlayer++ % 9;
            }
            else if(frequency >= 5 && frequency <= 6){

                mediumMediaPlayer[mediumCountPlayer].stop();
                mediumMediaPlayer[mediumCountPlayer].release();
                mediumMediaPlayer[mediumCountPlayer] = MediaPlayer.create(context, R.raw.applause);
                Log.d("Log", "here2");
                mediumMediaPlayer[mediumCountPlayer].start();
                mediumCountPlayer = mediumCountPlayer++ % 9;
            }
            else if(frequency >= 7){
                longMediaPlayer[longCountPlayer].stop();
                longMediaPlayer[longCountPlayer].release();
                longMediaPlayer[longCountPlayer] = MediaPlayer.create(context, R.raw.applause);
                Log.d("Log", "here3");
                longMediaPlayer[longCountPlayer].start();
                longCountPlayer = longCountPlayer++ % 9;
            }

        } catch (Exception e){
            Log.d("", "can't play audio.");
        }
    }
}
