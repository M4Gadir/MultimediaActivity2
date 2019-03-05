package com.magad.multimediaactivity2;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoStreaming extends AppCompatActivity {

    @BindView(R.id.vstream)
    VideoView vstream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_video_streaming);
        ButterKnife.bind(this);


    }

    @Override
    protected void onStart() {
        super.onStart();
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("loading");
        dialog.setMessage("mohon tunggu");
        dialog.setIndeterminate(false);
        dialog.setCancelable(false);
        dialog.show();

        MediaController controller = new MediaController(this);
        controller.setAnchorView(vstream);
        vstream.setMediaController(controller);

        String url = "http://dedykuncoro.com/childrens-song/uploads/videos/japanese_childrens_song_-_okina_kuri_no_ki_no_shita_de.mp4";
        Uri uri_url = Uri.parse(url);
        vstream.setVideoURI(uri_url);
        vstream.requestFocus();

        vstream.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                dialog.dismiss();
                mp.start();
            }
        });
    }
}
