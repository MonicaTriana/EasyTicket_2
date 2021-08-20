package com.example.easyticket2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;

public class menu_activity extends AppCompatActivity {
    Button btn, Presupuesto;
    ImageView img, btncamera;
    CardView card;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ///Boton configuracion
        btn = findViewById(R.id.mis_tickets);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MisTickets_activity.class);
                startActivity(intent);
            }
        });

        ///Boton camara
        btncamera = findViewById(R.id.btncamera);
        btncamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), escanear_activity.class);
                startActivity(intent);
            }
        });

        img = findViewById(R.id.config);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), config_activity.class);
                startActivity(intent);
            }
        });

        /////////Boton home
        img = findViewById(R.id.home);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), menu_activity.class);
                startActivity(intent);
            }
        });
        img = findViewById(R.id.search);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BuscarProducto_activity.class);
                startActivity(intent);
            }
        });


        Presupuesto = findViewById(R.id.presupuesto);
        Presupuesto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MiPresupuesto_activity.class);
                startActivity(intent);
            }
        });

        card = findViewById(R.id.cardregistro);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),registro_activity.class);
                startActivity(intent);
            }
        });

    }


}