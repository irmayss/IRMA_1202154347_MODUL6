package com.example.irmayantisyam.irma_1202154347_modul6;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class masuk extends AppCompatActivity {
    //mendeklarasikan variabel yang digunakan
    EditText user; EditText pass; ProgressDialog dlg;
    FirebaseAuth auth; FirebaseAuth.AuthStateListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masuk);

        //menginisiasikan variabel yang telah dideklarasikan
        user = findViewById(R.id.user); pass = findViewById(R.id.password);
        dlg = new ProgressDialog(this);
        auth = FirebaseAuth.getInstance();
        listener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = auth.getCurrentUser(); //mengatur nilai firebase pada variabel user
                if(user!=null){ //kondisi ketikan user diinputkan
                    Intent move = new Intent(masuk.this, home.class); //memberikan fungsi intent
                    move.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //menghandle action bar
                    startActivity(move); //memulai intent
                    finish(); //mengakhiri intent
                }
            }
        };
    }
    @Override
    protected void onStop(){ //method ketika berhenti
        super.onStop();
        auth.removeAuthStateListener(listener);
    }
    @Override
    protected void onStart(){ //method ketika dimulai
        super.onStart();
        auth.addAuthStateListener(listener);
    }
    public void masuk(View view){ //method ketika button diklik
        //mengatur pesan dialog loging in
        dlg.setMessage("Loging In");
        //mengambil nilai user dan pass yang disimpan ke dalam variabel baru bertipe string
        String inuser = user.getText().toString();
        String inpass = pass.getText().toString();

        if (!TextUtils.isEmpty(inuser)||!TextUtils.isEmpty(inpass)){ //kondisi pengecekan
            dlg.show();
            auth.signInWithEmailAndPassword(inuser, inpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    //ketika user menginputkan datanya dengan lengkap
                    if (task.isSuccessful()){ //kondisi jika sukses
                        Intent a = new Intent(masuk.this,home.class); //memberikan fungsi intent
                        a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //menghandel action bar
                        startActivity(a); //memulai intent
                        finish();//mengakhiri intent
                    } else {
                        Toast.makeText(masuk.this, "Failed to login!", Toast.LENGTH_SHORT).show(); //menampilkan pesan toast
                    }
                    dlg.dismiss();
                }
            });
        } else {
            //mengatur pesa error ketika field kosong
            user.setError("Masukkan email");
            pass.setError("Masukkan password");
        }
    }
    public void sign(View view){ //method ketika button diklik
        startActivity(new Intent(masuk.this, daftar.class)); //memulai fungsi intent
    }
}
