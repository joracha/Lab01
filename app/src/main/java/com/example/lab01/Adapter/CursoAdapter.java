package com.example.lab01.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.lab01.Logica.Curso;
import com.example.lab01.R;

import java.util.ArrayList;
import java.util.List;

public class CursoAdapter extends RecyclerView.Adapter<CursoAdapter.MyViewHolder> {

    private List<Curso> listaCursos; // Lista de cursos
    private List<Curso> listaCursosFiltrada; // Lista de cursos que se mostrara al presentar filtros
    private CursoAdapterListener listener;  // Para eventos
    private Curso deletedItem;
    private Curso selectedItem;


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public MyViewHolder(TextView v) {
            super(v);
            textView = v;
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onContactSelected(listaCursos.get(getAdapterPosition()));
                }
            });
        }
    }

    public CursoAdapter(ArrayList<Curso> cursos, CursoAdapterListener listener) {
        listaCursos = cursos;
        this.listener = listener;
        this.listaCursosFiltrada = listaCursos;
    }

    @Override
    public CursoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_cursos, parent, false);
        v.setFocusable(true);

        v.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText(listaCursos.get(position).getNombre());
    }

    @Override
    public int getItemCount() {
        return listaCursos.size();
    }

    public interface CursoAdapterListener {
        void onContactSelected(Curso curso);
    }

}