package com.example.easyticket2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class crear_registro_activity extends AppCompatActivity {
    Button btnguardar;
    EditText txtnombre, txtcosto, txtfecha, txthora;
    long ultimoid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_registro);

        btnguardar = findViewById(R.id.btnguardar);
        txtnombre = findViewById(R.id.nombretienda);
        txtcosto = findViewById(R.id.costo);
        txtfecha = findViewById(R.id.fechacompra);
        txthora = findViewById(R.id.horacompra);
        //RegistroBD registroBD = new RegistroBD();

        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = txtnombre.getText().toString();
                String costo = txtcosto.getText().toString();
                String fecha = txtfecha.getText().toString();
                String hora = txthora.getText().toString();
                /*registroBD.createRegistro(nombre, costo, fecha, hora);
                Toast.makeText(crear_registro_activity.this, "Se ha registrado tu ticket correctamente",
                        Toast.LENGTH_LONG).show();
                startActivity(new Intent(crear_registro_activity.this, registro_activity.class));*/
                createRegistro(nombre, costo, fecha, hora);
            }
        });
    }

//Metodo para insertar en Firebase
    public void createRegistro(String nombre, String costo, String fecha, String hora){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String dispositivo = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference myRef = database.getReference(dispositivo).child("registro");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()){
                    ultimoid = (snapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
        String id = String.valueOf(ultimoid+1);
        RegistroBD registroBD = new RegistroBD(id, costo, nombre, fecha, hora);
        //myRef.setValue(registroBD);
        myRef.child(id).setValue(registroBD);
    }
}