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
    private ArrayList<Curso> cursosFiltrados;

    public CursoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Este es el apartado de Cursos");
        this.cursos = ModelData.getInstance().getCursoList();
        this.cursosFiltrados = this.cursos;
    }

    public LiveData<String> getText() {
        return mText;
    }

    public ArrayList<Curso> getCursos() {
        return cursos;
    }

    public ArrayList<Curso> getCursosFiltrados() {
        return cursosFiltrados;
    }


    public void setCursosFiltrados(ArrayList<Curso> cursos) {
        this.cursosFiltrados = cursos;
    }

    public boolean agregarCurso(Curso curso) {
        cursos.add(curso);
        return true;
    }


    public boolean eliminarCurso(Curso c) {
        cursos.remove(c);
        cursosFiltrados.remove(c);
        return true;
    }

    public int editarCurso(Curso curso) {
        int index = 0;
        for (Curso curso_ite : cursosFiltrados) {
            if (curso_ite.getCodigo().trim().toLowerCase().equals(curso.getCodigo().trim().toLowerCase())) {
                cursos.remove(index);
                cursos.add(index, curso);
                return index;
            }
            index++;
        }
        return -1;
    }
}
