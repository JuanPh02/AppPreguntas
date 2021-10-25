package com.example.gamepreguntas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ResourceCursorTreeAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText txtCorreoLog, txtPasswordLog, txtNombre, txtCorreoReg, txtPasswordReg;
    private Button btnIngresar, btnRegistrar;
    private TextView tvLogJugador;
    ArrayList<User> lstUsers;
    int logReady = 1;
    User u1 = null, u2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        conectar();
        final opArchivoPlano operacionesArchivo = new opArchivoPlano(getApplicationContext());
        final String archUsers = "Users.txt";



         btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lstUsers = operacionesArchivo.leerUsuarios(archUsers);
                String correoLog = txtCorreoLog.getText().toString();
                String passLog = txtPasswordLog.getText().toString();


                if(correoLog.isEmpty() || passLog.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"INGRESE LOS DATOS REQUERIDOS",Toast.LENGTH_SHORT).show();
                } else if(verificarUser(correoLog, passLog) != null) {
                    if(logReady == 1 ) {
                        logReady++;
                        u1 = verificarUser(correoLog, passLog);
                        Toast.makeText(getApplicationContext(),"BIENVENIDO(A), " + u1.getNombre(),Toast.LENGTH_SHORT).show();
                        tvLogJugador.setText("- - - J U G A D O R  2 - - -");
                        limpiarCamposLog();
                    } else {
                        u2 = verificarUser(correoLog, passLog);
                        Toast.makeText(getApplicationContext(),"BIENVENIDO(A), " + u2.getNombre(),Toast.LENGTH_SHORT).show();
                        limpiarCamposLog();
                        logReady = 1;
                        abrirTablero();
                    }
                }
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = txtNombre.getText().toString().trim();
                String correoReg = txtCorreoReg.getText().toString().trim();
                String passReg = txtPasswordReg.getText().toString().trim();
                if(name.isEmpty() || correoReg.isEmpty() || passReg.isEmpty()){
                    Toast.makeText(getApplicationContext(),"INGRESE LOS DATOS REQUERIDOS",Toast.LENGTH_SHORT).show();
                } else {
                    operacionesArchivo.crearUser(archUsers, name, correoReg, passReg);
                    limpiarCamposReg();
                }
            }
        });
    }

    public void limpiarCamposLog() {
        txtCorreoLog.setText("");
        txtPasswordLog.setText("");
    }

    public void limpiarCamposReg() {
        txtNombre.setText("");
        txtCorreoReg.setText("");
        txtPasswordReg.setText("");
    }

    public User verificarUser(String correo, String pass) {
        for (User u: lstUsers) {
            if(u.getCorreo().equals(correo) && u.getPassword().equals(pass)) {
                return u;
            }
        }
        Toast.makeText(getApplicationContext(),"Los datos son incorrectos ",Toast.LENGTH_LONG).show();
        return null;
    }

    public void abrirTablero() {
        try {
            Intent intentTablero = new Intent(MainActivity.this, TableroActivity.class);
            intentTablero.putExtra("user1", (Serializable) u1);
            intentTablero.putExtra("user2", (Serializable) u2);
            startActivity(intentTablero);
        } catch (Exception ex) {
            Log.e("Error-> ", ex.getMessage());
            Toast.makeText(getApplicationContext(),"Error" + ex.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    private void conectar() {
        tvLogJugador = findViewById(R.id.tvLogJugador);
        txtCorreoLog = findViewById(R.id.txtCorreoLog1);
        txtPasswordLog = findViewById(R.id.txtPasswordLog1);
        btnIngresar = findViewById(R.id.btnIngresar);
        txtNombre = findViewById(R.id.txtNombre);
        txtCorreoReg = findViewById(R.id.txtCorreoReg);
        txtPasswordReg = findViewById(R.id.txtPasswordReg);
        btnRegistrar = findViewById(R.id.btnRegistrar);
    }
}
