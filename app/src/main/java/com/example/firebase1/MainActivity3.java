package com.example.firebase1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firebase1.entity.UserHappy;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity3 extends AppCompatActivity {
    private EditText emailedit, passedit, name1,passedit1;
    private Button btnRe;
    private TextView txt4;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        mAuth=FirebaseAuth.getInstance();

        emailedit = findViewById(R.id.email1);
        passedit= findViewById(R.id.pass1);
        name1 = findViewById(R.id.name);
        passedit1= findViewById(R.id.pass2);
        btnRe = findViewById(R.id.button3);
        txt4 = findViewById(R.id.textView4);

        btnRe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

        txt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity3.this, MainActivity2.class));

            }
        });
    }

    private void register() {
        String email, pass,pass2,name;
        email = emailedit.getText().toString().trim();
        pass = passedit.getText().toString().trim();
        pass2 = passedit1.getText().toString().trim();
        name =  name1.getText().toString().trim();

        if (TextUtils.isEmpty(name)){
            Toast.makeText(this, "Vui lòng nhập tên!!!",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Vui lòng nhập email!!!",Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(pass)){
            Toast.makeText(this, "Vui lòng nhập password!!!",Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(pass2)){
            Toast.makeText(this, "Vui lòng xác nhận lại password!!!",Toast.LENGTH_SHORT).show();
            return;
        }



        mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(MainActivity3.this, "Authentication failed." + task.getException(),
                            Toast.LENGTH_SHORT).show();
                } else {
                    UserHappyDatabase database1 = Room.databaseBuilder(MainActivity3.this, UserHappyDatabase.class, "mydb")
                            .allowMainThreadQueries()
                            .build();
                    UserHappyDAO itemDAO = database1.getItemDAO();
                    UserHappy user = new UserHappy();
                    user.setEmail(email);
                    user.setName(name);
                    user.setNormal(0);
                    user.setPassword(pass);
                    user.setUnhappy(0);
                    user.setHappy(0);
                    long tam = 0;
                    if(itemDAO.getUsers().size()>0){
                        for(int i=0; i<itemDAO.getUsers().size();i++){
                            if(tam<itemDAO.getUsers().get(i).getId())
                                tam = itemDAO.getUsers().get(i).getId();
                            else
                                tam = tam;
                        }
                        long id = tam +1;
                        user.setId(id);
                    }
                    else {
                        user.setId(tam);
                    }
                    itemDAO.insert(user);

                    //firestore
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    Map<String,Object> status = new HashMap<>();
                    status.put("name",name);
                    status.put("email",email);
                    status.put("password",pass);
                    status.put("Funny",0);
                    status.put("Normal",0);
                    status.put("UnFunny",0);
                    db.collection("user").document(email).set(status).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) { if(task.isSuccessful()){
                            Toast.makeText(MainActivity3.this, "Register success",Toast.LENGTH_SHORT
                            ).show();
                        }
                        }
                    });
                    startActivity(new Intent(MainActivity3.this, MainActivity2.class));
                    finish();
                }
            }
        });
    }
}