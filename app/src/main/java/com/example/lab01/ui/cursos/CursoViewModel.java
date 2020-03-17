package com.example.lab01.ui.cursos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lab01.AccesoDatos.ModelData;
import com.example.lab01.Logica.Curso;

import java.util.ArrayList;

public class CursoViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private ArrayList<Curso> cursos;


    public CursoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Este es el apartado de Cursos");
        this.cursos = ModelData.getInstance().getCursoList();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public ArrayList<Curso> listarCursos() {
        return cursos;
    }
}