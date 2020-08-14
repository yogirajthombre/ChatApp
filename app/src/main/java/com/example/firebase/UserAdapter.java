package com.example.firebase;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{
  private ArrayList<String> stringArrayList ;
  private ArrayList<String> stringArrayList1 ;
  private Context mContext;

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final UserViewHolder holder, final int position) {
        holder.textView.setText(stringArrayList.get(position));
        if (stringArrayList1.get(position).equals("Default")){
            Glide.with(mContext).load(R.drawable.ic_baseline_account_circle_24).into(holder.imageView1);


        }else {
            Glide.with(mContext).load(stringArrayList1.get(position)).into(holder.imageView1);

        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(){
                    @Override
                    public void run() {
                        Intent intent = new Intent(mContext,Messaging.class);
                        intent.putExtra("reciever",stringArrayList.get(position));
                        mContext.startActivity(intent);
                        super.run();
                    }



                }.start();

            }
        });


    }

    @Override
    public int getItemCount() {
        return stringArrayList.size();
    }

    public UserAdapter(Context mContext,ArrayList<String> stringArrayList,ArrayList<String> stringArrayList1){
        this.stringArrayList = stringArrayList;
        this.stringArrayList1 = stringArrayList1;
        this.mContext = mContext;


    }

    public  static class  UserViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        CircleImageView imageView1;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_user);
            imageView1 = itemView.findViewById(R.id.imageView);

        }
    }
}

