package com.example.quizfirebase2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {
    TextView login;
    EditText txt_fullname,txt_username,txt_email,txt_password,txt_phonenumber;
    Button btn_register;
    FirebaseAuth firebaseAuth;
    DocumentReference documentReference;
    FirebaseFirestore firebaseFirestore;
    private static final String TAG = "SampleActivity";
    int flag=1;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        txt_fullname = findViewById(R.id.txtName);
        txt_email = findViewById(R.id.txtEmail);
        txt_password = findViewById(R.id.txtPassword);
        txt_phonenumber = findViewById(R.id.txtPhonenumber);
        btn_register = findViewById(R.id.btnRegister);
        login = findViewById(R.id.lnkLogin);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

//        if(firebaseAuth.getCurrentUser() != null)
//        {
//            Intent intent = new Intent(Signup.this,Quiz.class);
//            startActivity(intent);
//            finish();
//        }

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String fullname = txt_fullname.getText().toString();
                final String phonenumber = txt_phonenumber.getText().toString();
                final String email = txt_email.getText().toString().trim();
                final String password = txt_password.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    txt_email.setError("Enter email");
                }
                if (TextUtils.isEmpty(password)) {
                    txt_password.setError("Enter Password");
                }

                if (TextUtils.isEmpty(fullname)) {
                    txt_fullname.setError("Enter Fullname");
                }
                if (TextUtils.isEmpty(phonenumber) && phonenumber.length() == 10) {
                    txt_phonenumber.setError("Enter Mobile Number");
                }

                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(Signup.this, "Submitted data", Toast.LENGTH_SHORT).show();
                            userId = firebaseAuth.getInstance().getCurrentUser().getUid();
                            documentReference = firebaseFirestore.collection("users").document(userId);
                            Map<String,Object> user =new HashMap<>();
                            user.put("Name",fullname);
                            user.put("email",email);
                            user.put("phone",phonenumber);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onSuccess: "+userId);
                                }
                            });

                            Intent intent = new Intent(Signup.this,Login.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(Signup.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
             }
        });
    }

    public void redirecttologin(View view)
    {
        Intent intent =new Intent(Signup.this,Login.class);
        startActivity(intent);
    }


}