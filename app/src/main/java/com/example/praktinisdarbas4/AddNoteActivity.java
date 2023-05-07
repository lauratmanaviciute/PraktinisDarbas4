package com.example.praktinisdarbas4;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class AddNoteActivity extends AppCompatActivity {
    private EditText txtNoteName;
    private EditText txtNoteContent;
    private TextView tvWordCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        txtNoteName = findViewById(R.id.txtNoteName);
        txtNoteName.setInputType(524288);
        txtNoteContent = findViewById(R.id.txtNoteContent);
        txtNoteContent.setInputType(524288);
        tvWordCounter = findViewById(R.id.tvWordCounter);

        txtNoteContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {
                String text = txtNoteContent.getText().toString();
                text = text.replace("\n", " ");
                String[] textArray = text.split("\\s+");
                tvWordCounter.setText("Words: " + textArray.length);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void onAddNoteClick(View view) {
        String noteName = txtNoteName.getText().toString();
        String noteContent = txtNoteContent.getText().toString();

        if (txtNoteName.getText().toString().isEmpty() || txtNoteContent.getText().toString().isEmpty()) {
            Toast.makeText(this, "Note name or content is empty", Toast.LENGTH_SHORT).show();
            return;
        }

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