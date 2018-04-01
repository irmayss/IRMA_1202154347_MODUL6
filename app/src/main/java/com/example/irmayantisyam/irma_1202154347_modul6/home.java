package com.example.irmayantisyam.irma_1202154347_modul6;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IRMAYANTI SYAM on 3/31/2018.
 */

public class home extends AppCompatActivity{
    //mendeklarasikan variabel yang digunakan
    ViewPager vipage;
    TabLayout tab;
    AppBarLayout bar;
    Toolbar tlbr;
    FirebaseAuth auth;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //menginisiasikan semua variabel yang telah dideklarasikan
        vipage = findViewById(R.id.viewpages);
        tab = findViewById(R.id.tabs);
        bar = findViewById(R.id.app);
        tlbr = findViewById(R.id.tb);
        auth = FirebaseAuth.getInstance();

        setSupportActionBar(tlbr);
        //mengatur toolbar sebagai actionbar pada jendela aktivitas
        setPager(vipage);
        //memanggil method

        tab.setupWithViewPager(vipage);
        //mengatur tampilan viewpager agar terlihat terpisah
        tab.getTabAt(0).setIcon(R.drawable.ic_group);
        //mengatur tab bar dengan icon yang ditentukan
        tab.getTabAt(1).setIcon(R.drawable.ic_person_add_black_24dp);
        //mengatur tab bar dengan icon yang ditentukan
        tab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onTabSelected(TabLayout.Tab tab) { //ketika tab di klik
                if (tab.getPosition()==0){ //ketika tab dengan nilai array 0 yang dipilih
                    bar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    //mengatur warna untuk tampilan tab
                } else {
                    bar.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    //mengatur warna untuk tampilan tab
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) { //method ketikan tab tidak dipilih
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) { //method ketika tab dipilih ulang
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){ //membuat tampilan menu
        getMenuInflater().inflate(R.menu.logout, menu); //mengatur layout tumpukan dnegan kata logout di pilihan menu
        return true;//mengembalikan nilai benar
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){ //ketika item pilihan terpilih
        if (item.getItemId()==R.id.menulogout){ //ketika item yang dipilih merupakan id logout
            auth.signOut(); //akun akan keluar
            startActivity(new Intent(home.this,masuk.class)); //memberikan dan memulai fungsi intent untuk perpindahan
            finish(); //menutup intent
        }
        return true;
    }

    public void setPager(ViewPager a){ //kelas yang mengatur fragment
        Vadapt adapt = new Vadapt(getSupportFragmentManager()); //mendeklarasikan variabel untuk tampilan berfragment
        adapt.addFrag(new all(), "TERBARU");//mengatur fragment terbaru berdasarkan class all
        adapt.addFrag(new user(), "SAYA"); //mengatur fragment saya berdasarkan class user
        a.setAdapter(adapt); //mengatur adapter
    }

    public void TambahPost (View view){ //method ketika button dipilih
        startActivity(new Intent(home.this, upload.class)); //memberikan fungsi intent
    }

    class Vadapt extends FragmentPagerAdapter{ //class fragment adapter
        //mendeklarasikan variabel yang digunakan
        private final List<Fragment> listfragment = new ArrayList<>();
        private final List<String> lfTitle = new ArrayList<>();
        //kontruktor
        public Vadapt(FragmentManager fm){
            super(fm);
        }
        //method yang medapatkan jumlah frgment
        @Override
        public Fragment getItem(int position){
            //mengatur posisi dari fragment
            return listfragment.get(position);
        }

        public void addFrag (Fragment f, String title){ //method yang menambahkan tampilan fragment
            listfragment.add(f); // //menambahkan fragment
            lfTitle.add(title); //meambahkan judul
        }
        @Override
        public CharSequence getPageTitle(int position){
            return null;
        }
        @Override
        public int getCount(){
            return listfragment.size();
        }
    }
}
