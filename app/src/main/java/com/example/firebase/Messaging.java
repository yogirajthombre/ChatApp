package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Messaging extends AppCompatActivity {
    ImageButton btn_send;
    EditText text_message;
    public static String msg, Reciever, Sender;
    TextView user;
    FirebaseDatabase database2;
    DatabaseReference reference2;
    public static int message_id = 0;
    UserChats userChats;
    RecyclerView recyclerView1;
    RecyclerView.Adapter adapter;

    public static ArrayList<MessageItem> all_chats = new ArrayList<MessageItem>();

    String Sent_chat;
    String all_senders;
    String all_receivers;
    public MessageItem messageItem;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);


        Intent intent = getIntent();
        Reciever = intent.getStringExtra("reciever");


        Sender = MainActivity.User_Login;


        recyclerView1 = findViewById(R.id.recylerview2);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Messaging.this);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView1.setLayoutManager(linearLayoutManager);
        recyclerView1.setHasFixedSize(true);

        btn_send = findViewById(R.id.btn_send);
        text_message = findViewById(R.id.send_message);
        user = findViewById(R.id.user);
        user.setText(Reciever);

        reference2 = database2.getInstance().getReference().child("Chats");


        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                all_chats.clear();


                for (int z = 0; z < dataSnapshot.getChildrenCount(); z++) {
                    Sent_chat = dataSnapshot.child(String.valueOf(z)).child("message_Chats").getValue(String.class);
                    all_senders = dataSnapshot.child(String.valueOf(z)).child("sender").getValue(String.class);
                    all_receivers = dataSnapshot.child(String.valueOf(z)).child("receiver").getValue(String.class);

                    if ((Sender.equals(all_senders) && Reciever.equals(all_receivers)) || (Reciever.equals(all_senders) && Sender.equals(all_receivers))) {
                        messageItem = new MessageItem(Sent_chat, all_senders, all_receivers);
                        all_chats.add(messageItem);
                    }


                }




                adapter = new MessageAdapter(getApplicationContext(), all_chats);

                recyclerView1.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });






        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                message_id = (int) dataSnapshot.getChildrenCount();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userChats = new UserChats();
                msg = text_message.getText().toString();
                if (Sender == null) {
                    Toast.makeText(Messaging.this, "Sender Not Available", Toast.LENGTH_LONG).show();

                } else {
                    userChats.setMessage_Chats(msg);
                    userChats.setSender(Sender);
                    userChats.setReceiver(Reciever);
                    reference2.child(String.valueOf(message_id)).setValue(userChats);
                    text_message.setText(" ");
                }
            }

        });



    }


}



