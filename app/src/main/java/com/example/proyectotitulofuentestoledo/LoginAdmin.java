package com.example.proyectotitulofuentestoledo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class LoginAdmin extends AppCompatActivity {

    private FirebaseAuth mAuth;
    FirebaseFirestore mDB = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseApp.initializeApp(this);
        //llamar a firebase
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);
        Button btIniciarAdmin = findViewById(R.id.btIniciarAdmin);
        EditText etCorreoAdmin = findViewById(R.id.etCorreoAdmin);
        EditText etPasswordAdmin = findViewById(R.id.etPasswordAdmin);

        btIniciarAdmin.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  String correo = etCorreoAdmin.getText().toString();
                  String pass = etPasswordAdmin.getText().toString();

                  if (correo.equals("") || pass.equals((""))) {
                      Toast.makeText(getApplicationContext(), "Ingrese todos los Campos", Toast.LENGTH_LONG).show();
                      return;
                  } else {
                      mAuth.signInWithEmailAndPassword(correo, pass).addOnCompleteListener(LoginAdmin.this, new OnCompleteListener<AuthResult>() {
                          @Override
                          public void onComplete(@NonNull Task<AuthResult> task) {
                              if (task.isSuccessful()) {
                                  mDB.collection("usuarios").whereEqualTo("correo", correo)
                                          .whereEqualTo("privilegios", 0).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                              @Override
                                              public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                  if (task.isSuccessful()) {
                                                      for (QueryDocumentSnapshot document : task.getResult()) {
                                                          Log.d("FIREBASE DOCUMENT", document.getId() + " => " + document.getData());
                                                          document.getId();
                                                          Toast.makeText(getApplicationContext(), "ACCESO ADMIN", Toast.LENGTH_LONG).show();
                                                          Intent i = new Intent(LoginAdmin.this, ActivityReservarAdmin.class);
                                                          startActivity(i);
                                                      }
                                                  }
                                              }
                                          });
                              } else {
                                  Log.w("FIREBASE", "createUserWithEmail:failure", task.getException());
                                  Toast.makeText(LoginAdmin.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                              }
                          }
                      });
                  }
              }
        });
    }
}






