package com.example.firebase;

import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder>{

    Context context;

    ArrayList<MessageItem> chatarraylist;

    private static  Integer MSG_RIGHT = 1;
    private static  Integer MSG_LEFT = 0;



    public MessageAdapter(Context context,ArrayList<MessageItem> chatarraylist){
        this.chatarraylist = chatarraylist;
        this.context = context;



    }

    public static class MessageViewHolder extends RecyclerView.ViewHolder {
        TextView send_message;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            send_message = itemView.findViewById(R.id.senders);


        }
    }


    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == MSG_RIGHT){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item1,parent,false);
            return new MessageAdapter.MessageViewHolder(view);

        }else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item2,parent,false);
            return new MessageAdapter.MessageViewHolder(view);

        }

    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {

        holder.send_message.setText(chatarraylist.get(position).getMessage_Chat());


    }

    @Override
    public int getItemCount() {
        return chatarraylist.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (MainActivity.User_Login.equals(chatarraylist.get(position).getMessage_Sender()) && Messaging.Reciever.equals(chatarraylist.get(position).getMessage_Receiver())) {

            return MSG_RIGHT;

        } else   {
            return MSG_LEFT;

        }

    }




}
