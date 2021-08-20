package com.example.easyticket2;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class  RegistroBD {

    /*
    //Fecha y hora del sistema
    Date fecha = Calendar.getInstance().getTime();
    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat ts = new SimpleDateFormat("HH;mm:ss");

    String fechaString = df.format(fecha);
    String horaString = ts.format(fecha);
/*
    public ArrayList<HashMap<String, String>> getRegistros(){
        ArrayList<HashMap<String, String>> listaRegistros = new ArrayList<>();
        for (int i = 1; i<=10; i++){
            HashMap<String, String> registro = new HashMap<>();
            registro.put("id", String.valueOf(i));
            registro.put("nombre", "Nombre tienda " + i);
            registro.put("fecha", fechaString);
            registro.put("hora", horaString);
            registro.put("costo", " $ " + i);

            listaRegistros.add(registro);
        }
        return listaRegistros;
    }*/
    private String rID;
    private String nombre;
    private String costo;
    private String fecha;
    private String hora;

    public RegistroBD(){

    }

    public RegistroBD(String rID, String nombre, String costo, String fecha, String hora) {
        this.rID = rID;
        this.nombre = nombre;
        this.costo = costo;
        this.fecha = fecha;
        this.hora = hora;
    }

    public String getrID() {
        return rID;
    }

    public void setrID(String rID) {
        this.rID = rID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }


}
