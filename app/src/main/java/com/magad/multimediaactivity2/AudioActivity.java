package com.magad.multimediaactivity2;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AudioActivity extends AppCompatActivity {

    MediaPlayer player;

    @BindView(R.id.btn_Play)
    Button btnPlay;
    @BindView(R.id.btn_Stop)
    Button btnStop;
    @BindView(R.id.btn_Pause)
    Button btnPause;
    @BindView(R.id.btn_Resume)
    Button btnResume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
        ButterKnife.bind(this);

        player = new MediaPlayer();

        enable(btnPlay);
        disable(btnResume, btnStop, btnPause);
    }

    @OnClick({R.id.btn_Play, R.id.btn_Stop, R.id.btn_Pause, R.id.btn_Resume})
    public void onViewClicked(View view) {

        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.dhuha);

        switch (view.getId()) {
            case R.id.btn_Play:

                player = new MediaPlayer();
                player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                try {
                    player.setDataSource(AudioActivity.this, uri);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                }
                try {
                    player.prepare();
                } catch (IOException e) {
                    e.printStackTrace();

                }

                player.start();

                disable(btnPlay, btnResume);
                enable(btnStop, btnPause);

                break;
            case R.id.btn_Stop:
                player.stop();
                player.reset();
                player.release();
                disable(btnStop, btnPause, btnResume);
                enable(btnPlay);

                break;

            case R.id.btn_Pause:
                if (player.isPlaying() && player != null) {
                    player.pause();
                    disable(btnPause, btnPlay);
                    enable(btnStop, btnResume);
                }
                break;

            case R.id.btn_Resume:
                player.start();

                disable(btnPlay, btnResume);
                enable(btnStop, btnPause);
                break;
        }
    }

    private void enable(View... views) {
        for (View v : views) {
            v.setEnabled(true);
        }
    }

    private void disable(View... views) {
        for (View v : views) {
            v.setEnabled(false);
        }
    }
}
