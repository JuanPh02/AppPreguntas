package com.example.gamepreguntas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class TableroActivity extends AppCompatActivity {

    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9,btn10, btn11, btn12, btn13, btn14, btn15, btn16, btn17, btn18, btn19, btn20;
    EditText etPreguntas, etInfo;
    TextView tvTurno, tvJugador1, tvJugador2, tvPuntaje1, tvPuntaje2;
    RadioGroup rgRespuestas;
    RadioButton rb1, rb2, rb3, rb4;
    ArrayList<Pregunta> lstPreguntas;
    Pregunta pregCargada;
    Button btnSelected;
    User user1 = (User)getIntent().getExtras().getSerializable("user1");
    User user2 = (User)getIntent().getExtras().getSerializable("user2");
    String turno1 = user1.getNombre();
    String turno2 = user2.getNombre();
    int turno = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablero);
        conectar();

        final String archPreguntas = "Preg.txt";
        final opArchivoPlano operacionesArchivo = new opArchivoPlano(getApplicationContext());
        lstPreguntas = operacionesArchivo.cargarPreguntas(archPreguntas);
        tvJugador1.setText(user1.getNombre());
        tvJugador2.setText(user2.getNombre());


        rgRespuestas.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rbSelected = findViewById(checkedId);
                //Toast.makeText(getApplicationContext(),"selected-> " +rbSelected.getText().toString(), Toast.LENGTH_LONG).show();
                //Toast.makeText(getApplicationContext(),"correcta-> " + pregCargada.getResCorrecta(), Toast.LENGTH_LONG).show();
                verificarRespuesta(pregCargada,rbSelected,btnSelected);
                reestablecerZonaPregunta();
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pregCargada = cargarPregunta();
                btnSelected = btn1;
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pregCargada = cargarPregunta();
                btnSelected = btn2;
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pregCargada = cargarPregunta();
                btnSelected = btn3;
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pregCargada = cargarPregunta();
                btnSelected = btn4;
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pregCargada = cargarPregunta();
                btnSelected = btn5;
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pregCargada = cargarPregunta();
                btnSelected = btn6;
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pregCargada = cargarPregunta();
                btnSelected = btn7;
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pregCargada = cargarPregunta();
                btnSelected = btn8;
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pregCargada = cargarPregunta();
                btnSelected = btn9;
            }
        });

        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pregCargada = cargarPregunta();
                btnSelected = btn10;
            }
        });

        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pregCargada = cargarPregunta();
                btnSelected = btn11;
            }
        });

        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pregCargada = cargarPregunta();
                btnSelected = btn12;
            }
        });

        btn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pregCargada = cargarPregunta();
                btnSelected = btn13;
            }
        });

        btn14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pregCargada = cargarPregunta();
                btnSelected = btn14;
            }
        });

        btn15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pregCargada = cargarPregunta();
                btnSelected = btn15;
            }
        });

        btn16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pregCargada = cargarPregunta();
                btnSelected = btn16;
            }
        });

        btn17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pregCargada = cargarPregunta();
                btnSelected = btn17;
            }
        });

        btn18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pregCargada = cargarPregunta();
                btnSelected = btn18;
            }
        });

        btn19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pregCargada = cargarPregunta();
                btnSelected = btn19;
            }
        });

        btn20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pregCargada = cargarPregunta();
                btnSelected = btn20;
            }
        });
    }

    private void reestablecerZonaPregunta() {
        etPreguntas.setText("");
        rb1.setText("");
        rb2.setText("");
        rb3.setText("");
        rb4.setText("");
        rb1.setChecked(false);
        rb2.setChecked(false);
        rb3.setChecked(false);
        rb4.setChecked(false);
    }

    private void verificarRespuesta(Pregunta p, RadioButton rbSelected, Button b) {
        String respSelected = rbSelected.getText().toString().trim();
        if(respSelected.equals(p.getResCorrecta())){
            b.setBackgroundColor(Color.GREEN);
            sumarPuntaje(p.getDificultad());
        } else {
            b.setBackgroundColor(Color.RED);
        }
        b.setClickable(false);
        cambiarTurno();
    }

    private void sumarPuntaje(int puntaje) {
        if(turno==1){
            user1.setPuntaje(user1.getPuntaje() + puntaje);
            tvPuntaje1.setText("Points: " + user1.getPuntaje());
        } else {
            user2.setPuntaje(user2.getPuntaje() + puntaje);
            tvPuntaje2.setText("Points: " + user2.getPuntaje());
        }
    }

    private void cambiarTurno() {
        turno = (turno==1)? 2 : 1;
        tvTurno.setText(turno);
    }

    public Pregunta cargarPregunta() {
        Random rnd = new Random();
        int preg = rnd.nextInt(lstPreguntas.size());
        Pregunta pregSelected = null;
        Pregunta p = lstPreguntas.get(preg);
        try{
            if(!p.isUtilizada()){
                etPreguntas.setText(p.getEnunc());
                rb1.setText(p.getRes1());
                rb2.setText(p.getRes2());
                rb3.setText(p.getRes3());
                rb4.setText(p.getRes4());
                p.setUtilizada(true);
                pregSelected = p;
                return pregSelected;
            } else {
                cargarPregunta();
            }
        } catch (Exception e){
            Toast.makeText(getApplicationContext(),"ERROR AL CARGAR PREGUNTA", Toast.LENGTH_SHORT).show();
        }
        return pregSelected;
    }

    private void conectar() {
        tvTurno = findViewById(R.id.tvTurno);
        tvJugador1 = findViewById(R.id.tvNombre1);
        tvJugador2 = findViewById(R.id.tvNombre2);
        tvPuntaje1 = findViewById(R.id.tvPuntaje1);
        tvPuntaje2 = findViewById(R.id.tvPuntaje2);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn7 = findViewById(R.id.btn6);
        btn6 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btn10 = findViewById(R.id.btn10);
        btn11 = findViewById(R.id.btn11);
        btn12 = findViewById(R.id.btn12);
        btn13 = findViewById(R.id.btn13);
        btn14 = findViewById(R.id.btn14);
        btn15 = findViewById(R.id.btn15);
        btn16 = findViewById(R.id.btn16);
        btn17 = findViewById(R.id.btn17);
        btn18 = findViewById(R.id.btn18);
        btn19 = findViewById(R.id.btn19);
        btn20 = findViewById(R.id.btn20);
        rgRespuestas = findViewById(R.id.rgRespuestas);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        etPreguntas = findViewById(R.id.etPreguntas);
    }
}
