package com.example.lab01.ui.cursos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab01.Logica.Curso;
import com.example.lab01.R;

import java.util.ArrayList;


public class CursoAdapter extends RecyclerView.Adapter<CursoAdapter.MyViewHolder> {
    private ArrayList<Curso> listaCursos;
    private CursoAdapterListener listener;  // Para eventos

    class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView codigo;
        public TextView nombre;
        public TextView creditos;
        public TextView horas;

        MyViewHolder(View v) {
            super(v);
            codigo = v.findViewById(R.id.codigo_curso);
            nombre = v.findViewById(R.id.nombre_curso);
            creditos = v.findViewById(R.id.creditos_curso);
            horas = v.findViewById(R.id.horas_curso);

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
    }

    @NonNull
    @Override
    public CursoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CursoAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_curso, parent, false));
    }

    @Override
    public void onBindViewHolder(CursoAdapter.MyViewHolder holder, int position) {
        holder.nombre.setText(listaCursos.get(position).getNombre());
        holder.codigo.setText("NRC: " + listaCursos.get(position).getCodigo());
        holder.creditos.setText(listaCursos.get(position).getCreditos() + " creditos");
        holder.horas.setText(listaCursos.get(position).getHoras() + " horas");
    }

    @Override
    public int getItemCount() {
        return listaCursos.size();
    }

    public interface CursoAdapterListener {
        void onContactSelected(Curso curso);
    }
}