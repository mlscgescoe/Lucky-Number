package com.example.luckynumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn;
    TextView txt;
    EditText eText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        eText = findViewById(R.id.editText);
        txt = findViewById(R.id.textVewH);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_name = eText.getText().toString();

                if (!user_name.trim().isEmpty()) {
                    // Valid name, proceed with generating and sharing the lucky number
                    Intent i = new Intent(getApplicationContext(), LuckyNumberActivity.class);
                    i.putExtra("name", user_name);
                    startActivity(i);
                } else {
                    // Show an error message if the name is empty
                    Toast.makeText(MainActivity.this, "Please enter a valid name", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}