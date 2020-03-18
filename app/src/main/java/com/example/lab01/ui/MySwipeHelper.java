package com.example.lab01.ui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab01.Logica.Profesor;
import com.example.lab01.R;
import com.example.lab01.ui.profesores.ProfesorListAdapter;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class MySwipeHelper extends ItemTouchHelper.SimpleCallback {

    private RecyclerView recyclerView;
    private ProfesorListAdapter adapter;
    private ArrayList<Profesor> teachers;
    private Profesor aux;

    public MySwipeHelper(int dragDirs, int swipeDirs, ProfesorListAdapter adapter, ArrayList<Profesor> teachers, RecyclerView recyclerView) {
        super(dragDirs, swipeDirs);
        this.recyclerView = recyclerView;
        this.adapter = adapter;
        this.teachers = teachers;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        final int position = viewHolder.getAdapterPosition();
        switch (direction) {
            case ItemTouchHelper.LEFT:
                aux = teachers.get(position);
                teachers.remove(position);
                adapter.notifyItemRemoved(position);
                Snackbar.make(recyclerView, aux.getNombre(), Snackbar.LENGTH_LONG).setAction("Deshacer", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        teachers.add(position, aux);
                        adapter.notifyItemInserted(position);
                    }
                }).show();
                break;
            case ItemTouchHelper.RIGHT:
                break;
        }
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                .addSwipeLeftBackgroundColor(Color.parseColor("#ED5147"))
                .addSwipeLeftActionIcon(R.drawable.ic_delete_24dp)
                .addSwipeRightBackgroundColor(Color.parseColor("#00B2CA"))
                .addSwipeRightActionIcon(R.drawable.ic_edit_24dp)
                .create()
                .decorate();
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }
}
