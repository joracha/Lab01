package com.example.lab01.ui.profesores;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProfesorViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ProfesorViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Este es el apartado de profesores");
    }

    public LiveData<String> getText() {
        return mText;
    }
}