package com.example.yoncal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etSifre;
    Button btngirisyap;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        etEmail = (EditText) findViewById(R.id.etEmail);
        etSifre = (EditText) findViewById(R.id.etSifre);
        TextView tvkaydol = findViewById(R.id.tvKaydol);
        //TextView tvsifremiunuttum = findViewById(R.id.tvSifremiUnuttum);
        btngirisyap = findViewById(R.id.btnGirisYap);

        final String kullaniciadi = etEmail.getText().toString();
        final String sifre = etSifre.getText().toString();

        btngirisyap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (internetKontrol()) { //internet kontrol methodu çağırılıyor
                    Toast.makeText(getApplicationContext(), "İnternete Bağlanın!", Toast.LENGTH_LONG).show();
                    return;
                }

                final String email = etEmail.getText().toString();
                final String password = etSifre.getText().toString();

                try {
                    if (password.length() > 0 && email.length() > 0) {
                        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(LoginActivity.this, "E-mail veya şifreniz uyuşmuyor", Toast.LENGTH_LONG).show();
                                    return;
                                } else {
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(LoginActivity.this, "Boş alanları doldurun", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        tvkaydol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        /*
        tvsifremiunuttum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, forgotpassword.class);
                startActivity(intent);
            }
        });*/
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
