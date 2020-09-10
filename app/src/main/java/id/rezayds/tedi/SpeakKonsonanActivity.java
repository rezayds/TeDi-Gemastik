package id.rezayds.tedi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Toast;

import java.util.Locale;

public class SpeakKonsonanActivity extends AppCompatActivity {

    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speak_konsonan);

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    tts.setLanguage(new Locale("id", "ID"));
                } else {
                    Toast.makeText(SpeakKonsonanActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        CardView cardB, cardC, cardD, cardF;
        cardB = findViewById(R.id.cardB);
        cardC = findViewById(R.id.cardC);
        cardD = findViewById(R.id.cardD);
        cardF = findViewById(R.id.cardF);

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

        cardF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speechLetter("F");
            }
        });


    }

    private void speechLetter(String letter) {
        if (letter.equals("")) {
            Toast.makeText(SpeakKonsonanActivity.this, "Masukan Pesan", Toast.LENGTH_SHORT).show();
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