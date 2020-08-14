package com.example.firebase;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;



public class Users extends Fragment {


    public RecyclerView recyclerView;
    public RecyclerView.Adapter adapter;
    public  static ArrayList<String> arrayList = new ArrayList<String>();
    public  static ArrayList<String> arrayList_profile = new ArrayList<String>();
    DatabaseReference ref;
    FirebaseDatabase database;
    WebView webView;
    SharedPreferences sharedPreferences;


    public static Users newInstance() {
        return new Users();
    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

       View vt = inflater.inflate(R.layout.users_fragment, container, false);


       recyclerView = vt.findViewById(R.id.all_users);
       sharedPreferences =getContext().getSharedPreferences("com.App",Context.MODE_PRIVATE);



       recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
       ref = FirebaseDatabase.getInstance().getReference().child("Users");

       ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();
                arrayList_profile.clear();
                for (int x = 0 ;x<dataSnapshot.getChildrenCount();x++) {
                    if (dataSnapshot.child(String.valueOf(x)).child("name").getValue(String.class).equals(sharedPreferences.getString("Username",null))){

                    }else {
                        arrayList.add(dataSnapshot.child(String.valueOf(x)).child("name").getValue(String.class));
                        arrayList_profile.add(dataSnapshot.child(String.valueOf(x)).child("imageUrl").getValue(String.class));

                    }


                }



                adapter = new UserAdapter(getContext(),arrayList,arrayList_profile);
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
       return vt ;

    }



}