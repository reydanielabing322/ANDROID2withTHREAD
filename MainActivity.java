package com.example.loginreg;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public Button loginBtn;
    private Button regBtn;


    FirebaseFirestore db = FirebaseFirestore.getInstance();

        @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);



            regBtn = (Button) findViewById(R.id.regBtn);
            regBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){openActivityReg();}

            });
          loginBtn = (Button) findViewById(R.id.loginBtn);
          loginBtn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                db.collection("users")
                        .document()
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                String queriedPassword = documentSnapshot.getString("password");
                                if(queriedPassword == null){

                                    Log.d("Firestore", "Data read from Firestore: " + documentSnapshot.getData());

                                    openHomePage();
                                }
                                else
                                {
                                    Toast.makeText(MainActivity.this,"User does not exist", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

              }
          });
        }
        public void openActivityReg () {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);

        }
     public void openHomePage(){

            Intent intent = new Intent(this,HomePage.class);
            startActivity(intent);
     }
    }


