package com.example.a3d;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private MyOpenGlView openGLView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        openGLView=new MyOpenGlView(this);
        setContentView(openGLView);
    }
}