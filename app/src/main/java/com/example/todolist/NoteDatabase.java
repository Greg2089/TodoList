package com.example.todolist;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    private static final String DB_NAME = "notes.db";

    private static NoteDatabase instance = null;

    public static NoteDatabase getInstance(Application application) {

        if (instance == null) {
            instance = Room.databaseBuilder(
                    application, NoteDatabase.class, DB_NAME
            ).build();
        }
        return instance;
    }

    public abstract NotesDao notesDao();

}
