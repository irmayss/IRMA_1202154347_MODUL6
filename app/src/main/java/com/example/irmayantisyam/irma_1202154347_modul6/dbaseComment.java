package com.example.irmayantisyam.irma_1202154347_modul6;

/**
 * Created by IRMAYANTI SYAM on 3/31/2018.
 */
//class database comment
public class dbaseComment {
    //mendeklarasikan variabel yang digunakan
    String yangkomen, komennya, fotoyangdikomen;

    //membuat kelas kosong untuk membaca data
    public dbaseComment(){
    }

    //kelas yang digunakan untuk mengambil nilai berdasarkan parametenr yang ditentukan
    public dbaseComment(String yangkomen, String komennya, String fotoyangdikomen) {
        this.yangkomen = yangkomen;
        this.komennya = komennya;
        this.fotoyangdikomen = fotoyangdikomen;
    }

    //mengatur getter untuk variabel dari class ini
    public String getFotoyangdikomen() {
        return fotoyangdikomen;
    }

    public String getYangkomen() {
        return yangkomen;
    }

    public String getKomennya() {
        return komennya;
    }
}
