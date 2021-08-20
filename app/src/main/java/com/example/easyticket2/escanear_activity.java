package com.example.easyticket2;

import androidx.appcompat.app.AppCompatActivity;
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

public class escanear_activity extends AppCompatActivity {
    ImageView img, imgcamera;
    Button btn, btncamara;
    String rutaImagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escanear);

        imgcamera = findViewById(R.id.imageviewcamera);
        btncamara = findViewById(R.id.btnCamara);
        btncamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirCamara();
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

        btn = findViewById(R.id.btntexto);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TicketTexto_activity.class);
                startActivity(intent);
            }
        });
    }

    //El metodo se coloca en el onclick del boton para abrir camara
    private void abrirCamara() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (intent.resolveActivity(getPackageManager()) != null){
            File imagenArchivo = null;
            try {
                imagenArchivo = crearImagen();
            }catch (IOException ex){
                Log.e("Error", ex.toString());
            }
            if (imagenArchivo != null){
                Uri fotoUri = FileProvider.getUriForFile(this, "com.example.easyticket2", imagenArchivo);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fotoUri);
                startActivityForResult(intent, 1); // para poder saber si esta abriendo la camara
            }
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK){
            Bitmap imgBitmap = BitmapFactory.decodeFile(rutaImagen);
            imgcamera.setImageBitmap(imgBitmap);

        }
    }

    private File crearImagen() throws IOException {
        String nombreImagen = "foto_";
        File directorio = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imagen = File.createTempFile(nombreImagen, ".jpg", directorio);
        rutaImagen = imagen.getAbsolutePath();
        return imagen;
    }
}