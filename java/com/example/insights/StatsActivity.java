package com.example.insights;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;



public class StatsActivity extends AppCompatActivity {
    String nume1;
    int pos;
    TextView numeBobi;
    ImageView bobiChoice;
    RatingBar health,emotional,hunger;
    ImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.stats);

        
        /*
        if(bundle!=null)
        {
            int res_image = bundle.getInt("bobi");
            bobiChoice.setImageResource(res_image);
        }*/






        Intent intent = getIntent();
        nume1 = intent.getStringExtra(CustomActivity.NumeBobibred);




        numeBobi = (TextView) findViewById(R.id.NumeBobi);
        numeBobi.setText(nume1);

       configure();
    }

    private void configure() {
        Button one = (Button) findViewById(R.id.butonNext2);
        one.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StatsActivity.this, MeniuActivity.class);
                startActivity(intent);
            }
        });
    }
}
