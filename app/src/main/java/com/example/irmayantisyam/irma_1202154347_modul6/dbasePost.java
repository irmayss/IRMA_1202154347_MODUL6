package com.example.irmayantisyam.irma_1202154347_modul6;

/**
 * Created by IRMAYANTI SYAM on 3/31/2018.
 */

public class dbasePost {
    String image, title, caption, user, key;

    //Dibutuhkan kosong untuk membaca data
    public dbasePost() {
    }

    //membuat kelas konstruktor
    public dbasePost(String caption, String image, String title, String user) {
        this.image = image;
        this.title = title;
        this.caption = caption;
        this.user = user;
    }

    //Mendapatkan key dari Firebase
    public String getKey() {
        return key;
    }

    //Menentukan key dari Firebase
    public void setKey(String key) {
        this.key = key;
    }

    //Sisanya getter variabel dari class ini
    public String getImage() {
        return image;
    }

    //mendapatkan judul dari firebase
    public String getTitle() {
        return title;
    }

    //mendapatkan caption dari firebase
    public String getCaption() {
        return caption;
    }

    //mendapatkan user dari firebase
    public String getUser() {
        return user;
    }

    public void key(String key) {
    }
}
