package hackharvard.io.apploud;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Context context = this;
    MediaPlayer mplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mplayer = MediaPlayer.create(context, R.raw.clap1);
    }

    public void onClick(View v)
    {
        Log.d("Log", "Apploud");

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
