package com.example.insights;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.budiyev.android.circularprogressbar.CircularProgressBar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.StorageReference;


public class MeniuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String TimpExecutat = "com.example.insights.example.NumeBobibred";
    public static final String currencyGlobal = "com.example.insights.example.currencyGlobal";
    TextView ShowText;
    TextView currency;
    private TextView countdownText;
    private Button countdownButton, resetButton;
    CircularProgressBar Progressbar;
    int progressBarValue = 100;
    Handler handler = new Handler();
    private CountDownTimer countDownTimer;
    private long timeLeftInMilliseconds = 10000;//1800000; //30 mins
    private boolean timeRunning;
        DrawerLayout drawerLayout;
        NavigationView navigationView;
        Toolbar toolbar;
        Menu menu;
    boolean time = false;
    boolean cycle;
    int banuti;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    private String text;
    SharedPreferences preferencesCoins;


    TextView fullName,email,phone,verifyMsg;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;
    Button resendCode;
    Button resetPassLocal,changeProfileImage;
    ImageView profileImage;
    StorageReference storageReference;
    String nume, mail;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            this.requestWindowFeature(Window.FEATURE_NO_TITLE);
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setContentView(R.layout.meniu);
            cycle = false;
            drawerLayout = findViewById(R.id.drawer_layout);
            navigationView = findViewById(R.id.nav_view);
            toolbar = findViewById(R.id.toolbar);
            resetButton = findViewById(R.id.resetButton);
            LayoutInflater inflater = getLayoutInflater();
            setSupportActionBar(toolbar);
            Menu menu1 = navigationView.getMenu();

            // menu1.findItem(R.id.nav_logout).setVisible(false);
            // menu1.findItem(R.id.nav_profile).setVisible(false);

            navigationView.bringToFront();
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();

            navigationView.setNavigationItemSelectedListener(this);

            // View vi = inflater.inflate(R.layout.header, null);
            // TextView nn = (TextView)vi.findViewById(R.id.textplayer);
            // TextView ee = (TextView)vi.findViewById(R.id.textplayermail);

            countdownText = findViewById(R.id.countdown_textm);
            timeLeftInMilliseconds=12000;
            int minutes = (int) timeLeftInMilliseconds / 60000;
            int seconds = (int) timeLeftInMilliseconds % 60000/ 1000;
            String timeLeftText;
            timeLeftText = "" + minutes;
            if(minutes <10) timeLeftText +="0";
            timeLeftText += ":";
            if(seconds < 10) timeLeftText += "0";
            timeLeftText += seconds;


            countdownText.setText(timeLeftText);
            countdownText.setText(timeLeftText);
            countdownButton = findViewById(R.id.countdown_buttonm);
            currency = findViewById(R.id.textCurrency);
            Progressbar = (CircularProgressBar) findViewById(R.id.progress_barm);

            resetButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    reset();
                }
            });


            countdownButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startStop();

                }
            });


            handler = new Handler() {
                public void handleMessage(android.os.Message msg) {
                    if (timeRunning) {
                        progressBarValue--;
                    }
                    Progressbar.setProgress(progressBarValue);

                    handler.sendEmptyMessageDelayed(0, 18000);
                }
            };
            handler.sendEmptyMessage(0);

                loadData();
                updateViews();
            String value = currency.getText().toString();
            banuti=Integer.parseInt(value);


        }
    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TEXT, currency.getText().toString());
        editor.apply();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        text = sharedPreferences.getString(TEXT, "");
    }
    public void updateViews() {
        currency.setText(text);
    }

    public void reset() {
       if(cycle)
       {
           timeLeftInMilliseconds=12000;
           int minutes = (int) timeLeftInMilliseconds / 60000;
           int seconds = (int) timeLeftInMilliseconds % 60000/ 1000;
           String timeLeftText;
           timeLeftText = "" + minutes;
           if(minutes <10) timeLeftText +="0";
           timeLeftText += ":";
           if(seconds < 10) timeLeftText += "0";
           timeLeftText += seconds;


           countdownText.setText(timeLeftText);
           countdownText.setText(timeLeftText);
           stopTimer();
       }
    }

    public void startStop() {
        if(timeRunning) {
            stopTimer();
        } else {
            startTimer();
        }
    }


    public void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMilliseconds, 1000) {
            @Override
            public void onTick(long l) {
                timeLeftInMilliseconds = l;
                int minutes = (int) timeLeftInMilliseconds / 60000;
                int seconds = (int) timeLeftInMilliseconds % 60000/ 1000;

                String timeLeftText;

                timeLeftText = "" + minutes;
                if(minutes <10) timeLeftText +="0";
                timeLeftText += ":";
                if(seconds < 10) timeLeftText += "0";
                timeLeftText += seconds;

                countdownText.setText(timeLeftText);
                updateTimer();


            }

            @Override
            public void onFinish() {
                cycle=true;
                banuti++;
                if(cycle)
                {
                    currency.setText(String.valueOf(banuti));
                }
                saveData();
                stopTimer();
                reset();
            }
        } .start();

        countdownButton.setText("PAUSE");
        timeRunning = true;
    }

    public void stopTimer() {
        countDownTimer.cancel();
        countdownButton.setText("START");
        timeRunning = false;
    }

    public void updateTimer(){
        int minutes = (int) timeLeftInMilliseconds / 60000;
        int seconds = (int) timeLeftInMilliseconds % 60000/ 1000;

        String timeLeftText;

        timeLeftText = "" + minutes;
        if(minutes <10) timeLeftText +="0";
        timeLeftText += ":";
        if(seconds < 10) timeLeftText += "0";
        timeLeftText += seconds;

        countdownText.setText(timeLeftText);
        if(timeLeftInMilliseconds==0)
        {
            Intent intent = new Intent(MeniuActivity.this, StatsActivity.class);
            intent.putExtra(TimpExecutat, time);
            currency.setText("Maus");

        }

    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }

    }




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


        switch (menuItem.getItemId()) {


            case R.id.nav_home:
                Intent intent0 = new Intent(MeniuActivity.this, CustomActivity.class);
                startActivity(intent0);

                break;

            case R.id.nav_collection:
                SharedPreferences preferences = getSharedPreferences("PREFERNCE", MODE_PRIVATE);
            String FirstTime = preferences.getString("FirstTimeInstall","");

            if(FirstTime.equals("Yes"))
            {
                Intent intent1 = new Intent(MeniuActivity.this, ColectioNActivity.class);
                startActivity(intent1);
            }
            else{
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("FirstTimeInstall","Yes");
                editor.apply();
                Intent intent2 = new Intent(MeniuActivity.this, Tutorial5Activity.class);
                startActivity(intent2);
            }
            break;

            case R.id.nav_shop:
            SharedPreferences preferences1 = getSharedPreferences("PREFERNCE", MODE_PRIVATE);
            String FirstTime1 = preferences1.getString("FirstTimeInstall","");

            if(FirstTime1.equals("Yes"))
            {
                Intent intent1 = new Intent(MeniuActivity.this, RecyclerViewActivity.class);
                startActivity(intent1);
            }
            else{
                SharedPreferences.Editor editor = preferences1.edit();
                editor.putString("FirstTimeInstall","Yes");
                editor.apply();
                Intent intent2 = new Intent(MeniuActivity.this, RecyclerViewActivity.class);
                text = String.valueOf(currency);
                intent2.putExtra(currencyGlobal, text);
                startActivity(intent2);
            }
            break;

            case R.id.nav_quests:
                SharedPreferences preferences2 = getSharedPreferences("PREFERNCE", MODE_PRIVATE);
                String FirstTime2 = preferences2.getString("FirstTimeInstall","");

                if(FirstTime2.equals("Yes"))
                {
                    Intent intent1 = new Intent(MeniuActivity.this, QuestsActivity.class);
                    startActivity(intent1);
                }
                else{
                    SharedPreferences.Editor editor = preferences2.edit();
                    editor.putString("FirstTimeInstall","Yes");
                    editor.apply();
                    Intent intent2 = new Intent(MeniuActivity.this, Tutorial4Activity.class);
                    startActivity(intent2);
                }
                break;

            case R.id.nav_achievements:
                SharedPreferences preferences8 = getSharedPreferences("PREFERNCE", MODE_PRIVATE);
                String FirstTime8 = preferences8.getString("FirstTimeInstall","");

                if(FirstTime8.equals("Yes"))
                {
                    Intent intent8 = new Intent(MeniuActivity.this, AchivementActivity.class);
                    startActivity(intent8);
                }
                else{
                    SharedPreferences.Editor editor = preferences8.edit();
                    editor.putString("FirstTimeInstall","Yes");
                    editor.apply();
                    Intent intent8 = new Intent(MeniuActivity.this, Achivement2Activity.class);
                    startActivity(intent8);
                }
                break;


            case R.id.nav_tasks:

                    Intent intent1 = new Intent(MeniuActivity.this, ToDoActivity.class);
                    startActivity(intent1);

                break;

            case R.id.nav_logout:
                Intent intent6 = new Intent(MeniuActivity.this, LogoutActivity.class);
                startActivity(intent6);

                break;

            case R.id.nav_profile:
                SharedPreferences preferences9 = getSharedPreferences("PREFERNCE", MODE_PRIVATE);
                String FirstTime9 = preferences9.getString("FirstTimeInstall","");

                if(FirstTime9.equals("Yes"))
                {
                    Intent intent7 = new Intent(MeniuActivity.this, ProfileActivity.class);
                    startActivity(intent7);
                }
                else{
                    SharedPreferences.Editor editor = preferences9.edit();
                    editor.putString("FirstTimeInstall","Yes");
                    editor.apply();
                    Intent intent2 = new Intent(MeniuActivity.this, Stats1Activity.class);
                    startActivity(intent2);
                }

                break;





           /* case R.id.nav_login: menu.findItem(R.id.nav_logout).setVisible(true);
                menu.findItem(R.id.nav_profile).setVisible(true);
                menu.findItem(R.id.nav_login).setVisible(false);
                break;
            case R.id.nav_logout: menu.findItem(R.id.nav_logout).setVisible(false);
                menu.findItem(R.id.nav_profile).setVisible(false);
                menu.findItem(R.id.nav_login).setVisible(true);
                break;    */

        }
        drawerLayout.closeDrawer(GravityCompat.START); return true;
    }



}

