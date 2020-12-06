package com.example.insights;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class TutorialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.tutorial);
        SharedPreferences preferences2 = getSharedPreferences("PREFERNCE", MODE_PRIVATE);
        String FirstTime2 = preferences2.getString("FirstTimeInstall1","");

        if(FirstTime2.equals("Yes2"))
        {
            Intent intent1 = new Intent(TutorialActivity.this, SignUpActivity.class);
            startActivity(intent1);
        }
        else{
            SharedPreferences.Editor editor = preferences2.edit();
            editor.putString("FirstTimeInstall1","Yes2");
            editor.apply();
            Intent intent2 = new Intent(TutorialActivity.this, Tutorial1Activity.class);
            startActivity(intent2);
        }
        configure();
    }

    private void configure() {
        Button one = (Button) findViewById(R.id.buttonnext1);
        one.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(TutorialActivity.this, Tutorial1Activity.class));
            }
        });
    }
}