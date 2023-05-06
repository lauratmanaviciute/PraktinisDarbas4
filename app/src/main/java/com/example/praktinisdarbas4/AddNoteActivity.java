package com.example.praktinisdarbas4;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class AddNoteActivity extends AppCompatActivity {
    private EditText txtNoteName;
    private EditText txtNoteContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        txtNoteName = findViewById(R.id.txtNoteName);
        txtNoteName.setInputType(524288);
        txtNoteContent = findViewById(R.id.txtNoteContent);
        txtNoteContent.setInputType(524288);
    }

    public void onAddNoteClick(View view) {
        String noteName = txtNoteName.getText().toString();
        String noteContent = txtNoteContent.getText().toString();

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor spEd = sp.edit();

        Set<String> oldSet = sp.getStringSet("notes", new HashSet<String>());
        Set<String> newSet = new HashSet<String>();

        String newNote = noteName + "\n" + noteContent;
        newSet.add(newNote);
        newSet.addAll(oldSet);

        spEd.putStringSet("notes", newSet);
        spEd.apply();

        finish();
    }
}