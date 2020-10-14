package com.xingfeng.sxliveplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.changba.songstudio.video.ChangbaPlayerActivity;

public class MainActivity extends AppCompatActivity {

    static {
        System.loadLibrary("songstudio");
    }
    private Button forward_video_player;
    private Button live_video_player;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        forward_video_player = (Button) findViewById(R.id.forward_video_player);
        forward_video_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChangbaPlayerActivity.class);
                startActivity(intent);
            }
        });


        live_video_player = (Button) findViewById(R.id.live_video_player);
        live_video_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = "rtmp://58.200.131.2:1935/livetv/hunantv";
                ELLivePlayerController.getInstance().init(path, true);
                Intent intent = new Intent(MainActivity.this, SpeedUpFirstScreenPlayerActivity.class);
                startActivity(intent);
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 10);
        }
    }
}
