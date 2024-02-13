package com.example.todolist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class EnterNoteViewModel extends AndroidViewModel {

    private NotesDao notesDao;
    MutableLiveData<Boolean> shouldCloseScreen = new MutableLiveData<>();

    public LiveData<Boolean> getShouldCloseScreen() {
        return shouldCloseScreen;
    }

    public EnterNoteViewModel(@NonNull Application application) {
        super(application);
        notesDao = NoteDatabase.getInstance(application).notesDao();
    }

    public void saveNote(Note note) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                notesDao.add(note);
                shouldCloseScreen.postValue(true);
            }
        });
        thread.start();
    }
}
