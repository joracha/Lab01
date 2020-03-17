package com.example.lab01.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
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

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;

        public MyViewHolder(TextView v) {
            super(v);
            textView = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CursoAdapter(ArrayList<Curso> cursos) {
        listaCursos = cursos;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CursoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        // create a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lista_cursos, parent, false);

        v.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView.setText(listaCursos.get(position).getNombre());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return listaCursos.size();
    }

    public interface CursoAdapterListener {
        void onContactSelected(Curso curso);
    }
}