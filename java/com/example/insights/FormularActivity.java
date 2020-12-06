package com.example.insights;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class FormularActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText edidName, editAge, editGoal;
    private ImageView imageViewFormular;
     Button submitButton;
     FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private Spinner mInterest, mProductive, mMotivated;
    private static final String memberS = "members";
    private String name, age, goal, interest, productive, motivated;
     Member member;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth firebaseAuth;
    private StorageReference storageReference;
    private FirebaseStorage firebaseStorage;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.formular);


        edidName = findViewById(R.id.editname);
        editAge = findViewById(R.id.editage);
        editGoal = findViewById(R.id.editgoal);
        imageViewFormular = findViewById(R.id.imageViewFormular);
        submitButton = findViewById(R.id.buttonnsubmit1);
        mInterest = findViewById(R.id.spinner);
        mProductive = findViewById(R.id.spinner1);
        mMotivated = findViewById(R.id.spinner2);




        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.numbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mInterest.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.numbers1, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mProductive.setAdapter(adapter1);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.numbers2, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mMotivated.setAdapter(adapter2);

        //spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        member = new Member();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("user");

                //insert data into firebase database
                    name = edidName.getText().toString();
                    age = editAge.getText().toString();
                    goal = editGoal.getText().toString();
                    interest = mInterest.getAdapter().toString();
                    productive = mProductive.getAdapter().toString();
                    motivated = mMotivated.getAdapter().toString();
                    member.setName(name);
                    member.setAge(age);
                    member.setGoal(goal);
                    member.setInterest(interest);
                    member.setProductive(productive);
                    member.setMotivated(motivated);
                    mDatabase.push().setValue(member);

        FirebaseUser member = FirebaseAuth.getInstance().getCurrentUser();






        configure();

    }






    private void configure() {
        Button one = (Button) findViewById(R.id.buttonnsubmit1);
        one.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(FormularActivity.this, MeniuActivity.class));
            }
        });
    }


}