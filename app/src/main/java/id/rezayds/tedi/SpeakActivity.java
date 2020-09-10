package id.rezayds.tedi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

public class SpeakActivity extends AppCompatActivity {

    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speak);

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    tts.setLanguage(new Locale("id", "ID"));
                } else {
                    Toast.makeText(SpeakActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        CardView cardA, cardB, cardC, cardD;
        cardA = findViewById(R.id.cardA);
        cardB = findViewById(R.id.cardB);
        cardC = findViewById(R.id.cardC);
        cardD = findViewById(R.id.cardD);

        cardA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speechLetter("A");
            }
        });

        cardB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speechLetter("B");
            }
        });

        cardC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speechLetter("C");
            }
        });

        cardD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speechLetter("D");
            }
        });


    }

    private void speechLetter(String letter) {
        if (letter.equals("")) {
            Toast.makeText(SpeakActivity.this, "Masukan Pesan", Toast.LENGTH_SHORT).show();
        } else {
            tts.speak(letter, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    @Override
    protected void onPause() {
        if (tts != null && tts.isSpeaking()) {
            tts.stop();
        }
        super.onPause();
    }
}