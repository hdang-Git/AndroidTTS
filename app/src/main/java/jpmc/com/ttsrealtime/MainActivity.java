package jpmc.com.ttsrealtime;

import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;
    TextToSpeech tts;
    EditText editText;
    String input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab = findViewById(R.id.floatingActionButton);
        editText = findViewById(R.id.editText);

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR){
                    tts.setLanguage(Locale.ENGLISH);
                }
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input = editText.getText().toString();
                tts.setSpeechRate(0.8f);
//                tts.setPitch(0.5f);
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    tts.speak(input,TextToSpeech.QUEUE_ADD, null, "1");
                } else{
                    tts.speak(input,TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });
    }


    //Release resources
    public void onPause(){
        if(tts !=null){
            tts.stop();
            tts.shutdown();
        }
        super.onPause();
    }
}
