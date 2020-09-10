package id.rezayds.tedi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import id.rezayds.tedi.Helper.PrefManager;

public class InputNameActivity extends AppCompatActivity {

    private PrefManager prefManager;
    private TextView txtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_input_name);

        txtName = findViewById(R.id.txtName);
        Button btnName = findViewById(R.id.btnName);

        prefManager = new PrefManager(InputNameActivity.this);
        if (!prefManager.getUserName().isEmpty()) {
            launchHomeScreen();
            finish();
        }

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        btnName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchHomeScreen();
            }
        });
    }

    private void launchHomeScreen() {
        prefManager.setUserName(txtName.getText().toString());
        startActivity(new Intent(InputNameActivity.this, MainActivity.class));
        finish();
    }
}