package com.example.lab01.ui.cursos;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab01.AccesoDatos.ModelData;
import com.example.lab01.Logica.Curso;
import com.example.lab01.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CursoFragment extends Fragment implements CursoAdapter.CursoAdapterListener {

    private CursoViewModel cursoViewModel;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private FloatingActionButton fab;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Inicializacion de variables
        cursoViewModel = new CursoViewModel();
        ArrayList<Curso> cursos = cursoViewModel.listarCursos();
        cursoViewModel = ViewModelProviders.of(this).get(CursoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_cursos, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.lista_cursos);

        // Boton flotante
        fab = (FloatingActionButton) root.findViewById(R.id.crear_curso);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearCurso(view);
            }
        });

        // Elementos para el recicle view
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        // Creando el adapter
        CursoAdapter adapter = new CursoAdapter(cursos, this);

        // Asignando el adapter
        recyclerView.setAdapter(adapter);

        return root;
    }

    public void crearCurso(View view) {
        Toast.makeText(getActivity().getApplicationContext(), "Crear curso", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getActivity(), CursoCrear.class);
        startActivity(intent);
    }

    // Evento de OnClick
    @Override
    public void onContactSelected(Curso curso) {
        Toast.makeText(getActivity().getApplicationContext(), "Selected: " + curso.getCodigo() + ", " + curso.getNombre(), Toast.LENGTH_LONG).show();
    }


}
