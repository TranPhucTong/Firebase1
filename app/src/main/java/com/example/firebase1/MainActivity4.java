package com.example.firebase1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        Button btn3 = findViewById(R.id.button5);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity4.this, MainActivity.class));
                Toast.makeText(getApplicationContext(), "Đã thoát !!!",Toast.LENGTH_SHORT).show();

            }
        });

//        Button btn4 = findViewById(R.id.button4);
//        btn4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(MainActivity4.this, MainActivity.class));
//                Toast.makeText(getApplicationContext(), "Đã thoát !!!",Toast.LENGTH_SHORT).show();
//
//            }
//        });
    }
}