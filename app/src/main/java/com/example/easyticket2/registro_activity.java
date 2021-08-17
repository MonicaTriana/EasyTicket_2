package com.example.easyticket2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class registro_activity extends AppCompatActivity {
    Button btn, Presupuesto, btn_ticket;
    ImageView home, camera, config, search;
    RegistroBD registroBD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

/*

        registroBD = new RegistroBD();
        final ArrayList<HashMap<String, String>> listaregistros = registroBD.getRegistros();
        ListView listView = findViewById(R.id.listview);
        final ListAdapter adapter = new SimpleAdapter(registro_activity.this,
                listaregistros, R.layout.activity_plantilla_registro, new String[]{"id", "nombre", "fecha", "hora", "costo"},
                new int []{R.id.idregistro, R.id.nombretienda, R.id.fechacompra, R.id.horacompra, R.id.costo});
        listView.setAdapter(adapter);
        escribirBDFirebase(listaregistros);*/

        btn_ticket = findViewById(R.id.btnticket);
        btn_ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(registro_activity.this, crear_registro_activity.class);
                startActivity(intent);
            }
        });
/*
        private void escribirBDFirebase(ArrayList<HashMap<String, String>> listaregistros){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("registro");
        myRef.setValue(String.valueOf(listaregistros));*/



        ///Boton configuracion
        btn = findViewById(R.id.mis_tickets);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MisTickets_activity.class);
                startActivity(intent);
            }
        });
        //////Boton camara
        camera = findViewById(R.id.camera);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), escanear_activity.class);
                startActivity(intent);
            }
        });

        config = findViewById(R.id.config);
        config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), config_activity.class);
                startActivity(intent);
            }
        });

        /////////Boton home
        home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), menu_activity.class);
                startActivity(intent);
            }
        });
        search = findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
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
    }
}