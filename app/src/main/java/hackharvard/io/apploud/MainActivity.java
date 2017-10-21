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

    int tapAmount = 0;
    int time = 1000;
    int frequency = 0;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mplayer = MediaPlayer.create(context, R.raw.clap1);

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
            if(mplayer.isPlaying()){
                mplayer.stop();
                mplayer.release();
                mplayer = MediaPlayer.create(context, R.raw.clap1);
            }
            mplayer.start();
        } catch (Exception e){
            Log.d("", "can't play audio.");
        }
    }
}
