package com.example.yoncal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class kolantreman extends AppCompatActivity {

    Button btnBitir;
    TextView kol_sandalye, kol_plank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kolantreman);

        btnBitir = findViewById(R.id.btnbitir);
        kol_sandalye = findViewById(R.id.kolsandalye);
        kol_plank = findViewById(R.id.kolplank);

        final SharedPreferences sharedPreferences = getSharedPreferences("file",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int kolCounter = sharedPreferences.getInt("kol",0);

        if (kolCounter < 3){
            kol_sandalye.setText("SANDALYE ŞINAV x10");
            kol_plank.setText("YAN PLANK 15sn");
        }
        else if(kolCounter <= 5) {
            kol_sandalye.setText("SANDALYE ŞINAV x12");
            kol_plank.setText("YAN PLANK 15sn");
        }
        else if(kolCounter <= 7) {
            kol_sandalye.setText("SANDALYE ŞINAV x15");
            kol_plank.setText("YAN PLANK 15sn");
        }
        else if(kolCounter >= 8) {
            kol_sandalye.setText("SANDALYE ŞINAV x17");
            kol_plank.setText("YAN PLANK 15sn");
        }

        btnBitir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitir();
            }
        });

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Bitir();
        return;
    }

    public void Bitir(){
        AlertDialog.Builder builder = new AlertDialog.Builder(kolantreman.this);

        builder.setTitle("Uyarı");
        builder.setMessage("Spor hareketlerini tamamladınız mı?");

        builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

                saveData();

                startActivity(new Intent(kolantreman.this, MainActivity.class));
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                startActivity(new Intent(kolantreman.this, MainActivity.class));
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("file", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int kolCounter = sharedPreferences.getInt("kol",0);
        editor.putInt("kol", ++kolCounter);
        editor.commit();
    }

}
