package com.example.firebase1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity3 extends AppCompatActivity {
    private EditText emailedit, passedit, name1,passedit1;
    private Button btnRe;
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

        btnRe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    private void register() {
        String email, pass,pass2,name;
        email = emailedit.getText().toString();
        pass = passedit.getText().toString();
        pass2 = passedit1.getText().toString();
        name =  name1.getText().toString();

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
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Tạo tài khoản thành công",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity3.this, MainActivity4.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Tạo tài khoản không thành công",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}