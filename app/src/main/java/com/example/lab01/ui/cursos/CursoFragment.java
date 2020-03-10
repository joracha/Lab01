package com.example.lab01.ui.cursos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.lab01.R;

public class CursoFragment extends Fragment {

    private CursoViewModel cursoViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        cursoViewModel =
                ViewModelProviders.of(this).get(CursoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_cursos, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        cursoViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
