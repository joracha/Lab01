package com.example.lab01.ui.cursos;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab01.Logica.Curso;
import com.example.lab01.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class CursoFragment extends Fragment implements CursoAdapter.CursoAdapterListener {

    private static String textoBuscado;
    private CursoViewModel cursoViewModel;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private FloatingActionButton fab;
    private CursoAdapter adapter;
    private SearchView searchView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

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
        adapter = new CursoAdapter(this);

        // Asignando el adapter
        recyclerView.setAdapter(adapter);

        // Si llega solicitud de crear nuevo curso...
        if (getActivity().getIntent().getSerializableExtra("accion") != null) {

            Curso nuevo_curso = (Curso) getActivity().getIntent().getSerializableExtra("cursonuevo"); // Ya sea nuevo o uno actualizado

            if (getActivity().getIntent().getSerializableExtra("accion").equals("creado")) {
                adapter.getModel().agregarCurso(nuevo_curso);
                adapter.notifyItemInserted(adapter.getItemCount());
                Toast.makeText(getActivity().getApplicationContext(), "Curso insertado correctamente", Toast.LENGTH_SHORT).show();
            } else if (getActivity().getIntent().getSerializableExtra("accion").equals("actualizado")) {
                int pos = adapter.getModel().editarCurso(nuevo_curso);
                adapter.notifyItemChanged(pos);
                Toast.makeText(getActivity().getApplicationContext(), "Curso modificado correctamente", Toast.LENGTH_SHORT).show();
            }

            getActivity().getIntent().removeExtra("cursonuevo");
            getActivity().getIntent().removeExtra("accion");
        }

        // FILTRO
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        this.searchView = (SearchView) root.findViewById(R.id.busqueda);
        this.searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getActivity().getComponentName()));
        this.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                adapter.getFilter().filter(query);
                return false;
            }
        });

        return root;
    }

    public void crearCurso(View view) {
        Intent intent = new Intent(getActivity(), CursoCrear.class);
        intent.putExtra("accion", "crear");
        startActivity(intent);
    }


    @Override
    public void onContactSelected(Curso curso) {

    }

    @Override
    public void eliminar(final int pos) {
        final Curso curso = adapter.getModel().getCursosFiltrados().get(pos);
        final int pos2 = adapter.getModel().getCursos().indexOf(curso);
        adapter.getModel().eliminarCurso(curso);
        adapter.notifyItemRemoved(pos);
        Snackbar.make(recyclerView, curso.getNombre(), Snackbar.LENGTH_LONG).setAction("Deshacer", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.getModel().getCursos().add(pos2, curso);
                if (adapter.getModel().getCursos() != adapter.getModel().getCursosFiltrados())
                    adapter.getModel().getCursosFiltrados().add(pos, curso);
                adapter.notifyItemInserted(pos);
                adapter.notifyDataSetChanged();
            }
        }).show();
    }

    @Override
    public void editar(int pos) {
        final Curso curso = adapter.getModel().getCursosFiltrados().get(pos);
        Intent intent = new Intent(getActivity(), CursoCrear.class);
        intent.putExtra("accion", "editar");
        intent.putExtra("curso_a_editar", curso);
        startActivity(intent);
    }

    @Override
    public void onPause() {
        super.onPause();
        // Guarda el estado de la barra de busqueda
        textoBuscado = this.searchView.getQuery().toString();
    }

    @Override
    public void onResume() {
        super.onResume();
        // Solucion a bug de que al presionar atras se queda el movimiento pegado
        searchView.setQuery(textoBuscado, false);
        adapter.notifyDataSetChanged();
    }
}
