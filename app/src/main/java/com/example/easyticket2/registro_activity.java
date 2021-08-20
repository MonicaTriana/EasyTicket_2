package com.example.easyticket2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
        ListView listView = findViewById(R.id.listview);
        registroBD = new RegistroBD();
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

        //GET REGISTROS
        FirebaseDatabase database =  FirebaseDatabase.getInstance();
        String dispositivo = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference myRef = database.getReference(dispositivo).child("registro");
        myRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(Task<DataSnapshot> task) {
                if (!task.isSuccessful()){
                    Log.e("firebase", "Error getting data", task.getException());
                }else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                    ArrayList<HashMap<String, String>> listaregistros = new ArrayList<>();
                    for (DataSnapshot ds : task.getResult().getChildren()){
                        HashMap<String, String>registro = new HashMap<>();
                        registro.put("id", ds.child("rID").getValue().toString());
                        registro.put("nombre", ds.child("nombre").getValue().toString());
                        registro.put("fecha", ds.child("fecha").getValue().toString());
                        registro.put("hora", ds.child("hora").getValue().toString());
                        registro.put("costo", ds.child("costo").getValue().toString());
                        listaregistros.add(registro);
                    }

                    final ListAdapter adapter = new SimpleAdapter(registro_activity.this,
                            listaregistros, R.layout.activity_plantilla_registro, new String[]{"id", "nombre", "fecha", "hora", "costo"},
                            new int []{R.id.idregistro, R.id.nombretienda, R.id.fechacompra, R.id.horacompra, R.id.costo});
                    listView.setAdapter(adapter);
                }
            }
        });

        //Borrar registro con un click sostenido
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                    String registro = String.valueOf(snapshot.getRef().child(String.valueOf(id + 1)).removeValue());
                    Toast.makeText(registro_activity.this, "Se ha borrado el registro : " + (id + 1),Toast.LENGTH_LONG).show();
                    finish();
                    startActivity(getIntent());
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {

                    }
                });
                    return false;
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