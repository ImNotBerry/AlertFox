package com.example.alertfox;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Opcion1 extends AppCompatActivity {

    EditText txtNombre;
    EditText txtDescripcion;
    EditText txtLugar;
    EditText txtFecha;
    EditText txtHora;
    Button btnAgregar;
    Button btnDevolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_opcion1);

        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtDescripcion = (EditText) findViewById(R.id.txtDescripcion);
        txtLugar = (EditText) findViewById(R.id.txtLugar);
        txtFecha = (EditText) findViewById(R.id.txtFecha);
        txtHora = (EditText) findViewById(R.id.txtHora);
        btnAgregar = (Button) findViewById(R.id.btnAgregar);
        btnDevolver = (Button) findViewById(R.id.btnDevolver);

        // Esto recoge los datos de la vista anterior, si hay
        // Reutiliza esta vista para hacer las ediciones también
        try {
            Intent intent = getIntent();
            txtNombre.setText(intent.getExtras().getString("nombre"));
            txtDescripcion.setText(intent.getExtras().getString("descripcion"));
            txtLugar.setText(intent.getExtras().getString("lugar"));
            txtFecha.setText(intent.getExtras().getString("fecha"));
            txtHora.setText(intent.getExtras().getString("hora"));
        } catch(Exception e) { }

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Recordatorio recordatorio = new Recordatorio();
                recordatorio.nombre = txtNombre.getText().toString();
                recordatorio.descripcion = txtDescripcion.getText().toString();
                recordatorio.lugar = txtLugar.getText().toString();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/mm/yyyy hh:mm");
                String stringFechaHora = txtFecha.getText().toString() + " " + txtHora.getText().toString();
                recordatorio.fecha = LocalDateTime.parse(stringFechaHora);

                // Cuando vengas desde Opcion4, también incluye la ID (posición en la lista)
                Calendario calendario = Calendario.getSingleton();
                ArrayList<Recordatorio> recordatorios = calendario.getRecordatorios();
                try {
                    Intent intent = getIntent();
                    int id = intent.getExtras().getInt("id");
                    recordatorios.set(id, recordatorio);
                } catch (Exception e) {
                    recordatorios.add(recordatorio);
                }
                calendario.sortRecordatorios();

                finish();
            }
        });

        btnDevolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}