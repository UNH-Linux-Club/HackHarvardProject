package hackharvard.io.apploud;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Context context = this;
    MediaPlayer mplayer;
    MediaPlayer mediaPlayer[] = new MediaPlayer[10];

    int countPlayer = 0;

    int tapAmount = 0;
    int time = 1000;
    int frequency = 0;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < 10; i++){
            mediaPlayer[i] = MediaPlayer.create(context, R.raw.clap1);
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

    public void onClick(View v)
    {
        Log.d("Log", "Apploud");

        tapAmount++;

        try{
            if(countPlayer > 9){
                countPlayer = 0;
            }
            if(mediaPlayer[countPlayer].isPlaying()){
                mediaPlayer[countPlayer].stop();
                mediaPlayer[countPlayer].release();
                if (frequency > 0 && frequency < 5) {
                    mediaPlayer[countPlayer] = MediaPlayer.create(context, R.raw.clap1);
                }
                else if (frequency >= 5 && frequency < 7){
                    mediaPlayer[countPlayer] = MediaPlayer.create(context, R.raw.clap1);
                }
                else {
                    mediaPlayer[countPlayer] = MediaPlayer.create(context, R.raw.clap1);
                }
            }
            mediaPlayer[countPlayer].start();
            countPlayer++;
        } catch (Exception e){
            Log.d("", "can't play audio.");
        }
    }
}
