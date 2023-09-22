package com.example.myfirstapp;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class VerDatos extends AppCompatActivity {

    private String data;
    private TextView dataView;
    private Button btnAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_datos);

        // Recuperar los datos enviados desde la primera actividad
        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombre");
        String apellido = intent.getStringExtra("apellido");
        String genero = intent.getStringExtra("genero");
        String fechaNacimiento = intent.getStringExtra("fechaNacimiento");
        boolean leGustaProgramar = intent.getBooleanExtra("leGustaProgramar", false); // Valor predeterminado si no se encuentra el dato
        boolean java = intent.getBooleanExtra("java", false);
        boolean js = intent.getBooleanExtra("js", false);
        boolean cPlusPlus = intent.getBooleanExtra("cPlusPlus", false);
        boolean python = intent.getBooleanExtra("python", false);
        boolean golang = intent.getBooleanExtra("golang", false);
        boolean csharp = intent.getBooleanExtra("csharp", false);

        data = "Hola, ¿qué tal? Mi nombre es " + nombre + " " + apellido + ".\n";
        data += "Soy de género " + genero + ".\n";
        data += "Nací el " + fechaNacimiento + ".\n";

        if (leGustaProgramar) {
            data += "Me gusta programar y mis lenguajes favoritos son:";
            if (java) data += " Java,";
            if (js) data += " JS,";
            if (cPlusPlus) data += " C/C++,";
            if (python) data += " Python,";
            if (golang) data += " Golang,";
            if (csharp) data += " C#";
            data += ".\n";
        } else {
            data += "No me gusta programar y no he seleccionado ningún lenguaje.\n";
        }

        dataView = findViewById(R.id.dataView);
        dataView.setText(data);

        btnAtras = findViewById(R.id.buttonAtras);
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerDatos.this, MainActivity.class); // Usar MainActivity.this
                startActivity(intent);            }
        });

    }

}
