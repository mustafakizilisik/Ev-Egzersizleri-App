package com.example.yoncal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class RegisterActivity extends AppCompatActivity {

    EditText etEmail, etSifre, etKullaniciAdi, etAdSoyad, etYas, etBoy, etKilo;
    Button btnkaydol;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();

        /*
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            finish();
        }
        */
        btnkaydol = findViewById(R.id.btnKaydol);
        etEmail = (EditText) findViewById(R.id.etemail);
        etSifre = (EditText) findViewById(R.id.etsifre);
        etKullaniciAdi = (EditText) findViewById(R.id.etkullaniciadi);
        etAdSoyad = (EditText) findViewById(R.id.etadsoyad);
        etYas = (EditText) findViewById(R.id.etyas);
        etBoy = (EditText) findViewById(R.id.etboy);
        etKilo = (EditText) findViewById(R.id.etkilo);

        btnkaydol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (internetKontrol()) { //internet kontrol methodu çağırılıyor
                    Toast.makeText(getApplicationContext(), "İnternete Bağlanın!", Toast.LENGTH_LONG).show();
                    return;
                }
                final String email = etEmail.getText().toString();
                final String password = etSifre.getText().toString();
                final String kullaniciAdi = etKullaniciAdi.getText().toString();
                final String adSoyad = etAdSoyad.getText().toString();
                final String yas = etYas.getText().toString();
                final String boy = etBoy.getText().toString();
                final String kilo = etKilo.getText().toString();

                try {
                    if (password.length()>0 && email.length()>0 && adSoyad.length()>0 && yas.length()>0 && boy.length()>0 && kilo.length()>0) {
                        if (kullaniciAdi.length() < 4) {
                            Toast.makeText(RegisterActivity.this, "Kullanıcı adınızı 4 karakterden uzun giriniz", Toast.LENGTH_LONG).show();
                            return;
                        } else {
                            if (password.length() < 6) {
                                Toast.makeText(RegisterActivity.this, "Şifrenizi 6 karakterden uzun giriniz", Toast.LENGTH_LONG).show();
                                return;
                            } else {
                                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (!task.isSuccessful()) {
                                            Toast.makeText(RegisterActivity.this, "E-mail adresinizi doğru giriniz", Toast.LENGTH_LONG).show();
                                            return;
                                        } else {
                                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    }
                                });
                            }
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "Boş alanları doldurun", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    protected boolean internetKontrol() { //interneti kontrol eden method
        // TODO Auto-generated method stub
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return false;
        }
        return true;
    }
}
