package com.example.insights;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.budiyev.android.circularprogressbar.CircularProgressBar;

public class Timer extends AppCompatActivity {
    TextView ShowText;
    private TextView countdownText;
    private Button countdownButton;
    CircularProgressBar Progressbar;
    int progressBarValue = 100;
    Handler handler = new Handler();
    private CountDownTimer countDownTimer;
    private long timeLeftInMilliseconds = 18000; //1800000; //30 mins
    private boolean timeRunning;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer);

        countdownText = findViewById(R.id.countdown_text);
        countdownButton = findViewById(R.id.countdown_button);
        Progressbar = (CircularProgressBar)findViewById(R.id.progress_bar);
        countdownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startStop();

            }
        });

        handler = new Handler()
        {
            public void handleMessage(android.os.Message msg)
            {
                if(timeRunning)
                {
                    progressBarValue--;
                }
                Progressbar.setProgress(progressBarValue);

                handler.sendEmptyMessageDelayed(0, 600);
            }
        };

        handler.sendEmptyMessage(0);
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
                updateTimer();


            }

            @Override
            public void onFinish() {

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
        timeLeftText += ":";
        if(seconds < 10) timeLeftText += "0";
        timeLeftText += seconds;

        countdownText.setText(timeLeftText);
    }
}