package com.example.easyticket2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login_activity extends AppCompatActivity {
    Button btn;
    EditText edtEmail, edtpassword;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        //COMPROBAR SI EL USUARIO ESTA FIRMADO
        if (auth.getCurrentUser() != null){
            startActivity(new Intent(login_activity.this, menu_activity.class));
            finish();
        }
        //LOGIN
        btn = findViewById(R.id.button3);
        edtEmail = findViewById(R.id.edtEmail);
        edtpassword = findViewById(R.id.edtpassword);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String email = edtEmail.getText().toString();
            String password = edtpassword.getText().toString();
                //VALIDAR DATOS
                if (TextUtils.isEmpty(email)){
                    Toast.makeText(login_activity.this, "Ingresa un email válido", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(login_activity.this, "Ingresa un password válido", Toast.LENGTH_LONG).show();
                    return;
                }
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(login_activity.this,
                    new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull  Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(login_activity.this, "Datos correctos", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(login_activity.this, menu_activity.class));
                                finish();
                            }else{
                                Toast.makeText(login_activity.this, "Datos incorrectos", Toast.LENGTH_LONG).show();
                                return;
                            }
                        }
                    });
            }
        });
    }
}