package com.example.irmayantisyam.irma_1202154347_modul6;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class all extends Fragment {
    //mendeklarasikan semua variabel yang digunakan
    RecyclerView rc;
    DatabaseReference dataref;
    ArrayList<dbasePost> list;
    adaptPost adapterPost;

    //method bawaan
    public all(){
    }

    //method yang membuat aturan tampilan pada tab sebelah kiri
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Inisialisasi semua variabel yang telah dideklarasikan sebelumnya
        View v = inflater.inflate(R.layout.fragment_all, container, false);
        rc = v.findViewById(R.id.rchomeall);
        list = new ArrayList<>();
        dataref = FirebaseDatabase.getInstance().getReference().child("post");
        adapterPost = new adaptPost(list, this.getContext());

        //Menampilkan recyclerview
        rc.setHasFixedSize(true);
        //mengatur ukuran
        rc.setLayoutManager(new LinearLayoutManager(this.getContext()));
        //mengatur tampilan inflaternya
        rc.setAdapter(adapterPost);
        //menyimpan nilai adapternya

        //Event listener ketika value pada Firebase berubah
        dataref.addChildEventListener(new ChildEventListener() {
            //Digunakan untuk membaca seluruh postingan dari firebase
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    dbasePost post = dataSnapshot.getValue(dbasePost.class);
                    //mendapatkan nilai database pada variabel post dari kelas dbasepost
                    post.key=dataSnapshot.getKey();
                    //menyimpan nilai kunci dari variabel post
                    list.add(post);
                    //menambahkan item berdasarkan nilai variabel post
                    adapterPost.notifyDataSetChanged();
                    //mengupdate nilai adapter ketika ada item yang ditambahkan, ditambah pada bagian bawah
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                //method dari ChildAdded untuk perubahan
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                //method dari ChildAdded untuk penghapusan
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                //method dari ChildAdded untuk perpindahan
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //methoud untuk membatalkan
            }
        });
        return v;
    }
}
