package com.example.proyectotitulofuentestoledo;

import static android.content.ContentValues.TAG;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);
        //llamar a firebase
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // llamar a dos widgets
        Button btIniciarSesion = findViewById(R.id.btIniciarSesion);
        TextView tvTitulo = findViewById(R.id.tvTitulo);
        EditText etCorreo = findViewById(R.id.etCorreo);
        Button btCrearCuenta = findViewById(R.id.btCrearCuenta);
        EditText etPassword = findViewById(R.id.etPassword);
        TextView tvRecuperar = findViewById(R.id.tvOlvidopassword);

        btIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correo = etCorreo.getText().toString();
                String pass = etPassword.getText().toString();

                if(correo.equals("")|| pass.equals((""))){
                    Toast.makeText(getApplicationContext(),"Ingrese todos los Campos", Toast.LENGTH_LONG).show();
                    return;
                }
                mAuth.signInWithEmailAndPassword(correo,pass)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("FIREBASE", "createUserWithEmail:success");
                                    Toast.makeText(getApplicationContext(),"Bienvenido", Toast.LENGTH_LONG).show();
                                    //FirebaseUser user = mAuth.getCurrentUser();
                                    //updateUI(user);
                                    Intent i = new Intent(MainActivity.this,BienvenidaPantalla.class);
                                    startActivity(i);

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("FIREBASE", "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(MainActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                }
                            }
                        });
            }
        });

        //Hola a todos

        btCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,RegistrarCuenta.class);
                startActivity(i);
            }

        });

        tvRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Aún no se desarrolla la pantalla de recuperación", Toast.LENGTH_LONG).show();
                //Intent i = new Intent(MainActivity.this,ConfirmarPasswordActivity.class);
                //startActivity(i);
            }

        });
    }
}

// hola hola

//aknlknasdflnflasd