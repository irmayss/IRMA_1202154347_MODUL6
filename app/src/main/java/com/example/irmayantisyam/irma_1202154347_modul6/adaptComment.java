package com.example.irmayantisyam.irma_1202154347_modul6;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by IRMAYANTI SYAM on 3/31/2018.
 */

public class adaptComment extends RecyclerView.Adapter<adaptComment.CommentHolder> {
    //mendeklarasikan variabel yang digunakan
    Context con;
    List<dbaseComment> list;

    //membuat method yang mendeklarasikan konstruktornya
    public adaptComment(Context con, List<dbaseComment> list){
        this.con=con;
        this.list=list;
    }

    //Method untuk menentukan viewholder untuk recyclerview
    @Override
    public CommentHolder onCreateViewHolder(ViewGroup parent, int viewType){
        //mengembalikan nilai dengan tampilan yang bertumpuk (mengatur layout inflater)
        return new CommentHolder(LayoutInflater.from(con).inflate(R.layout.komentar,parent,false));
    }

    //Method untuk mengikat nilai dengan objek pada viewholder
    @Override
    public void onBindViewHolder(CommentHolder holder, int position) {
        dbaseComment cur = list.get(position);
        //menentukan posisi dari database ke dalam variabel cur
        holder.yangkomen.setText(cur.getYangkomen());
        //mengikat atau menyimpan nama pemberi komentar
        holder.komennya.setText(cur.getKomennya());
        //mengikat atau menyimpan komentar yang diberikan
    }

    //Mendapatkan jumlah item pada recyclerview
    @Override
    public int getItemCount() {
        //mengatur ukurannya
        return list.size();
    }

    //Subclass sebagai viewholder
    class CommentHolder extends RecyclerView.ViewHolder{
        //mendeklarasikan variabel yang digunakan
        TextView yangkomen, komennya;
        public CommentHolder(View itemView) {
            super(itemView);
            //menginisiasikan variabel yang telah dideklarasikan berdasarkan ID
            yangkomen = itemView.findViewById(R.id.yangkomen);
            komennya = itemView.findViewById(R.id.komennya);
        }
    }
}
