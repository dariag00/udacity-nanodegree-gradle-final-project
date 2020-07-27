package com.example.jokeactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    private TextView jokeView;

    public String JOKE_ID = "joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        jokeView = findViewById(R.id.tv_joke);

        String joke = getIntent().getStringExtra(JOKE_ID);
        jokeView.setText(joke);
    }
}