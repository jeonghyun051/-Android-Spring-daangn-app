package com.cos.daangnpost2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private Context mContext = MainActivity.this;
    private Button btn1;
    private FloatingActionButton fab1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.btn_1);
        fab1 = findViewById(R.id.fab_1);

        fab1.setOnClickListener(v ->{
            Intent intent = new Intent(mContext, WritingActivty.class);
            startActivity(intent);
        });

        btn1.setOnClickListener(v ->{
            Intent intent = new Intent(mContext, WritingActivty.class);
            startActivity(intent);
        });
    }
}