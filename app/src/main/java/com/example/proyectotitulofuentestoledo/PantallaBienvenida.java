package com.example.proyectotitulofuentestoledo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.Image;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;

public class PantallaBienvenida extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_bienvenida);
    }

    @SuppressLint("ResourceType")
    public void qrPoppup(View v){
        ImageView qr = findViewById(R.id.ivLogoQR);
        PopupMenu pm = new PopupMenu(this, qr);
        pm.getMenuInflater().inflate(R.layout.activity_qrpopup,pm.getMenu());
        pm.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                return false;
            }
        });
    }


}