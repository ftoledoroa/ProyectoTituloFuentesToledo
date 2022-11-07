package com.example.proyectotitulofuentestoledo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectotitulofuentestoledo.modelo.Usuario;
import com.example.proyectotitulofuentestoledo.modelo.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegistrarCuenta extends AppCompatActivity {
    private FirebaseAuth mAuth;
// Access a Cloud Firestore instance from your Activity

    FirebaseFirestore mDB = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_cuenta);
        FirebaseApp.initializeApp(this);
        // Initialize Firebase Auth
        this.mAuth = FirebaseAuth.getInstance();
        this.mDB = FirebaseFirestore.getInstance();

        // llamar a dos widgets
        EditText etNombre = findViewById(R.id.etNombre);
        EditText etApellido = findViewById(R.id.etApellido);
        EditText etRut = findViewById(R.id.etRut);
        EditText etCorreo = findViewById(R.id.etEmail);
        EditText etPassword = findViewById(R.id.etPassword);
        EditText etRePassword = findViewById(R.id.etRePassword);
        Button btRegistrar = findViewById(R.id.btRegistrar);

        btRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correo = etCorreo.getText().toString();
                String pass = etPassword.getText().toString();
                String rePass = etRePassword.getText().toString();
                //String email = mAuth.getCurrentUser().getEmail();
                String apellido = etApellido.getText().toString();
                String nombre = etNombre.getText().toString();
                String rut = etRut.getText().toString();
                String password = etPassword.getText().toString();

                //verificar si usuario y contraseña son no vacios
                if(correo.equals("")|| pass.equals((""))){
                    Toast.makeText(getApplicationContext(),"Ingrese todos los Campos", Toast.LENGTH_LONG).show();
                    return;
                }
                //verificar si las contraseñas son iguales
                if(pass.equals(rePass)){
                    //crear usuario nuevo en mAuth
                    mAuth.createUserWithEmailAndPassword(correo, pass)
                            .addOnCompleteListener(RegistrarCuenta.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d("FIREBASE", "createUserWithEmail:success");
                                        //FirebaseUser user = mAuth.getCurrentUser();
                                        //updateUI(user);
                                        //creando nuevo documento en la bd
                                        Usuario usuario = new Usuario (apellido, nombre, correo, rut, password, 1, 1);
                                        //mDB.collection("usuarios").add(usuario); // para añadir a la base de datos

                                        mDB.collection("usuarios")
                                                .add(usuario)
                                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                    @Override
                                                    public void onSuccess(DocumentReference documentReference) {
                                                        Log.d(String.valueOf(RegistrarCuenta.this), "DocumentSnapshot added with ID: " + documentReference.getId());
                                                        Toast.makeText(RegistrarCuenta.this, "Nuevo Usuario Regirstrado", Toast.LENGTH_SHORT).show();
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Log.w(String.valueOf(RegistrarCuenta.this), "Error adding document", e);
                                                        Toast.makeText(RegistrarCuenta.this, "Error al añadir usuario", Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                        Intent i = new Intent(RegistrarCuenta.this, MainActivity.class);
                                        startActivity(i);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w("FIREBASE", "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(RegistrarCuenta.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                        //updateUI(null);
                                    }
                                }
                            });
                }
                else{
                    Toast.makeText(getApplicationContext(),"Las contraseñas son incorrectas", Toast.LENGTH_LONG).show();
                    return;
                }
                //laskfjlkasjlkdjas

            }
        });
    }
}