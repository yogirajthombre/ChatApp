package com.example.firebase;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    Context mcontext;
    ArrayList<String> stringArrayList2;
  

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View chater = LayoutInflater.from(mcontext).inflate(R.layout.example_item3,parent,false);
        return new ChatViewHolder(chater);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, final int position) {
        holder.textView1.setText(stringArrayList2.get(position));
        holder.textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent msg = new Intent(mcontext,Messaging.class);
                msg.putExtra("reciever",stringArrayList2.get(position));
                mcontext.startActivity(msg);
            }
        });









    }

    @Override
    public int getItemCount() {
        return stringArrayList2.size();
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder{
        TextView textView1;
        ImageView imageView1;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.user_chat);
        }
    }

    public ChatAdapter(Context mcontext, ArrayList<String> stringArrayList2){
        this.mcontext=mcontext;
        this.stringArrayList2 = stringArrayList2;




    }


}
