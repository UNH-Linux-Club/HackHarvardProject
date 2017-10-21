package hackharvard.io.apploud;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toastReceive(View v)
    {
        Context appContext = getApplicationContext();
        CharSequence textOfToast = "Apploud";
        int lengthOfToast = Toast.LENGTH_SHORT;

        Toast.makeText(appContext, textOfToast, lengthOfToast).show();
        Log.d("", "Apploud");
    }
}
