package com.example.lab01.ui.cursos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.example.lab01.AccesoDatos.ModelData;
import com.example.lab01.Adapter.CursoAdapter;
import com.example.lab01.Logica.Curso;
import com.example.lab01.R;

import java.util.ArrayList;
import java.util.List;

public class CursoFragment extends Fragment implements CursoAdapter.CursoAdapterListener {

    private CursoViewModel cursoViewModel;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        ModelData datos = ModelData.getInstance();
        ArrayList<Curso> cursos = datos.getCursoList();

        cursoViewModel = ViewModelProviders.of(this).get(CursoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_cursos, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.lista_cursos);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        // specify an adapter
        CursoAdapter adapter = new CursoAdapter(cursos, this);

        recyclerView.setAdapter(adapter);

        return root;
    }

    @Override
    public void onContactSelected(Curso curso) {
        Toast.makeText(getActivity().getApplicationContext(), "Selected: " + curso.getCodigo() + ", " + curso.getNombre(), Toast.LENGTH_LONG).show();
    }
}
