package com.magad.multimediaactivity2;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AudioSederhanaActivity extends AppCompatActivity {

    @BindView(R.id.playing)
    ImageButton playing;
    @BindView(R.id.stopping)
    ImageButton stopping;

    MediaPlayer player;
    private int mStatePlayerButton;
    String mStatePlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_sederhana);
        ButterKnife.bind(this);
        mStatePlayerButton = android.R.drawable.ic_media_play;
        mStatePlayer = "PLAY";
    }

    @OnClick({R.id.playing, R.id.stopping})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.playing:


                if(mStatePlayerButton == android.R.drawable.ic_media_play && mStatePlayer.equals(("PLAY"))) {
                    player = new MediaPlayer();
                    mStatePlayer = "PAUSE";
                    player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    playing.setImageResource(android.R.drawable.ic_media_pause);
                    Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.dhuha);

                    mStatePlayerButton = android.R.drawable.ic_media_pause;

                    try {
                        player.setDataSource(AudioSederhanaActivity.this, uri);
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
                }else if (mStatePlayerButton == android.R.drawable.ic_media_pause && mStatePlayer.equals("PAUSE")){
                    mStatePlayerButton = android.R.drawable.ic_media_play;
                    mStatePlayer = "RESUME";
                    playing.setImageResource(android.R.drawable.ic_media_play);
                    if(player.isPlaying()){
                        player.pause();
                    }

                }else if (mStatePlayerButton == android.R.drawable.ic_media_play && mStatePlayer.equals("RESUME")){
                    mStatePlayerButton = android.R.drawable.ic_media_pause;
                    playing.setImageResource(android.R.drawable.ic_media_pause);
                    mStatePlayer = "PAUSE";
                    player.start();
                }
                    break;
                    case R.id.stopping:
                        mStatePlayerButton = android.R.drawable.ic_media_play;
                        mStatePlayer = "PLAY";
                        playing.setImageResource(android.R.drawable.ic_media_play);
                        player.stop();
                        break;
        }
    }
}
