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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText mFullName,mEmail,mPassword,mPhone;
    Button mRegisterBtn, schimba;
    TextView mLoginBtn, fullName, emailplayer;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    FirebaseFirestore fStore;
    String userID;
    EditText edidName, editAge, editGoal;
    private ImageView imageViewFormular;
    Button submitButton;
    FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private Spinner mInterest, mProductive, mMotivated;
    private static final String memberS = "members";
    private String name, age, goal, interest, productive, motivated;
    Member member;
    private ImageView updateProfilePic;
    private static int PICK_IMAGE = 123;
    Uri imageUri;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    ImageView UpdateProfilePic;
    private final int CODE_IMG_GALLERY = 1;
    private static final int PERMISSION_CODE = 1001;
    private static final int IMAGE_PICK_CODE =1000;


////////

////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        //mFullName   = findViewById(R.id.fullname);
        mEmail      = findViewById(R.id.editemail1);
        mPassword   = findViewById(R.id.editpassword1);
        //mPhone      = findViewById(R.id.phone);
        mRegisterBtn= findViewById(R.id.buttonsignup);
        mLoginBtn   = findViewById(R.id.textViewsignup);
       // schimba = findViewById(R.id.switch_pic);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        updateProfilePic = findViewById(R.id.imageViewFormular);
       // progressBar = findViewById(R.id.progressBar);
      //  fullName = findViewById(R.id.textplayer);
       // emailplayer = findViewById(R.id.textplayermail);



        edidName = findViewById(R.id.editname);
        editAge = findViewById(R.id.editage);
        editGoal = findViewById(R.id.editgoal);
        mInterest = findViewById(R.id.spinner);
        mProductive = findViewById(R.id.spinner1);
        mMotivated = findViewById(R.id.spinner2);
        storageReference = FirebaseStorage.getInstance().getReference();



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





/*
        schimba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                try {
                    if (ActivityCompat.checkSelfPermission(EditProfileActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(EditProfileActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PICK_FROM_GALLERY);
                    } else {
                        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(galleryIntent, PICK_FROM_GALLERY);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

*/
        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MeniuActivity.class));
            finish();
        }



        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interest = mInterest.getSelectedItem().toString();
                int spinner_pos = mInterest.getSelectedItemPosition();
                String[] size_values = getResources().getStringArray(R.array.numbers);
                final String interest = (size_values[spinner_pos]);
                productive = mProductive.getSelectedItem().toString();
                int spinner_pos1 = mProductive.getSelectedItemPosition();
                String[] size_values1 = getResources().getStringArray(R.array.numbers1);
                final String productive = (size_values1[spinner_pos1]);
                motivated = mMotivated.getSelectedItem().toString();
                int spinner_pos2 = mMotivated.getSelectedItemPosition();
                String[] size_values2 = getResources().getStringArray(R.array.numbers2);
                final String motivated = (size_values2[spinner_pos2]);
                final String email = mEmail.getText().toString();
                name = edidName.getText().toString();
                age = editAge.getText().toString();
                goal = editGoal.getText().toString();
                String password = mPassword.getText().toString();
                //final String fullName = mFullName.getText().toString();

                mLoginBtn.setEnabled(false);
                //final String phone    = mPhone.getText().toString();

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is Required.");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password is Required.");
                    return;
                }

                if(password.length() < 6){
                    mPassword.setError("Password Must be >= 6 Characters");
                    return;
                }

               // progressBar.setVisibility(View.VISIBLE);

                // register the user in firebase

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            Map<String,Object> user = new HashMap<>();
                           // user.put("fName",fullName);
                            user.put("email",email);
                            user.put("name",name);
                            user.put("age",age);
                            user.put("interest",interest);
                            user.put("productive",productive);
                            user.put("motivated",motivated);
                            user.put("goal",goal);
                          /*  member.setName(name);
                            member.setAge(age);
                            member.setGoal(goal);
                            member.setInterest(interest);
                            member.setProductive(productive);
                            member.setMotivated(motivated);
                            */




                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onSucces user profile is created for" + userID);
                                }
                            })
                                    .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure: " + e);
                                }
                            });


                            startActivity(new Intent(getApplicationContext(),MeniuActivity.class));

                        }else {
                            Toast.makeText(SignUpActivity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
/////////////

///////////////


        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });



    }



    private void pickImageFromGallery()
    {
        Intent intent = new Intent (Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImageFromGallery();
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode==RESULT_OK && requestCode == IMAGE_PICK_CODE)
        {
            imageViewFormular.setImageURI(data.getData());
        }
    }

   /*@Override
    protected void onActivityResult(int requestCode, int resultCode,@Nullable Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == CODE_IMG_GALLERY && resultCode == RESULT_OK)
        {
            imageUri = data.getData();
            if(imageUri!=null){
                imageViewFormular.setImageURI(imageUri);
            }
        }
    }*/

    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
    }
}