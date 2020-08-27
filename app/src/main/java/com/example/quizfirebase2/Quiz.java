package com.example.quizfirebase2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class Quiz extends AppCompatActivity {
    private static final String TAG = "Quiz";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
    }
    @Override
    public void onBackPressed() {
        // super.onBackPressed();
     //   Toast.makeText(Quiz.this,"There is no back action",Toast.LENGTH_LONG).show();
        Log.d(TAG, "onBackPressed: "+"hello");
        return;
    }
}