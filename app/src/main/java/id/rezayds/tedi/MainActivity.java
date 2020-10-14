package id.rezayds.tedi;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import id.rezayds.tedi.helper.DatabaseHandler;
import id.rezayds.tedi.helper.PrefManager;

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

        DatabaseHandler db = new DatabaseHandler(this);
        if (db.getCount() == 0) {
            startActivity(new Intent(MainActivity.this, InputNameActivity.class));
            finish();
        }

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_help)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
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

    public void goMeHuruf(View view) {
//        startActivity(new Intent(getApplicationContext(), MatchingVoiceActivity.class));
        startActivity(new Intent(getApplicationContext(), LessonActivity.class));
    }


    public void goMembaca(View view) {
//        startActivity(new Intent(getApplicationContext(), MatchingWordActivity.class));
        startActivity(new Intent(getApplicationContext(), MainActivity2.class));
    }

    public void goMenulis(View view) {
        startActivity(new Intent(getApplicationContext(), DrawingActivity.class));
    }

    public void goHuLengkap(View view) {
        startActivity(new Intent(getApplicationContext(), SpeakActivity.class));
    }

    public void goHuKonsonan(View view) {
        startActivity(new Intent(getApplicationContext(), SpeakKonsonanActivity.class));
    }

    public void goHuVokal(View view) {
        startActivity(new Intent(getApplicationContext(), SpeakVocalActivity.class));
    }

    public void goPengDis1(View view) {
        startActivity(new Intent(getApplicationContext(), PengDisleksiaSatu.class));
    }

    public void goPengDis2(View view) {
        startActivity(new Intent(getApplicationContext(), PengDisleksiaDua.class));
    }

    public void goPengDis3(View view) {
        startActivity(new Intent(getApplicationContext(), PengDisleksiaTiga.class));
    }

}
