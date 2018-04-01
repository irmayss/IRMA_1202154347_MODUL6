package com.example.irmayantisyam.irma_1202154347_modul6;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class user extends Fragment {
    //mendeklarasikan variabel yang akan digunakan
    DatabaseReference ref; adaptPost adapter; ArrayList<dbasePost> list;
    RecyclerView rc;
    public user(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Inisialisasi semua objek pada database
        View v = inflater.inflate(R.layout.fragment_user, container, false);
        ref = FirebaseDatabase.getInstance().getReference().child("post");
        list = new ArrayList<>();
        adapter = new adaptPost(list, this.getContext());
        rc = v.findViewById(R.id.rchomeuser);

        //Menampilkan recyclerview
        rc.setHasFixedSize(true);
        rc.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rc.setAdapter(adapter);

        //Event listener ketika data pada Firebase berubah
        ref.addChildEventListener(new ChildEventListener() {
            //Digunakan untuk membaca postingan user dari Firebase
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //method yang berfugsi mengambil data secara realtime pada firebase
                //Digunakan untuk membaca komen pada post ini dari Firebase
                    dbasePost cur = dataSnapshot.getValue(dbasePost.class);
                    if(cur.getUser().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
                        //kondisi untuk mencocokkan kesesuaian data
                        cur.setKey(dataSnapshot.getKey());
                        //mengatur nilai kunci
                        list.add(cur);
                        //menambahkan nilai pada variabel kunci
                        adapter.notifyDataSetChanged();
                        //mengupdate tampilan
                    }
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s){} //ketika terdapat perubahan

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {} //ketika dihapus

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot,String s){} //ketika dipindahkan

            @Override
            public void onCancelled(DatabaseError databaseError) { //ketika dibatalkan

            }
        });
        return v;
    }
}
