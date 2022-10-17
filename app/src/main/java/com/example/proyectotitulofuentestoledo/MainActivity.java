package com.example.proyectotitulofuentestoledo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // llamar a dos widgets
        Button btIniciarSesion = findViewById(R.id.btIniciarSesion);
        TextView tvTitulo = findViewById(R.id.tvTitulo);
        EditText etRut = findViewById(R.id.etRut);
        Button btCrearCuenta = findViewById(R.id.btCrearCuenta);
        EditText etPassword = findViewById(R.id.etPassword);
        TextView tvRecuperar = findViewById(R.id.tvOlvidopassword);

        btIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rut = etRut.getText().toString();
                String pass = etPassword.getText().toString();

                if(rut.equals("")|| pass.equals((""))){
                    Toast.makeText(getApplicationContext(),"Ingrese todos los Campos", Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(getApplicationContext(),"Aún no se desarrolla la pantalla de bienvenida", Toast.LENGTH_LONG).show();
                //Intent i = new Intent(MainActivity.this,HomeActivity.class);
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