package com.example.irmayantisyam.irma_1202154347_modul6;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by IRMAYANTI SYAM on 3/31/2018.
 */

public class adaptPost extends RecyclerView.Adapter<adaptPost.PostViewHolder> {
    private List<dbasePost> list;
    private Context con;

    //mebuat kelas konstruktor
    public adaptPost(List<dbasePost> list, Context con) {
        this.list = list;
        this.con = con;
    }

    //Return ViewHolder untuk adapter
    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //mengembalikan nilai dengan tampilan yang bertumpuk (mengatur layout inflater)
        return new PostViewHolder(LayoutInflater.from(con).inflate(R.layout.feed, parent, false));
    }

    //Method untuk mengikat nilai dengan objek pada viewholder
    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        dbasePost current = list.get(position);
        //menentukan posisi nilai database pada variabel current
        String [] user = current.user.split("@");
        //perintah untuk memberi fungsi split pada android berdasarkan varaibel user dengan tipe array
        holder.user.setText(user[0]);
        //mengatur nilai user yang dimulai dari angkat 0
        holder.user.setTag(current.getKey());
        //mengatur nilai database dengan mengambil nilai kunci
        holder.title.setText(current.getTitle());
        //mengatur judul nilai database
        holder.caption.setText(current.getCaption());
        //mengatur nilai caption atau deskripsi dari database
        holder.caption.setTag(current.getImage());
        //mengatur nilai caption dengan gambar
        Glide.with(con).load(current.getImage()).placeholder(R.drawable.ic_add_a_photo).override(450, 450).into(holder.image);
        //fungsi yang mengatur library untuk meload gambar pada android
    }

    //Mendapatkan jumlah item pada recyclerview
    @Override
    public int getItemCount() {
        return list.size();
        //mengatur ukurannya
    }

    //Subclass sebagai viewholder
    class PostViewHolder extends RecyclerView.ViewHolder{
        //mendeklarasikan semua variabel yang digunakan
        ImageView image; TextView user, title, caption;
        public PostViewHolder(View itemView) {
            super(itemView);
            //mengisiasikan variabel yang telah dideklarasikan
            image = itemView.findViewById(R.id.postgambarnya);
            user = itemView.findViewById(R.id.postpengupload);
            title = itemView.findViewById(R.id.postjudul);
            caption = itemView.findViewById(R.id.postdeskripsi);

            //Operasi ketika item pada recyclerview diklik
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent pindah = new Intent(con, post.class);
                    //memberi fungsi intent untuk perpindahan tampilan
                    pindah.putExtra("user", user.getText());
                    //metode untuk mengirimkan data dari objek intent dengan key user dan value dari inputan variabel user
                    pindah.putExtra("key", user.getTag().toString());
                    //metode untuk mengirimkan data dari objek intent dengan key "key" dan value dari inputan variabel user
                    pindah.putExtra("title", title.getText());
                    //metode untuk mengirimkan data dari objek intent dengan key title dan value dari inputan variabel title
                    pindah.putExtra("caption", caption.getText());
                    //metode untuk mengirimkan data dari objek intent dengan key caption dan value dari inputan variabel caption
                    pindah.putExtra("image", caption.getTag().toString());
                    //metode untuk mengirimkan data dari objek intent dengan key image dan value dari inputan variabel caption
                    con.startActivity(pindah);
                    //memulai intent
                }
            });
        }
    }
}
