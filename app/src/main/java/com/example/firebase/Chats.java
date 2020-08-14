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
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Chats extends Fragment {
    RecyclerView recyclerView2;
    RecyclerView.Adapter adapter2;

    public ArrayList<String> chat_users = new ArrayList<String>();


;



    public static Chats newInstance() {
        return new Chats();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View vt =  inflater.inflate(R.layout.chats_fragment, container, false);
        recyclerView2 = vt.findViewById(R.id.chats);

        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));

        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference().child("Chats");
        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (int z = 0; z < dataSnapshot.getChildrenCount(); z++) {
                    String all_receivers = dataSnapshot.child(String.valueOf(z)).child("receiver").getValue(String.class);
                    String Sent_chat = dataSnapshot.child(String.valueOf(z)).child("message_Chats").getValue(String.class);
                    String all_senders = dataSnapshot.child(String.valueOf(z)).child("sender").getValue(String.class);

                    if (all_receivers.equals(MainActivity.User_Login)){

                    }else {
                        if (!chat_users.contains(all_receivers)){
                            chat_users.add(all_receivers);

                        }

                    }

                }




                adapter2 = new ChatAdapter(getContext(),chat_users);
                recyclerView2.setAdapter(adapter2);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });






        return  vt ;
    }


  


    }






