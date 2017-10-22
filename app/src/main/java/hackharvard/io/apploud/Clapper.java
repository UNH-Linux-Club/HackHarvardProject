package hackharvard.io.apploud;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Clapper extends AppCompatActivity {
    Context c = this;
    Clap claps;

    //TextView textView;
    TickerView tickerView;
    ImageView imageView;

    int clapCounter = 0;

    boolean openImage = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clapper);

        //textView = (TextView)findViewById(R.id.clapCount);
        imageView = (ImageView)findViewById(R.id.imageView);

        tickerView = (TickerView) findViewById(R.id.clapCount);
        tickerView.setCharacterList(TickerUtils.getDefaultNumberList());
        tickerView.setTextSize(100);
        tickerView.setGravity(Gravity.CENTER);
        tickerView.setAnimationDuration(100);
        tickerView.setText("0");

        claps = new Clap(c);
    }

    private void changeImage(){
        if(openImage){
            openImage = false;
            imageView.setImageResource(R.drawable.closed);
        }
        else {
            openImage = true;
            imageView.setImageResource(R.drawable.opened);
        }
    }

    public void onClick(View v)
    {
        clapCounter++;
        tickerView.setText(""+clapCounter);
        changeImage();
        claps.doClap();
    }
}
