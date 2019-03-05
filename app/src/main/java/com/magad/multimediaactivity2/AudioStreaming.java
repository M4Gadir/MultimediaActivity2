package com.magad.multimediaactivity2;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AudioStreaming extends AppCompatActivity {

    @BindView(R.id.btnplay)
    ImageButton btnplay;
    @BindView(R.id.btnStop)
    ImageButton btnStop;
    @BindView(R.id.progrepb)
    ProgressBar progrepb;

    MediaPlayer player;
    String url = "http://live2.radiorodja.com/;stream.mp3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_streaming);
        ButterKnife.bind(this);
        progrepb.setIndeterminate(false);
        progrepb.setVisibility(View.INVISIBLE);
        progrepb.setMax(100);

        btnplay.setImageResource(R.drawable.ic_play_arrow_black_24dp);
        btnStop.setImageResource(R.drawable.ic_stop_black_24dp);
    }

    private void setPlaying() {
        player = new MediaPlayer();
        try {
            player.setDataSource(url);
        } catch (IOException e) {
            e.printStackTrace();
        }catch (IllegalStateException e){
            e.printStackTrace();
        }
        try {
            player.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.start();
    }

    @OnClick({R.id.btnplay, R.id.btnStop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnplay:
                progrepb.setVisibility(View.VISIBLE);
                setPlaying();
                setProgressBarIndeterminate(true);
                try {
                    player.prepareAsync();
                }catch (IllegalStateException e){
                    e.printStackTrace();
                }
                player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        player.start();
                        progrepb.setIndeterminate(false);
                    }
                });
                btnplay.setEnabled(false);
                btnStop.setEnabled(true);
                break;
            case R.id.btnStop:
                if(player == null) return;
                if(player.isPlaying()){
                    player.stop();
                    player.release();
                    setPlaying();
                    progrepb.setVisibility(View.INVISIBLE);
                    progrepb.setIndeterminate(true);

                    btnStop.setEnabled(false);
                    btnplay.setEnabled(true);
                }
                break;
        }
    }
}
