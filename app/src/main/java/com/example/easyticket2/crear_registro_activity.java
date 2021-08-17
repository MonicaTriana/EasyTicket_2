package com.example.easyticket2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class crear_registro_activity extends AppCompatActivity {
    Button btnguardar;
    EditText txtnombre, txtcosto, txtfecha, txthora;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_registro);

        btnguardar = findViewById(R.id.btnguardar);
        txtnombre = findViewById(R.id.nombretienda);
        txtcosto = findViewById(R.id.costo);
        txtfecha = findViewById(R.id.fechacompra);
        txthora = findViewById(R.id.horacompra);
        RegistroBD registroBD = new RegistroBD();

        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = txtnombre.getText().toString();
                String costo = txtcosto.getText().toString();
                String fecha = txtfecha.getText().toString();
                String hora = txthora.getText().toString();
                registroBD.createRegistro(nombre, costo, fecha, hora);
                Toast.makeText(crear_registro_activity.this, "Se ha registrado tu ticket correctamente",
                        Toast.LENGTH_LONG).show();
                startActivity(new Intent(crear_registro_activity.this, registro_activity.class));
            }
        });
    }
}