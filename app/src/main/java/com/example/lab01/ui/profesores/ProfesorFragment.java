package com.example.lab01.ui.profesores;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab01.AccesoDatos.ModelData;
import com.example.lab01.R;

public class ProfesorFragment extends Fragment {

    private ProfesorViewModel profesorViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profesorViewModel =
                ViewModelProviders.of(this).get(ProfesorViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profesores, container, false);
        super.onCreate(savedInstanceState);
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.list_profesor);
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        // specify an adapter (see also next example)
        RecyclerView.Adapter mAdapter = new ProfesorListAdapter(ModelData.getInstance().getProfesorList());
        recyclerView.setAdapter(mAdapter);
        return root;
    }
}
