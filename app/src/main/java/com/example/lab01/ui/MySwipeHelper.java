package com.example.lab01.ui;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab01.AccesoDatos.ModelData;
import com.example.lab01.Logica.Profesor;
import com.example.lab01.R;
import com.example.lab01.ui.profesores.Profesor.ProfesorFragment;
import com.example.lab01.ui.profesores.Profesor.ProfesorListAdapter;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class MySwipeHelper extends ItemTouchHelper.SimpleCallback {

    private RecyclerView recyclerView;
    private ProfesorListAdapter adapter;
    private Profesor aux;
    private ProfesorFragment fragment;

    public MySwipeHelper(int dragDirs, int swipeDirs, ProfesorListAdapter adapter, RecyclerView recyclerView,
            ProfesorFragment fragment) {
        super(dragDirs, swipeDirs);
        this.recyclerView = recyclerView;
        this.adapter = adapter;
        this.fragment = fragment;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder,
            @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        final int position = viewHolder.getAdapterPosition();
        aux = adapter.getmDataset().get(position);
        switch (direction) {
            case ItemTouchHelper.LEFT:
                aux = adapter.getmDataset().remove(position);
                ModelData.getInstance().getProfesorList().remove(aux);
                adapter.notifyItemRemoved(position);
                Snackbar.make(recyclerView, aux.getNombre(), Snackbar.LENGTH_LONG)
                        .setAction("Deshacer", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (aux != null) {
                                    ModelData.getInstance().getProfesorList().add(aux);
                                    adapter.getmDataset().add(position, aux);
                                    adapter.notifyItemInserted(position);
                                    aux = null;
                                }
                            }
                        }).show();
                break;
            case ItemTouchHelper.RIGHT:
                if (aux != null) {
                    fragment.moveToAgrEdiProfesorActivity(aux, position);
                }
                break;
        }
    }

    @SuppressLint("ResourceType")
    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
            @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState,
            boolean isCurrentlyActive) {
        new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                .addSwipeLeftBackgroundColor(Color.parseColor(fragment.getString(R.color.delete)))
                .addSwipeLeftActionIcon(R.drawable.ic_delete_24dp)
                .addSwipeRightBackgroundColor(Color.parseColor(fragment.getString(R.color.edit)))
                .addSwipeRightActionIcon(R.drawable.ic_edit_24dp).create().decorate();
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }
}
