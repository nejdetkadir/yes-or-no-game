package com.nejdetkadirr.yesorno.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nejdetkadirr.yesorno.R;
import com.nejdetkadirr.yesorno.service.YesorNoAPI;
import com.nejdetkadirr.yesorno.service.YesorNoModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    CompositeDisposable compositeDisposable;
    ArrayList<YesorNoModel> yesorNoModelArrayList;
    private String baseURL = "https://api.nejdetkadirbektas.com/";
    Retrofit retrofit;
    ImageView gif,yesButton,noButton;
    ProgressBar progressBar;
    TextView score,time;
    int rightnowScore;
    Runnable runnable;
    Handler handler;
    int rightnowTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        gif = findViewById(R.id.gifImageView);
        progressBar = findViewById(R.id.progressBar);
        yesButton = findViewById(R.id.yesImageView);
        noButton = findViewById(R.id.noImageView);
        score = findViewById(R.id.scoreTextView);
        time = findViewById(R.id.timeTextView);
        score.setText("0");
        time.setText("0");
        getSingleData();
        timeMethod();
    }



    public void getSingleData() {
        yesButton.setClickable(false);
        noButton.setClickable(false);
        progressBar.setVisibility(View.VISIBLE);
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //Rx
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        YesorNoAPI yesorNoAPI = retrofit.create(YesorNoAPI.class);
        //RxJava
        compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(yesorNoAPI.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse));
    }



    private void handleResponse(List<YesorNoModel> yesornomodels) {
        yesorNoModelArrayList = new ArrayList<>(yesornomodels);
        if (yesorNoModelArrayList.size() > 0) {
            setData();
        } else {
            System.out.println("veri gelmedi");
        }
    }


    public void setData() {
        Glide.with(this).load(yesorNoModelArrayList.get(0).image).addListener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                progressBar.setVisibility(View.INVISIBLE);
                String answer = yesorNoModelArrayList.get(0).answer;
                rightnowScore = Integer.parseInt(score.getText().toString());
                yesButton.setClickable(true);
                noButton.setClickable(true);
                yesButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (answer.matches("yes")) {
                            rightnowScore += 10;
                            score.setText(""+rightnowScore);
                            getSingleData();
                        } else {
                            wrongAnsver();
                        }
                    }
                });

                noButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (answer.matches("no")) {
                            rightnowScore += 10;
                            score.setText(""+rightnowScore);
                            getSingleData();
                        } else {
                            wrongAnsver();
                        }
                    }
                });


                return false;
            }
        }).into(gif);

    }


    public void wrongAnsver() {
        rightnowScore = Integer.parseInt(score.getText().toString());
        rightnowScore-=10;
        score.setText(""+rightnowScore);
        getSingleData();
    }

    public void timeMethod() {

            handler = new Handler();
            runnable = new Runnable() {
                @Override
                public void run() {
                    if (rightnowTime > 59) {
                        handler.removeCallbacks(runnable);
                        Intent intent = new Intent(MainActivity.this,GameOverActivity.class);
                        intent.putExtra("score",score.getText().toString());
                        startActivity(intent);
                        finish();
                    } else {
                        time.setText(""+rightnowTime);
                        rightnowTime++;
                        handler.postDelayed(runnable,1000);
                    }
                }
            };
            handler.post(runnable);


    }

}
