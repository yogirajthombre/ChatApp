package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity3 extends AppCompatActivity {
    Button logout;
    ImageButton Back_Button;
    TextView account_user;
    TextView account_email,account_photo,photo_link;
    ImageView account_profile_photo;
    StorageReference storageReference;
    Uri url;
    int SELECT_PHOTO = 1;
    UploadTask uploadTask;
    CircleImageView circleImageView1;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        logout = findViewById(R.id.logout);
        Back_Button = findViewById(R.id.back_button);
        account_user = findViewById(R.id.account_username);
        account_email = findViewById(R.id.account_email);
        account_photo = findViewById(R.id.Add_Photo);
        circleImageView1 = findViewById(R.id.account_pic);

       sharedPreferences = getSharedPreferences("com.App",Context.MODE_PRIVATE);
        if (MainActivity2.path!=null){

            Glide.with(getApplicationContext()).load(sharedPreferences.getString("profile_url",null)).into(circleImageView1);
        }


        storageReference = FirebaseStorage.getInstance().getReference("uploads");
        Back_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(MainActivity3.this,MainActivity2.class);
                startActivity(back);

            }
        });

       account_user.setText(MainActivity2.textView1.getText().toString());
       account_email.setText(sharedPreferences.getString("Useremail",null));

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

          sharedPreferences.edit().clear().apply();
          sharedPreferences.edit().remove("Username").apply();
          sharedPreferences.edit().remove("Useremail").apply();
          sharedPreferences.edit().remove("profile_url").apply();
          Users.arrayList.clear();
          Users.arrayList_profile.clear();

          Messaging.all_chats.clear();
          Intent relogin = new Intent(MainActivity3.this,MainActivity.class);
          startActivity(relogin);

            }
        });

        account_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,SELECT_PHOTO);
            }
        });




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SELECT_PHOTO && resultCode == RESULT_OK && data.getData()!=null && data!=null){
            url = data.getData();



            sharedPreferences.edit().putString("profile_url",url.toString()).apply();
            upload_image(url);

            Glide.with(getApplicationContext()).load(url).into(circleImageView1);
        }
    }

    private void upload_image(final Uri uriImage) {



        final ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        String name = mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uriImage));

        final StorageReference reference = storageReference.child(sharedPreferences.getString("Username",null));
        reference.putFile(uriImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(final Uri uri) {
              

                        sharedPreferences.edit().putString("profile_image_url",uri.toString());

                        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
                        databaseReference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                               
                                for (int a = 0;a<dataSnapshot.getChildrenCount();a++){
                                   String name = dataSnapshot.child(String.valueOf(a)).child("name").getValue(String.class);
                                   if (name.equals(sharedPreferences.getString("Username",null))){
                                      
                                       databaseReference.child(String.valueOf(a)).child("imageUrl").setValue(uri.toString());
                                       break;


                                   }
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });



                    }
                });




            }
        });




    }


    @Override
    protected void onStart() {



        super.onStart();
    }
}


