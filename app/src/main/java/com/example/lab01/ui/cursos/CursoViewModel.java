package com.example.lab01.ui.cursos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CursoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CursoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Este es el apartado de Cursos");
    }

    public LiveData<String> getText() {
        return mText;
    }
}