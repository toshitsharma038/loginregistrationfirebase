package com.example.quizfirebase2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    TextView register;
    Button btn_login;
    EditText emaillg,passwordlg;
   FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        register = findViewById(R.id.lnkRegister);
        btn_login =findViewById(R.id.btnLogin);
        emaillg =findViewById(R.id.txtLgemail);
        passwordlg =findViewById(R.id.txtPwd);
        firebaseAuth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Signup.class);
                startActivity(intent);

          }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emaillg.getText().toString().trim();
                String password = passwordlg.getText().toString().trim();

                if(TextUtils.isEmpty(email))
                {
                    emaillg.setError("Enter email");
                }
                if(TextUtils.isEmpty(password))
                {
                    passwordlg.setError("Enter Password");
                }

                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {

                        if(task.isSuccessful())
                        {
                            Intent intent =new Intent(Login.this,Quiz.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(Login.this, "Please enter valid credential", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
      }

      }
