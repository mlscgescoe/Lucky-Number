package com.example.luckynumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class LuckyNumberActivity extends AppCompatActivity {

    Button share_btn;
    TextView welcomeTxt, luckyNumberTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lucky_number);

        welcomeTxt = findViewById(R.id.welcome_txt);
        luckyNumberTxt = findViewById(R.id.lucky_number_txt);
        share_btn = findViewById(R.id.share_number_btn);

        // username
        Intent i = getIntent();
        String user_name = i.getStringExtra("name");

        // random number generator
        generateRandomNumberWithDelay();

        share_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentRandomNum = Integer.parseInt(luckyNumberTxt.getText().toString());
                shareData(user_name, currentRandomNum);
            }
        });

        Toast.makeText(this, "User Name: " + user_name, Toast.LENGTH_LONG).show();
    }

    public int generateRandomNumber() {
        Random random = new Random();
        int upperLimit = 1000;

        // generates random number form 0 to 999
        int randomNum = random.nextInt(upperLimit);
        return  randomNum;
    }

    public void generateRandomNumberWithDelay() {
        showProgressBar();
        // handler is for scheduling
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                hideProgressBar();
                int randomNum = generateRandomNumber();
                luckyNumberTxt.setText(String.valueOf(randomNum));
                luckyNumberTxt.setVisibility(View.VISIBLE);
            }
        }, 2000);
    }

    public void showProgressBar() {
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
    }

    public void shareData(String userName, int randomNumber) {
        // implicit intent
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        // convert int to String
        String number = String.valueOf(randomNumber);

        i.putExtra(Intent.EXTRA_SUBJECT, userName + " got lucky today");
        i.putExtra(Intent.EXTRA_TEXT, "His lucky number is: " + number);

        startActivity(Intent.createChooser(i, "Choose a platform"));
    }
}