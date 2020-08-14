package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    EditText Name,Email,Password;
    Button Register;
    TextView LoginPage;
    FirebaseAuth mAuth;
    DatabaseReference reference;
    FirebaseDatabase database;
     public  static  Animation animation;
    int maxid;
    Member member;
    public  static String User_Login;
   public static  String Email_Login;
   LinearLayout linearLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("com.App",Context.MODE_PRIVATE);
        User_Login = sharedPreferences.getString("Username",null);
        if(sharedPreferences.getString("Username",null)!=null){
            Intent intent = new Intent(MainActivity.this,MainActivity2.class);
            startActivity(intent);

        }


        Name = findViewById(R.id.Name);
        Password = findViewById(R.id.Password);
        Email = findViewById(R.id.Email);
        Register = findViewById(R.id.Register);
        LoginPage = findViewById(R.id.LoginPage);
        linearLayout = findViewById(R.id.linear1);
        member = new Member();
        animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animation);
        linearLayout.setAnimation(animation);


        reference =  database.getInstance().getReference().child("Users");
           reference.addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot snapshot) {
                   if (snapshot.exists()){
                       maxid = (int ) snapshot.getChildrenCount();


                   }else {

                   }

               }

               @Override
               public void onCancelled(@NonNull DatabaseError error) {

               }
           });

           Register.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {

                   if (Name.getText().toString().isEmpty() ||Email.getText().toString().isEmpty() || Password.getText().toString().isEmpty()){
                       Toast.makeText(getApplicationContext(),"Empty Fields",Toast.LENGTH_LONG).show();

                   }else {
                       member.setName(Name.getText().toString());
                       member.setEmail(Email.getText().toString());
                       member.setPassword(Password.getText().toString());
                       member.setImageUrl("Default");

                       reference.child(String.valueOf(maxid)).setValue(member);
                       Name.setText("");
                       Email.setText("");
                       Password.setText("");

                       Intent iot = new Intent(MainActivity.this,LoginActivity.class);
                       startActivity(iot);


                   }



               }
           });

           LoginPage.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                   startActivity(intent);
               }
           });

    }

    @Override
    public void onBackPressed() {
        this.finishAffinity();
    }


}