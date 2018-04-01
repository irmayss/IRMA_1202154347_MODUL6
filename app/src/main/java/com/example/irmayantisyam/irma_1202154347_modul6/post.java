package com.example.irmayantisyam.irma_1202154347_modul6;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class post extends AppCompatActivity {
    //mendeklarasikan variabel yang digunakan
    TextView user, title, caption;
    ImageView image;
    EditText sourcekomentar;
    RecyclerView rc;
    adaptComment adapter;
    ArrayList<dbaseComment> list;
    DatabaseReference dref;
    ProgressDialog pd;
    String usernya, idfoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        //menginisiasikan variabel yang telah dideklarasikan
        user = findViewById(R.id.yangaplod);
        image = findViewById(R.id.gambardaripost);
        sourcekomentar = findViewById(R.id.sourcekomentar);
        pd = new ProgressDialog(this);
        title = findViewById(R.id.judulgambarpost);
        caption = findViewById(R.id.deskripsigambarpost);
        dref = FirebaseDatabase.getInstance().getReference().child("comment");
        rc = findViewById(R.id.rckomentar);
        list = new ArrayList<>();
        adapter = new adaptComment(this, list);

        //mengatur tampilan dari recycler view
        rc.setHasFixedSize(true);
        rc.setLayoutManager(new LinearLayoutManager(this));
        rc.setAdapter(adapter);

        //Memberikan tulisan atau nilai untuk View pada class
        String [] usersaatini  = FirebaseAuth.getInstance().getCurrentUser().getEmail().split("@");
        //mengatur nilai array pada user
        usernya = usersaatini[0];

        //menangkap intent pada objek yang telah disimpan datanya pada putextra
        idfoto = getIntent().getStringExtra("key");
        user.setText(getIntent().getStringExtra("user"));
        title.setText(getIntent().getStringExtra("title"));
        caption.setText(getIntent().getStringExtra("caption"));
        //sebagai library gambar yang meload gambar
        Glide.with(this).load(getIntent().getStringExtra("image")).override(250,250).into(image);

        //method yang berfugsi mengambil data secara realtime pada firebase
        //Digunakan untuk membaca komen pada post ini dari Firebase
        dref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    dbaseComment cur = dataSnapshot.getValue(dbaseComment.class);
                    //mengambil data dari database yang disimpan pada variabel cur
                    if(cur.getFotoyangdikomen().equals(idfoto)){ //kondisi pengecekan
                        list.add(cur); //menambahkan data pada variabel
                        adapter.notifyDataSetChanged(); //melakukan update pada adapter
                    }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s){} //ketikan diganti
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) { //ketika dihapus
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) { //ketika dipindahkan
            }
            @Override
            public void onCancelled(DatabaseError databaseError) { //ketika dibatalkan

            }
        });
    }
    //method ketika button diklik
    public void postcomment(View view) {
        //Mengatur pesan dialog
        pd.setMessage("Adding comment. . . ");
        //menampilkan dialog
        pd.show();

        //Inisialisasi objek
        dbaseComment com = new dbaseComment(usernya, sourcekomentar.getText().toString(), idfoto);

        //Input data ke Firebase
        dref.push().setValue(com).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) { //ketika datanya sudah lengkap
                if(task.isSuccessful()){ //pengecekan ketika perintah sukses
                    Toast.makeText(post.this, "Comment added", Toast.LENGTH_SHORT).show(); //menampilkan pesan toast
                    sourcekomentar.setText(null);//komentar diatur kosong lagi
                }else{
                    Toast.makeText(post.this, "Failed to comment", Toast.LENGTH_SHORT).show(); //menampilkan pesan toast
                }
                pd.dismiss(); //progres dialog ditutup
            }
        });
    }
}
