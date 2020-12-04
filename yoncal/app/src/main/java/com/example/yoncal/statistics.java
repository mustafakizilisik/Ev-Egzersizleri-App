package com.example.yoncal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class statistics extends AppCompatActivity {

    TextView karin, kol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        karin = findViewById(R.id.tvkarin);
        kol = findViewById(R.id.tvkol);

        final SharedPreferences sharedPreferences = getSharedPreferences("file",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int karinCounter = sharedPreferences.getInt("karin",0);
        int kolCounter = sharedPreferences.getInt("kol",0);

        karin.setText(String.valueOf(karinCounter));
        kol.setText(String.valueOf(kolCounter));

    }
}
