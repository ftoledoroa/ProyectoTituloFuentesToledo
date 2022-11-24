package com.example.proyectotitulofuentestoledo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.proyectotitulofuentestoledo.databinding.ActivityBienvenidaPantallaBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class BienvenidaPantalla extends AppCompatActivity {
    Button btnCamara;
    Button btPerfil;
    Button btAdmin;
    ImageView visor;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityBienvenidaPantallaBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBienvenidaPantallaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarBienvenidaPantalla.toolbar);
        binding.appBarBienvenidaPantalla.btPerfil.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_bienvenida_pantalla);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        RelativeLayout rlEscanear = findViewById(R.id.rlEscanearQR);
        rlEscanear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BienvenidaPantalla.this,Camara.class);
                startActivity(i);
                Toast.makeText(BienvenidaPantalla.this, "Acá ase añadirá la funcion de Camara", Toast.LENGTH_SHORT).show();

            }
        });


        RelativeLayout rlReservar = findViewById(R.id.rlReservar);
        rlReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BienvenidaPantalla.this,ActivityReservar.class);
                startActivity(i);

            }
        });

        Button btAdmin = (Button) findViewById(R.id.btAdmin);

        btAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(BienvenidaPantalla.this, ActivityReservarAdmin.class);
                startActivity(i);
            }});



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bienvenida_pantalla, menu);
        return true;
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_bienvenida_pantalla);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}