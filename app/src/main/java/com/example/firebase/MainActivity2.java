package com.example.firebase;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;


import com.example.firebase.ui.main.SectionsPagerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import android.os.Bundle;


import com.google.firebase.storage.StorageReference;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity2 extends AppCompatActivity {
    MenuItem item;
    public  static TextView textView1;
    StorageReference storageReference;
    CircleImageView circleImageView;
    DatabaseReference ref2;
    FirebaseDatabase database2;
    public static String ActualUserName,path,path1,User_Status;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        setSupportActionBar(toolbar);
        textView1 = findViewById(R.id.App_UserName);
        circleImageView = findViewById(R.id.profile_photo_account);

        sharedPreferences = getSharedPreferences("com.App",Context.MODE_PRIVATE);
        path = sharedPreferences.getString("profile_url",null);
        textView1.setText(sharedPreferences.getString("Username",null));

        if (path!=null){
            Glide.with(getApplicationContext()).load(path).into(circleImageView);

        }


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       MenuInflater menuInflater =  getMenuInflater();
       menuInflater.inflate(R.menu.profile,menu);
       return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(MainActivity2.this,MainActivity3.class);
                startActivity(intent);
                return false;
            }
        });
        return super.onOptionsItemSelected(item);
    }








    @Override
    public void onBackPressed() {
        this.finishAffinity();
    }


}