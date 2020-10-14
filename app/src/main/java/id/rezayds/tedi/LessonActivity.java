package id.rezayds.tedi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import id.rezayds.tedi.ui.lesson.MeHurufFragment;
import id.rezayds.tedi.ui.lesson.MembacaFragment;
import id.rezayds.tedi.ui.lesson.MenulisFragment;
import id.rezayds.tedi.ui.lesson.TabAdapter;

public class LessonActivity extends AppCompatActivity {

    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);
        getSupportActionBar().hide();

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new MeHurufFragment(), "Mengenal Huruf");
        adapter.addFragment(new MembacaFragment(), "Membaca");
        adapter.addFragment(new MenulisFragment(), "Menulis");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}