package com.magad.multimediaactivity2;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.VideoView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VideoActivity extends AppCompatActivity {

    @BindView(R.id.video_view)
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);

        Uri alamatVideo = Uri.parse("android.resource://" + getPackageName() +"/"+ R.raw.tikal);
        MediaController controller = new MediaController(this);
        videoView.setMediaController(controller);
        videoView.setVideoURI(alamatVideo);
        videoView.requestFocus();
        videoView.start();
    }

    @OnClick(R.id.video_view)
    public void onViewClicked() {

    }
}
