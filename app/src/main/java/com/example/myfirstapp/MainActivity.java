package com.example.myfirstapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myfirstapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private EditText editTextNombre;
    private EditText editTextApellido;
    private Spinner spinnerGenero;
    private EditText editTextDate;
    private RadioGroup radioGroup;
    private RadioButton radioButtonSi;
    private RadioButton radioButtonNo;
    private CheckBox checkBoxJava;
    private CheckBox checkBoxJS;
    private CheckBox checkBoxCCPlusPlus;
    private CheckBox checkBoxPython;
    private CheckBox checkBoxGolang;
    private CheckBox checkBoxCSharp;
    private Button buttonEnviar;
    private Button buttonLimpiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        editTextNombre = findViewById(R.id.editTextText2);
        editTextApellido = findViewById(R.id.editTextText3);
        spinnerGenero = findViewById(R.id.spinner);
        editTextDate = findViewById(R.id.editTextDate);
        radioGroup = findViewById(R.id.radioGroup);
        radioButtonSi = findViewById(R.id.radioButton);
        radioButtonNo = findViewById(R.id.radioButton2);
        checkBoxJava = findViewById(R.id.checkBoxJava);
        checkBoxJS = findViewById(R.id.checkBoxJS);
        checkBoxCCPlusPlus = findViewById(R.id.checkBoxCCPlusPlus);
        checkBoxPython = findViewById(R.id.checkBoxPython);
        checkBoxGolang = findViewById(R.id.checkBoxGolang);
        checkBoxCSharp = findViewById(R.id.checkBoxCSharp);
        buttonEnviar = findViewById(R.id.buttonAtras);
        buttonLimpiar = findViewById(R.id.buttonLimpiar);

        List<String> generoList = new ArrayList<>();
        generoList.add("Masculino");
        generoList.add("Femenino");

        ArrayAdapter<String> generoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, generoList);
        generoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGenero.setAdapter(generoAdapter);
        spinnerGenero.setSelection(0);

        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        radioButtonSi.setChecked(true);

        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarFormulario();
            }
        });

        buttonLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiarCampos();
            }
        });
    }

    private void limpiarCampos() {
        // Limpiar campos de texto
        editTextNombre.setText("");
        editTextApellido.setText("");
        editTextDate.setText("");

        // Restablecer el Spinner al índice 0
        spinnerGenero.setSelection(0);

        // Desmarcar CheckBox
        radioButtonSi.setChecked(true); // Puedes establecerlo en el valor predeterminado deseado

        checkBoxJava.setChecked(false);
        checkBoxJS.setChecked(false);
        checkBoxCCPlusPlus.setChecked(false);
        checkBoxPython.setChecked(false);
        checkBoxGolang.setChecked(false);
        checkBoxCSharp.setChecked(false);
    }


    private void validarFormulario() {
        String nombre = editTextNombre.getText().toString().trim();
        String apellido = editTextApellido.getText().toString().trim();
        String fecha = editTextDate.getText().toString().trim();
        boolean leGustaProgramar = radioButtonSi.isChecked();
        boolean alMenosUnLenguajeSeleccionado = checkBoxJava.isChecked() || checkBoxJS.isChecked() || checkBoxCCPlusPlus.isChecked() || checkBoxPython.isChecked() || checkBoxGolang.isChecked() || checkBoxCSharp.isChecked();

        if (nombre.isEmpty() || apellido.isEmpty() || fecha.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos obligatorios.", Toast.LENGTH_SHORT).show();
        } else if (leGustaProgramar && !alMenosUnLenguajeSeleccionado) {
            Toast.makeText(this, "Si le gusta programar, debe seleccionar al menos un lenguaje.", Toast.LENGTH_SHORT).show();
        } else if (!leGustaProgramar && alMenosUnLenguajeSeleccionado) {
            Toast.makeText(this, "Si no le gusta programar, no debe seleccionar ningún lenguaje.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Datos enviados con éxito.", Toast.LENGTH_SHORT).show();
            enviarDatos();
        }
    }

    private void enviarDatos() {

        Intent intent = new Intent(MainActivity.this, VerDatos.class);

        intent.putExtra("nombre", editTextNombre.getText().toString());
        intent.putExtra("apellido", editTextApellido.getText().toString());
        intent.putExtra("genero", spinnerGenero.getSelectedItem().toString());
        intent.putExtra("fechaNacimiento", editTextDate.getText().toString());
        intent.putExtra("leGustaProgramar", radioButtonSi.isChecked());
        intent.putExtra("java", checkBoxJava.isChecked());
        intent.putExtra("js", checkBoxJS.isChecked());
        intent.putExtra("cPlusPlus", checkBoxCCPlusPlus.isChecked());
        intent.putExtra("python", checkBoxPython.isChecked());
        intent.putExtra("golang", checkBoxGolang.isChecked());
        intent.putExtra("csharp", checkBoxCSharp.isChecked());

        startActivity(intent);
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year1, month1, dayOfMonth1) -> {
                    // La fecha seleccionada se almacena en year, month y dayOfMonth
                    String selectedDate = dayOfMonth1 + "/" + (month1 + 1) + "/" + year1;
                    editTextDate.setText(selectedDate);
                }, year, month, dayOfMonth);

        datePickerDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}