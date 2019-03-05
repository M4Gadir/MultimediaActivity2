package com.magad.multimediaactivity2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Audio(View view) {
        startActivity(new Intent(MainActivity.this, AudioActivity.class));
    }

    public void Audiosederhana(View view) {
        startActivity(new Intent(MainActivity.this, AudioSederhanaActivity.class));
    }

    public void videos(View view) {
        startActivity(new Intent(MainActivity.this, VideoActivity.class));
    }

    public void straemaudio(View view) {
        startActivity(new Intent(MainActivity.this, AudioStreaming.class));
    }

    public void straemvideo(View view) {
        startActivity(new Intent(MainActivity.this, VideoStreaming.class));
    }
}
