package com.example.irmayantisyam.irma_1202154347_modul6;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class daftar extends AppCompatActivity {
    //mendeklarasikan semua variabel yang digunakan
    EditText user; EditText pass, ps; ProgressDialog dlg;
    FirebaseAuth auth; FirebaseAuth.AuthStateListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        //menginsikasikan variabel yanng telah dideklarasikan
        user = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        dlg = new ProgressDialog(this);
        auth = FirebaseAuth.getInstance();
        listener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                //method untuk menyimpan nilai database
                FirebaseUser user = auth.getCurrentUser();
                if(user!=null){ //kondisi ketika usernya telah diisi
                    Intent a = new Intent(daftar.this, daftar.class);
                    //memberikan fungsi intent untuk perpindahan
                    a.addFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
                    //mengatur action saat button diklik
                    startActivity(a);
                    //memulai intent
                }
            }
        };
    }
    @Override
    protected void onStart() { //method memulai
        super.onStart();
        auth.addAuthStateListener(listener);
    }
    @Override
    protected void onStop(){ //method ketika berhenti
        super.onStop();
        auth.removeAuthStateListener(listener);
    }
    public void dftr(View view) { //method ketika button diklik
        //mengatur pesan pada dialog
        dlg.setMessage("Creating account. . .");
        //menampilkan dialog
        dlg.show();

        //mengambil nilai user dan pass yang disimpan ke dalam variabel baru bertipe string
        String inuser = user.getText().toString().trim();
        final String inpass = pass.getText().toString().trim();
        if(!TextUtils.isEmpty(inuser)||!TextUtils.isEmpty(inpass)){ //kondisi pengecekan
            auth.createUserWithEmailAndPassword(inuser, inpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                //Membuat user baru berdasarkan input user
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    //ketika user mengimputkan datanya dengan lengkap
                    if(task.isSuccessful()){ //kondisi jika sukses
                        Toast.makeText(daftar.this, "Account created!", Toast.LENGTH_SHORT).show();
                        //menampilkan pesan toast
                    }else {Log.w("Firebase", task.getException()); //jika gagal
                            Toast.makeText(daftar.this, "Account creation failed!", Toast.LENGTH_SHORT).show();
                            //menampilkan pesan toast
                            user.setText(null); pass.setText(null);
                            //mengatur user dan password belum terisi
                        }
                    dlg.dismiss(); //menutup dialog
                }
            });

        }else{
            user.setError("Masukkan email"); //mengatur pesan error ketika field kosong
            pass.setError("Masukkan password"); //mengatur pesan error ketika field kosong
            user.setText(null); pass.setText(null); //mengatur user dan password belum terisi
        }

    }
    public void back (View view){ //method ketika teks kembali ke login di klik
        Intent kmbli = new Intent(daftar.this, masuk.class); //memberikan intent
        startActivity(kmbli); //memulai intent
        finish();//mengakhiri intent
    }
}
