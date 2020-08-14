package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

import static android.util.Log.println;

public class LoginActivity extends AppCompatActivity {
    EditText Login_Name,Login_Email,Login_Password;
    ImageButton button;
    Button Login_Account ;
    FirebaseDatabase database1;
    DatabaseReference reference1;

    Member member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Login_Email = findViewById(R.id.Email_Login);
        Login_Password = findViewById(R.id.Password_Login);
        Login_Account = findViewById(R.id.Login);
        member = new Member();

        reference1 = database1.getInstance().getReference().child("Users");

        Login_Account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                reference1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (int x = 0;x<dataSnapshot.getChildrenCount();x++){
                            String name =  dataSnapshot.child(String.valueOf(x)).child("name").getValue(String.class);
                            String email =  dataSnapshot.child(String.valueOf(x)).child("email").getValue(String.class);
                            String password =  dataSnapshot.child(String.valueOf(x)).child("password").getValue(String.class);

                            if (name.equals(Login_Email.getText().toString()) || email.equals(Login_Email.getText().toString()) && password.equals(Login_Password.getText().toString())) {
                                SharedPreferences sharedPreferences = getSharedPreferences("com.App",Context.MODE_PRIVATE);
                                sharedPreferences.edit().putString("Username",name).apply();
                                sharedPreferences.edit().putString("Useremail",email).apply();

                                Intent iot = new Intent(LoginActivity.this, MainActivity2.class);
                                startActivity(iot);

                            }else {
                                Toast.makeText(getApplicationContext(),"UserName or Password is incorrect",Toast.LENGTH_LONG).show();
                            }
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



            }
        });

        button = findViewById(R.id.back);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });





    }


}