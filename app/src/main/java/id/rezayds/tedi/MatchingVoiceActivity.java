package id.rezayds.tedi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MatchingVoiceActivity extends AppCompatActivity {

    private TextToSpeech tts;
    String rightChoice = "";
    String wrongChoice = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching_voice);

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    tts.setLanguage(new Locale("id", "ID"));
                } else {
                    Toast.makeText(MatchingVoiceActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Random rand  = new Random();
        int randRightIndex = rand.nextInt(25);
        int randWrongIndex = rand.nextInt(25);
        int randAnswerInd = rand.nextInt(2);

        String[] alphabet = {"A", "B", "C", "D", "E", "F", "G",
        "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
        "S", "T", "U", "V", "W", "X", "Y", "Z"};

        if (randRightIndex == randWrongIndex) {
            if (randWrongIndex == 0) {
                randWrongIndex += 1;
            } else {
                randWrongIndex -= 1;
            }
        }

        final Button firstChoice = findViewById(R.id.firstChoice);
        final Button secondChoice = findViewById(R.id.secondChoice);

        rightChoice = alphabet[randRightIndex];
        wrongChoice = alphabet[randWrongIndex];

        if (randAnswerInd == 0) {
            firstChoice.setText(rightChoice);
            secondChoice.setText(wrongChoice);
        } else {
            firstChoice.setText(wrongChoice);
            secondChoice.setText(rightChoice);
        }

        ImageView imageSpeech = findViewById(R.id.letterSpeech);
        imageSpeech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speechLetter(rightChoice);
            }
        });

        firstChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String teks = firstChoice.getText().toString().trim();
                if (teks.equals(rightChoice)) {
                    new SweetAlertDialog(MatchingVoiceActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("Berhasil!")
                            .setContentText("Jawaban kamu benar!")
                            .show();
                } else {
                    new SweetAlertDialog(MatchingVoiceActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Yah...")
                            .setContentText("Jawaban kamu salah!")
                            .show();
                }
            }
        });

        secondChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String teks = secondChoice.getText().toString().trim();
                if (teks.equals(rightChoice)) {
                    new SweetAlertDialog(MatchingVoiceActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("Berhasil!")
                            .setContentText("Jawaban kamu benar!")
                            .show();
                } else {
                    new SweetAlertDialog(MatchingVoiceActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Yah...")
                            .setContentText("Jawaban kamu salah!")
                            .show();
                }
            }
        });
    }

    private void speechLetter(String letter) {
        if (letter.equals("")) {
            Toast.makeText(MatchingVoiceActivity.this, "Masukan Pesan", Toast.LENGTH_SHORT).show();
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