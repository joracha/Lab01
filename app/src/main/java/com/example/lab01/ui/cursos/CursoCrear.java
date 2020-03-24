package com.example.lab01.ui.cursos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.lab01.AccesoDatos.ModelData;
import com.example.lab01.Logica.Carrera;
import com.example.lab01.Logica.Curso;
import com.example.lab01.MenuPrincipal;
import com.example.lab01.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CursoCrear extends AppCompatActivity {

    private Button button;
    private EditText editText_codigo;
    private EditText editText_nombre;
    private EditText editText_creditos;
    private EditText editText_horas;
    private Spinner spinner_carrera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curso_crear);

        // Inicialziacion de variables
        this.editText_codigo = (EditText) findViewById(R.id.curso_nuevo_codigo);
        this.editText_nombre = (EditText) findViewById(R.id.curso_nuevo_nombre);
        this.editText_creditos = (EditText) findViewById(R.id.curso_nuevo_creditos);
        this.editText_horas = (EditText) findViewById(R.id.curso_nuevo_horas);
        this.spinner_carrera = (Spinner) findViewById(R.id.curso_nuevo_carrera);
        this.button = (Button) findViewById(R.id.enviar_crear_curso);

        // Spinner con las carreras quemadas
        ArrayList<Carrera> arraySpinner = ModelData.getInstance().getCarreraList();
        Spinner s = (Spinner) findViewById(R.id.curso_nuevo_carrera);
        ArrayAdapter<Carrera> adapter = new ArrayAdapter<Carrera>(this, R.layout.support_simple_spinner_dropdown_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);

        String accion = (String) getIntent().getSerializableExtra("accion");

        if (accion.equals("crear")) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Curso curso = obtenerCursoDeCampos();
                    if (curso != null) {
                        Intent intent = new Intent(CursoCrear.this, MenuPrincipal.class);
                        intent.putExtra("accion", "creado");
                        intent.putExtra("cursonuevo", curso);
                        startActivity(intent);
                    }
                }
            });
        } else if (accion.equals("editar")) {
            this.editText_codigo.setFocusable(false);
            this.editText_codigo.setClickable(false);
            Curso nuevo_curso = (Curso) getIntent().getSerializableExtra("curso_a_editar");
            llenarCampos(nuevo_curso);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Curso curso = obtenerCursoDeCampos();
                    if (curso != null) {
                        Intent intent = new Intent(CursoCrear.this, MenuPrincipal.class);
                        intent.putExtra("accion", "actualizado");
                        intent.putExtra("cursonuevo", curso);
                        startActivity(intent);
                    }
                }
            });
        }
    }

    private void mostrarMensaje(String titulo, String mensaje) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(titulo)
                .setMessage(mensaje)
                .setPositiveButton("entendido!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                }).show();
    }

    private void llenarCampos(Curso nuevo_curso) {
        this.editText_codigo.setText(nuevo_curso.getCodigo());
        this.editText_nombre.setText(nuevo_curso.getNombre());
        this.editText_creditos.setText(String.valueOf(nuevo_curso.getCreditos()));
        this.editText_horas.setText(String.valueOf(nuevo_curso.getHoras()));
        this.spinner_carrera.setSelection(nuevo_curso.getCarrera() - 1);
    }

    private Curso obtenerCursoDeCampos() {

        String codigo = this.editText_codigo.getText().toString();
        String nombre = this.editText_nombre.getText().toString();
        String creditos = this.editText_creditos.getText().toString();
        String horas = this.editText_horas.getText().toString();
        int carrera = this.spinner_carrera.getSelectedItemPosition() + 1;

        // VALIDACIONES
        if (codigo.isEmpty() || nombre.isEmpty() || creditos.isEmpty() || horas.isEmpty()) {
            mostrarMensaje("ERROR", "Se detectaron campos vacios");
            return null;
        }
        if (codigo.length() > 5) {
            mostrarMensaje("ERROR", "Codigo no puede ser mayor a 5 caracteres");
            return null;
        }
        if (codigo.length() < 2) {
            mostrarMensaje("ERROR", "Codigo no puede ser menor a 2 caracteres");
            return null;
        }
        if (nombre.length() < 3) {
            mostrarMensaje("ERROR", "Nombre con extension muy corta");
            return null;
        }
        if (!creditos.matches("[0-9]+")) {
            mostrarMensaje("ERROR", "Creditos invalidos");
            return null;
        }
        if (!horas.matches("[0-9]+")) {
            mostrarMensaje("ERROR", "Horas invalidas");
            return null;
        }

        int creditos_f = Integer.parseInt(creditos);
        int horas_f = Integer.parseInt(horas);

        if (creditos_f > 10) {
            mostrarMensaje("ERROR", "Creditos invalidos");
            return null;
        }

        if (horas_f > 20) {
            mostrarMensaje("ERROR", "Horas invalidas");
            return null;
        }

        return new Curso(codigo, nombre, creditos_f, horas_f, carrera);
    }
}
