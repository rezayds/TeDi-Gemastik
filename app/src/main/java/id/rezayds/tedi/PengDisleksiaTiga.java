package id.rezayds.tedi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

public class PengDisleksiaTiga extends AppCompatActivity {

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peng_disleksia_tiga);

        TextView textDesc = findViewById(R.id.textDesc);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            textDesc.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
        }
    }
}