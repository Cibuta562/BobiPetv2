package com.example.insights;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText mFullName, mEmail, mPassword, mPhone;
    TextView Name, Age, Goal, Interest, Motivated, Productive, Mail;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    FirebaseFirestore fStore;
    String userID;
    EditText edidName, editAge, editGoal;
    private ImageView imageViewFormular;
    Button submitButton;
    FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private String name, age, goal, interest, productive, motivated;
    Uri imageUri;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    ImageView UpdateProfilePic;


////////

    ////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        Name = findViewById(R.id.editnameprofile);
        Age = findViewById(R.id.editageprofile);
        Goal = findViewById(R.id.editgoalprofile);
        Interest = findViewById(R.id.textViewf1);
        Productive = findViewById(R.id.textViewf4);
        Motivated = findViewById(R.id.textViewf3);
        Mail = findViewById(R.id.editemailprofile);
        storageReference = FirebaseStorage.getInstance().getReference();


        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userID = fAuth.getCurrentUser().getUid();

        final DocumentReference documentReference = fStore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                Name.setText(documentSnapshot.getString("name"));
                Mail.setText(documentSnapshot.getString("email"));
                Goal.setText(documentSnapshot.getString("goal"));
                Age.setText(documentSnapshot.getString("age"));
                Motivated.setText(documentSnapshot.getString("motivated"));
                Interest.setText(documentSnapshot.getString("interest"));
                Productive.setText(documentSnapshot.getString("productive"));

            }
        });

        configure();



    }

    private void configure () {
        Button one = (Button) findViewById(R.id.butonNext);
        one.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, StatsActivity.class);
                startActivity(intent);
            }
        });
    }
}