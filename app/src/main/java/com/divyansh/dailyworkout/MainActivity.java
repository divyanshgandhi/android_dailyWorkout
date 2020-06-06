package com.divyansh.dailyworkout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button five, ten, fifteen, twentyFive;
    ImageView reset;
    TextView main;
    int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Getting resources
        main = findViewById(R.id.number);
        five = findViewById(R.id.plus_five_button);
        ten = findViewById(R.id.plus_ten_button);
        fifteen = findViewById(R.id.plus_fifteen_button);
        twentyFive = findViewById(R.id.plus_twenty_five_button);
        reset = findViewById(R.id.reset);
        setClickers();

        String prior;
        SharedPreferences sharedPref = getSharedPreferences("number", MODE_PRIVATE);
        prior = sharedPref.getString("done", "0");
        main.setText(prior);
    }

    private void setClickers(){
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increment(5);
            }
        });

        ten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increment(10);
            }
        });

        fifteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increment(15);
            }
        });

        twentyFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increment(25);
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
    }

    private void increment(int num){
        String current = (String) main.getText();
        number = Integer.parseInt(current);
        number += num;
        current = String.valueOf(number);
        main.setText(current);

        SharedPreferences sharedPref = this.getSharedPreferences("number", 0);
        SharedPreferences.Editor prefEditor = sharedPref.edit();
        prefEditor.putString("done", current);
        prefEditor.apply();
    }

    private void reset(){
        number = 0;
        String current = String.valueOf(number);
        main.setText(current);

        SharedPreferences sharedPref = this.getSharedPreferences("number", 0);
        SharedPreferences.Editor prefEditor = sharedPref.edit();
        prefEditor.putString("done", current);
        prefEditor.apply();
    }
}