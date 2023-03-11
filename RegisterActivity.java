package com.example.loginreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;

public class RegisterActivity extends AppCompatActivity {

    final String TAG = "FIRESTORE";

 private Button registerBtn;


    private volatile boolean stopThreadFlag = false;

    private Handler mainHandler = new Handler();

    FirebaseFirestore db = FirebaseFirestore.getInstance();

   private String username,passwords;
     private EditText uname,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerBtn = (Button) findViewById(R.id.registerBtn);
        uname = findViewById(R.id.uname);
        password = findViewById(R.id.password);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  usernameInput = uname.getText().toString();
                String  passwordInput = password.getText().toString();

                if(!usernameInput.isEmpty() && !passwordInput.isEmpty())
                {

                    addUser(usernameInput,passwordInput);
                    mainactivity();

                }
                else
                {
                    Toast.makeText(RegisterActivity.this,"Please make sure there are no empty fields", Toast.LENGTH_SHORT).show();
                }



            }
        });
    }
        public void addUser(String uname,String password)
        {

            Map<String, Object> user = new HashMap<>();
            user.put("uname", uname);
            user.put("password", password);


            db.collection("users").document("details")
                    .set(user)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.d(TAG, "DocumentSnapshot added with ID: " + uname);
                            Toast.makeText(RegisterActivity.this,"Successfully Added " , Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(RegisterActivity.this,"Error adding user " + e, Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "Error adding document", e);
                        }
                    });

            }
            public void mainactivity(){

            Intent intent = new Intent(this,MainActivity.class);
                int seconds = 5;
            for(int i =0; i< seconds; i++){
            Log.d("THREAD ACTIVITY", "Start Thread : " + i);

           try {
             Thread.sleep(1000);
         } catch (InterruptedException e) {
              e.printStackTrace();
           }
       }
            startActivity(intent);


            }

        };



