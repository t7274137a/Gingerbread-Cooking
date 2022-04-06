package com.example.gingerbreadcooking.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.session.MediaController;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.VideoView;

import com.example.gingerbreadcooking.LoginActivity;
import com.example.gingerbreadcooking.R;
import com.example.gingerbreadcooking.Splash;

public class VideoActivity extends AppCompatActivity {
         VideoView video1,video2,video3,video4;
    MediaController mediaController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        video1=findViewById(R.id.video1);
        video2=findViewById(R.id.video2);
        video3=findViewById(R.id.video3);
        video4=findViewById(R.id.video4);
        video1.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video1));
        video2.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video2));
        video3.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video3));
        video4.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video4));
        video1.start();
        video2.start();
        video4.start();
        video3.start();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run(){
                video1.pause();
                video2.pause();
                video4.pause();
                video3.pause();
            }
        }, 700);
    }


    public void startVideo1(View view) {
            video1.start();
            video2.pause();
            video3.pause();
            video4.pause();
    }
    public void startVideo3(View view) {
        video3.start();
        video2.pause();
        video1.pause();
        video4.pause();
    }
    public void startVideo4(View view) {
        video4.start();
        video1.pause();
        video3.pause();
        video2.pause();
    }
    public void startVideo2(View view) {
        video2.start();
        video1.pause();
        video3.pause();
        video4.pause();
    }
}