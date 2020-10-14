package id.rezayds.tedi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import id.rezayds.tedi.helper.DatabaseHandler;
import id.rezayds.tedi.model.Users;

public class InputNameActivity extends AppCompatActivity {

    private TextView txtName;
    private String selectedAva = "";
    ImageView ava1, ava2, ava3, ava4, ava5, ava6;
    DatabaseHandler db = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_input_name);

        txtName = findViewById(R.id.txtName);
        Button btnSave = findViewById(R.id.btnSave);

        ava1 = findViewById(R.id.ava1);
        ava2 = findViewById(R.id.ava2);
        ava3 = findViewById(R.id.ava3);
        ava4 = findViewById(R.id.ava4);
        ava5 = findViewById(R.id.ava5);
        ava6 = findViewById(R.id.ava6);

        if (db.getCount() > 0) {
            launchHomeScreen();
            finish();
        }

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUser();
            }
        });

        ava1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearBackground();
                selectedAva = "ava1";
                ava1.setBackgroundResource(R.color.colorAccent);
            }
        });

        ava2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearBackground();
                selectedAva = "ava2";
                ava2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
            }
        });

        ava3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearBackground();
                selectedAva = "ava3";
                ava3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
            }
        });

        ava4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearBackground();
                selectedAva = "ava4";
                ava4.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
            }
        });

        ava5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearBackground();
                selectedAva = "ava5";
                ava5.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
            }
        });

        ava6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearBackground();
                selectedAva = "ava6";
                ava6.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
            }
        });
    }

    private void launchHomeScreen() {
        startActivity(new Intent(InputNameActivity.this, MainActivity.class));
        finish();
    }

    private void saveUser() {
        String nama = txtName.getText().toString();
        String ava = selectedAva;
        if (nama.trim().isEmpty() || selectedAva.trim().isEmpty()) {
            Toast.makeText(this, "Nama atau avatar kamu masih kosong!", Toast.LENGTH_SHORT).show();
        } else {
            Users user = new Users(nama, ava);
            db.add(user);
            launchHomeScreen();
        }
    }

    private void clearBackground() {
        ava1.setBackgroundColor(Color.TRANSPARENT);
        ava2.setBackgroundColor(Color.TRANSPARENT);
        ava3.setBackgroundColor(Color.TRANSPARENT);
        ava4.setBackgroundColor(Color.TRANSPARENT);
        ava5.setBackgroundColor(Color.TRANSPARENT);
        ava6.setBackgroundColor(Color.TRANSPARENT);
    }
}