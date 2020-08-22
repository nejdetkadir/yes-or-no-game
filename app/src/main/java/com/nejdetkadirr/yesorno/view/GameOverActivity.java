package com.nejdetkadirr.yesorno.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.nejdetkadirr.yesorno.R;

public class GameOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game_over);
        Intent intent = getIntent();
        String scoreString = intent.getStringExtra("score");
        TextView textView = findViewById(R.id.gameoverScoreTextView);
        textView.setText(scoreString);
        ImageView imageView = findViewById(R.id.gameOverReplayImageView);
        TextView textView1 = findViewById(R.id.gameOverReplayTextView);

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),InfoActivity.class);
                startActivity(intent);
                finish();
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),InfoActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
