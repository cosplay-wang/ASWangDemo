package as.founder.demo.wang.videorecorder;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.IOException;

import as.founder.demo.wang.R;

public class MediaPlayerVideoActivity extends Activity implements View.OnClickListener{
    SurfaceView surfaceView;
    Button button_play,button_pause,button_stop,button_replay;
    SeekBar seekBar;
    MediaPlayer player;
    int currentPosition;
    boolean isPlaying;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player_video);
        initLayout();
    }
    void initLayout(){
        surfaceView = (SurfaceView) findViewById(R.id.sv);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        button_play = (Button) findViewById(R.id.button_play);
        button_pause = (Button) findViewById(R.id.button_pause);
        button_stop = (Button) findViewById(R.id.button_stop);
        button_replay = (Button) findViewById(R.id.button_replay);

        button_play.setOnClickListener(this);
        button_pause.setOnClickListener(this);
        button_stop.setOnClickListener(this);
        button_replay.setOnClickListener(this);
        player = new MediaPlayer();

        surfaceView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);//4.0一下的版本需要加该段代码。

        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                /**
                 * 当点击手机上home键（或其他使SurfaceView视图消失的键）时，调用该方法，获取到当前视频的播放值，currentPosition。
                 * 并停止播放。
                 */
                currentPosition = player.getCurrentPosition();
                stopVideo();
            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                /**
                 * 当重新回到该视频应当视图的时候，调用该方法，获取到currentPosition，并从该currentPosition开始继续播放。
                 */
                if (currentPosition > 0) {
                    startVideo(currentPosition);
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height) {

            }
        });


    }
    void startVideo(final int currentPosition) {

        String path = "/sdcard/video.mp4";//指定视频所在路径。
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);//设置视频流类型
        player.setDisplay(surfaceView.getHolder());
        try {
            player.setDataSource(path);
            player.prepareAsync();
            player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    player.start();
                    int max = player.getDuration();
                    seekBar.setMax(max);
                    player.seekTo(currentPosition);

                    new Thread() {

                        public void run() {
                            isPlaying = true;
                            while (isPlaying) {
                                int position = player.getCurrentPosition();
                                seekBar.setProgress(position);
                                try {
                                    Thread.sleep(500);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }

                        }

                        ;
                    }.start();
                }

            });
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    Toast.makeText(getApplicationContext(), "视频播放完成", Toast.LENGTH_LONG).show();
                }
            });
            player.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    Toast.makeText(getApplicationContext(), "视频播放出错", Toast.LENGTH_LONG).show();
                    return false;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    void pauseVideo(){
        if (player.isPlaying()) {
            player.pause();
        } else {
            player.start();
        }
    }
    void stopVideo(){
        if (player.isPlaying()) {
            player.stop();
        }
    }
    void replayVideo(){
        if (player.isPlaying()) {
            startVideo(0);
        } else {
            startVideo(0);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_play:
            {
                startVideo(0);
                break;
            }
            case R.id.button_pause:
            {
                pauseVideo();
                break;
            }
            case R.id.button_stop:
            {
                stopVideo();
                break;
            }
            case R.id.button_replay:
            {
                replayVideo();
                break;
            }
        }
    }
}
