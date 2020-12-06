package com.example.insights;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.insights.Notes;
import com.example.insights.R;

import java.util.HashSet;

public class NoteEditorActivity extends AppCompatActivity {

    int noteId;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);

        EditText editText= (EditText) findViewById(R.id.editText);

        Intent intent=getIntent();
        noteId =intent.getIntExtra("noteId", -1);

        if (noteId != -1) {
            editText.setText(Notes.notes.get(noteId));
        }else  {

            Notes.notes.add("");
            noteId = Notes.notes.size() -1;
            Notes.arrayAdapter.notifyDataSetChanged();

        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {

                Notes.notes.set(noteId, String.valueOf(s));
                Notes.arrayAdapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("com.example.notes", Context.MODE_PRIVATE);

                HashSet<String> set=new HashSet (Notes.notes);

                sharedPreferences.edit().putStringSet("notes", set).apply();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}