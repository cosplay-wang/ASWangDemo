package as.founder.demo.wang.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import as.founder.demo.wang.R;
import as.founder.demo.wang.videorecorder.RecordVideoActivity;

public class MediaRecorderActivity extends AppCompatActivity implements View.OnClickListener{
    Button bt_startRecordSound,bt_endRecordSound,bt_startRecordVideo,bt_play_audio,bt_pause_audio;
    boolean isRecord;
    MediaPlayer  player;
    MediaRecorder mediaRecorder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_recorder);
        initLayout();
    }
    void initLayout(){
        bt_startRecordSound = (Button) findViewById(R.id.start_record_sound);
        bt_endRecordSound = (Button) findViewById(R.id.end_record_sound);
        bt_startRecordVideo = (Button) findViewById(R.id.start_record_video);
        bt_play_audio = (Button) findViewById(R.id.play_record_sound);
        bt_pause_audio = (Button) findViewById(R.id.pause_record_sound);

        bt_startRecordSound.setOnClickListener(this);
        bt_endRecordSound.setOnClickListener(this);
        bt_startRecordVideo.setOnClickListener(this);
        bt_play_audio.setOnClickListener(this);
        bt_pause_audio.setOnClickListener(this);
        player = new MediaPlayer();


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.start_record_sound:
            {
                startRecordSound();
                break;
            }
            case R.id.end_record_sound:
            {
                endRecordSound();
                break;
            }
            case R.id.start_record_video:
            {
                Intent intet = new Intent(MediaRecorderActivity.this, RecordVideoActivity.class);
                startActivity(intet);
                break;
            }
            case R.id.play_record_sound:
            {
                playRecordAudio();
                break;
            }
            case R.id.pause_record_sound:
            {
                playPauseOrGoOn();
                break;
            }

            default:
                break;
        }
    }
    private void startRecordSound(){

        File soundFile = new File("/sdcard/sound.amr");
        if(soundFile.exists()){
            soundFile.delete();
        }
        mediaRecorder = new MediaRecorder();
        // 设置音频录入源
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        // 设置录制音频的输出格式
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        // 设置音频的编码格式
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        // 设置录制音频文件输出文件路径
        mediaRecorder.setOutputFile(soundFile.getAbsolutePath());

        mediaRecorder.setOnErrorListener(new MediaRecorder.OnErrorListener() {
            @Override
            public void onError(MediaRecorder mr, int what, int extra) {
                // 发生错误，停止录制
                mediaRecorder.stop();
                mediaRecorder.release();
                isRecord = false;
                Toast.makeText(MediaRecorderActivity.this, "录音发生错误", 0).show();
            }
        });
        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        isRecord = true;
        mediaRecorder.start();
        Toast.makeText(MediaRecorderActivity.this, "开始录音", 0).show();

    }
    private void endRecordSound(){
        if(isRecord){
            // 停止录制
            mediaRecorder.stop();
            mediaRecorder.release();
            isRecord = false;
            Toast.makeText(MediaRecorderActivity.this, "录音结束", 0).show();
        }

    }
    private void playRecordAudio(){


        String  path   =  "/sdcard/tt.mp3";
        File soundFile = new File("/sdcard/tt.mp3");
        if(!soundFile.exists()){
            Toast.makeText(MediaRecorderActivity.this, "没有录音", 0).show();
        }else {

            try {
                player.setDataSource(path);
                player.prepare();
                player.start();
                bt_pause_audio.setEnabled(true);
                player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        bt_pause_audio.setEnabled(false);
                        Toast.makeText(MediaRecorderActivity.this, "播放结束", 10).show();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    private void playPauseOrGoOn(){

        if(bt_pause_audio.getText().equals("暂停")){
            player.pause();
            bt_pause_audio.setText("继续");
        }else if (bt_pause_audio.getText().equals("继续")){
            player.start();
            bt_pause_audio.setText("暂停");
        }




    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(isRecord) {
            mediaRecorder.stop();
            mediaRecorder.release();
            isRecord = false;
        }
    }
}
