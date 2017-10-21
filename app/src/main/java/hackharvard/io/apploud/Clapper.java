package hackharvard.io.apploud;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Clapper extends AppCompatActivity {
    Context c = this;
    Clap claps;

    TextView textView;

    int clapCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clapper);

        textView = (TextView)findViewById(R.id.clapCount);

        claps = new Clap(c);
    }

    public void onClick(View v)
    {
        clapCounter++;
        textView.setText(clapCounter+"");
        claps.doClap();
    }
}
