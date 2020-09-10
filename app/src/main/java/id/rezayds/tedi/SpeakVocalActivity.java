package id.rezayds.tedi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Toast;

import java.util.Locale;

public class SpeakVocalActivity extends AppCompatActivity {

    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speak_vocal);

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    tts.setLanguage(new Locale("id", "ID"));
                } else {
                    Toast.makeText(SpeakVocalActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        CardView cardA, cardE, cardI, cardO, cardU;
        cardA = findViewById(R.id.cardA);
        cardE = findViewById(R.id.cardE);
        cardI = findViewById(R.id.cardI);
        cardO = findViewById(R.id.cardO);
        cardU = findViewById(R.id.cardU);

        cardA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speechLetter("A");
            }
        });

        cardE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speechLetter("E");
            }
        });

        cardI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speechLetter("I");
            }
        });

        cardO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speechLetter("O");
            }
        });

        cardU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speechLetter("U");
            }
        });


    }

    private void speechLetter(String letter) {
        if (letter.equals("")) {
            Toast.makeText(SpeakVocalActivity.this, "Masukan Pesan", Toast.LENGTH_SHORT).show();
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