package com.example.lab01.ui.profesores;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.lab01.R;

import java.util.ArrayList;

public class ProfesorFragment extends Fragment {

    private ProfesorViewModel profesorViewModel;
    public static ArrayList<String> mobileArray = new ArrayList<String>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profesorViewModel =
                ViewModelProviders.of(this).get(ProfesorViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profesores, container, false);


       ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(),
                R.layout.lista_profes, mobileArray);

       mobileArray.add("Prueba");
        ListView listView = (ListView) root.findViewById(R.id.lista_profes);
        listView.setAdapter(adapter);



        return root;
    }
}
