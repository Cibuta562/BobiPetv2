package com.example.insights;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.view.View.OnClickListener;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CustomActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    public static final String NumeBobibred = "com.example.insights.example.NumeBobibred";
   // public static final String Pos  = "com.example.insights.example.Pos";
    TextView fNickname;
    ProgressBar progressBar;
    FirebaseFirestore fStore;
    String userID,nume;
    FirebaseAuth fAuth;
    EditText numeBobi;
    ViewPager viewPager;
    ImageAdapter adapter;
    ImageView imgBobi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.custom_zone1);



        ViewPager viewPager = findViewById(R.id.viewPager);
        adapter = new ImageAdapter(this);
        viewPager.setAdapter(adapter);



        configure();
    }






    private void configure() {
        Button one = (Button) findViewById(R.id.buttonnext);
        one.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                numeBobi = findViewById(R.id.autoCompleteTextViewnickname);
                nume = numeBobi.getText().toString();
                Intent intent = new Intent(CustomActivity.this, StatsActivity.class);
                intent.putExtra(NumeBobibred, nume);
                        startActivity(intent);
            }
        });
    }
}
