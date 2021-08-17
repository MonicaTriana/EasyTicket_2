package com.example.easyticket2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class crear_cuenta_activity extends AppCompatActivity {
    Button btn;
    EditText etemail, etpassword;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);

        auth = FirebaseAuth.getInstance();
        etemail = findViewById(R.id.etEmail);
        etpassword = findViewById(R.id.etPassword);
        btn = findViewById(R.id.btn_crear);
        btn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             String email = etemail.getText().toString();
             String password = etpassword.getText().toString();

             auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(crear_cuenta_activity.this,
                     new OnCompleteListener<AuthResult>() {
                         @Override
                         public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(crear_cuenta_activity.this,
                                        "Usuario guardado correctamente", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(crear_cuenta_activity.this, login_activity.class));
                                finish();
                            }else{
                                Toast.makeText(crear_cuenta_activity.this,
                                        "Error al crear usuario", Toast.LENGTH_SHORT).show();
                            }
                         }
                     });

         }
        });

    }
}