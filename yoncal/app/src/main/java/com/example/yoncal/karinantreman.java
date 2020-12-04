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


public class karinantreman extends AppCompatActivity {

    Button btnBitir;
    TextView sinav, mekik, plank, kopru, bacakkaldirma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_karinantreman);

        btnBitir = findViewById(R.id.btnbitir);
        sinav = findViewById(R.id.sinav);
        mekik = findViewById(R.id.mekik);
        plank = findViewById(R.id.plank);
        kopru = findViewById(R.id.kopru);
        bacakkaldirma = findViewById(R.id.bacakkaldirma);

        final SharedPreferences sharedPreferences = getSharedPreferences("file",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int karinCounter = sharedPreferences.getInt("karin",0);

        if (karinCounter < 3){
            sinav.setText("ŞINAV x10");
            mekik.setText("MEKİK x10");
            kopru.setText("KÖPRÜ x10");
            plank.setText("PLANK 15sn");
            bacakkaldirma.setText("BACAK KALDIRMA x5");
        }
        else if(karinCounter <= 5) {
            sinav.setText("ŞINAV x15");
            mekik.setText("MEKİK x12");
            kopru.setText("KÖPRÜ x12");
            plank.setText("PLANK 20sn");
            bacakkaldirma.setText("BACAK KALDIRMA x7");
        }
        else if(karinCounter <= 7) {
            sinav.setText("ŞINAV x20");
            mekik.setText("MEKİK x15");
            kopru.setText("KÖPRÜ x15");
            plank.setText("PLANK 25sn");
            bacakkaldirma.setText("BACAK KALDIRMA x10");
        }
        else if(karinCounter >= 8) {
            sinav.setText("ŞINAV x25");
            mekik.setText("MEKİK x17");
            kopru.setText("KÖPRÜ x17");
            plank.setText("PLANK 30sn");
            bacakkaldirma.setText("BACAK KALDIRMA x12");
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
        AlertDialog.Builder builder = new AlertDialog.Builder(karinantreman.this);

        builder.setTitle("Uyarı");
        builder.setMessage("Spor hareketlerini tamamladınız mı?");

        builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

                saveData();

                startActivity(new Intent(karinantreman.this, MainActivity.class));
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                startActivity(new Intent(karinantreman.this, MainActivity.class));
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("file", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int karinCounter = sharedPreferences.getInt("karin",0);
        editor.putInt("karin", ++karinCounter);
        editor.commit();
    }

}
