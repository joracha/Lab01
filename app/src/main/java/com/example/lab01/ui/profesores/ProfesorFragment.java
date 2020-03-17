package com.example.lab01.ui.profesores;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab01.AccesoDatos.ModelData;
import com.example.lab01.R;

public class ProfesorFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfesorViewModel profesorViewModel = ViewModelProviders.of(this).get(ProfesorViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profesores, container, false);
        super.onCreate(savedInstanceState);
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.list_profesor);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        layoutManager.setSmoothScrollbarEnabled(true);

        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation()));

        recyclerView.setLayoutManager(layoutManager);

        ProfesorListAdapter adapter = new ProfesorListAdapter(ModelData.getInstance().getProfesorList());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        return root;
    }
}
