package com.example.firebase1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.firebase1.entity.UserHappy;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity4 extends AppCompatActivity {
    ImageView img_fun,img_normal, img_unFunny;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        UserHappyDatabase database1 = Room.databaseBuilder(this, UserHappyDatabase.class, "mydb")
                .allowMainThreadQueries()
                .build();
        UserHappyDAO itemDAO = database1.getItemDAO();
        UserHappy items = itemDAO.getUserById(email);

        Button btn3 = findViewById(R.id.button5);
        img_fun = findViewById(R.id.imageView13);
        img_normal = findViewById(R.id.imageView15);
        img_unFunny = findViewById(R.id.imageView14);

        img_fun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                items.setHappy(items.getHappy() + 1);
                itemDAO.update(items);
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("user").document(email).update("Funny" , items.getHappy());
                Toast.makeText(MainActivity4.this, "Funny: " + items.getHappy() +", " +
                        "Normal: " + items.getNormal() + ", " + "UnFunny: " + items.getUnhappy(),Toast.LENGTH_SHORT
                ).show();
            }
        });

        img_normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                items.setNormal(items.getNormal() + 1);
                itemDAO.update(items);
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("user").document(email).update("Normal" , items.getNormal());
                Toast.makeText(MainActivity4.this, "Funny: " + items.getHappy() +", " +
                        "Normal: " + items.getNormal() + ", " + "UnFunny: " + items.getUnhappy(),Toast.LENGTH_SHORT
                ).show();
            }
        });

        img_unFunny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                items.setUnhappy(items.getUnhappy() + 1);
                itemDAO.update(items);
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("user").document(email).update("UnFunny" , items.getUnhappy());
                Toast.makeText(MainActivity4.this, "Funny: " + items.getHappy() +", " +
                        "Normal: " + items.getNormal() + ", " + "UnFunny: " + items.getUnhappy(),Toast.LENGTH_SHORT
                ).show();
            }
        });


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