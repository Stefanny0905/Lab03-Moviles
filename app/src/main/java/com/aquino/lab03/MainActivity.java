package com.aquino.lab03;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        videoView = (VideoView) findViewById(R.id.videoview);
        videoView.setMediaController(new MediaController(this));
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.vid_pizza));
        videoView.start();


    }

    public void ordenar(View view) {

        Intent intent = new Intent(this, OrdenActivity.class);
        startActivity(intent);
    }
}
