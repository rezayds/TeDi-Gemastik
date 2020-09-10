package id.rezayds.tedi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import id.rezayds.tedi.Helper.PrefManager;

public class MainActivity extends AppCompatActivity {

    private static final int WRITE_STORAGE_PERMISSION_CODE = 100;
    private static final int READ_STORAGE_PERMISSION_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        PrefManager prefManager = new PrefManager(getApplicationContext());
        checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, WRITE_STORAGE_PERMISSION_CODE);
        checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE, READ_STORAGE_PERMISSION_CODE);

        if (prefManager.isFirstTimeLaunch()) {
            prefManager.setFirstTimeLaunch(false);
            startActivity(new Intent(MainActivity.this, Welcome.class));
            finish();
        }

        if (prefManager.getUserName().equals("")) {
            prefManager.setUserName("");
            startActivity(new Intent(MainActivity.this, InputNameActivity.class));
            finish();
        }

        TextView txtName = findViewById(R.id.userName);
        String userName = "Halo " + prefManager.getUserName() + "!";
        txtName.setText(userName);

        CardView cardMeHuruf = findViewById(R.id.cardMeHuruf);
        cardMeHuruf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MatchingVoiceActivity.class));
            }
        });

        CardView cardMembaca = findViewById(R.id.cardMembaca);
        cardMembaca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SpeakActivity.class));
            }
        });

        CardView cardMenulis = findViewById(R.id.cardMenulis);
        cardMenulis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DrawingActivity.class));
            }
        });

        CardView cardHuLengkap = findViewById(R.id.cardHuLengkap);
        cardHuLengkap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SpeakActivity.class));
            }
        });

        CardView cardHuKonsonan = findViewById(R.id.cardHuKonsonan);
        cardHuKonsonan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SpeakKonsonanActivity.class));
            }
        });

        CardView cardHuVokal = findViewById(R.id.cardHuVokal);
        cardHuVokal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SpeakVocalActivity.class));
            }
        });
    }

    public void checkPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(
                MainActivity.this,
                permission) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[] { permission }, requestCode
            );
        }
    }
}