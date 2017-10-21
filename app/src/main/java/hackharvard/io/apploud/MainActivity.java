package hackharvard.io.apploud;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    //Context context = this;


    EditText inputBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputBox = (EditText) findViewById(R.id.textInputBox);

        inputBox.setOnEditorActionListener(new EditText.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event){
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    start();

                }
                return false;
            }
        });

    }

    public void startButton(View v){
        start();
    }

    public void start(){
        String testInput = inputBox.getText().toString();

        if(testInput.isEmpty()){
            Context appContext = getApplicationContext();
            CharSequence ErrorToast = "Name can't be empty!";
            int lengthToast = Toast.LENGTH_SHORT;
            Toast.makeText(appContext, ErrorToast, lengthToast).show();
            //Log.d("Error:", "Name can't be empty.");
        }
        else{
            File saveName = new File("saveName");
            if(saveName.exists()){
                MainActivity.this.deleteFile("saveName");
            }

            try{
                FileOutputStream outputStream = openFileOutput("saveName",
                        MainActivity.this.MODE_PRIVATE);
                outputStream.write(testInput.getBytes());
                outputStream.close();
                Log.d("saved", " success");
            }
            catch(Exception e){
                e.printStackTrace();
            }

            Intent intent = new Intent(this, Clapper.class);
            startActivity(intent);
        }
    }

}
