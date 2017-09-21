package as.founder.demo.wang.videorecorder;

import android.content.Intent;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import as.founder.demo.wang.R;
import as.founder.demo.wang.adapter.RecyclerViewAdapter;

public class RecordVideoActivity extends AppCompatActivity implements View.OnClickListener {
    private SurfaceView sv_view;
    private Button stopBT,startBT,play_video;
    private boolean isRecording;
    private MediaRecorder mediaRecorder;
    Camera camera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_video);
        initLayout();
        //startRecordVideo();
    }

    void initLayout() {
        sv_view = (SurfaceView) findViewById(R.id.camera_preview);
        stopBT = (Button) findViewById(R.id.end_record);
        startBT = (Button) findViewById(R.id.start_record);
        play_video = (Button) findViewById(R.id.play_video);

        stopBT.setOnClickListener(this);
        startBT.setOnClickListener(this);
        play_video.setOnClickListener(this);
        // 声明Surface不维护自己的缓冲区，针对Android3.0以下设备支持
       sv_view.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    void startRecordVideo() {
        File file = new File("/sdcard/video.mp4");
        if (file.exists()) {
            // 如果文件存在，删除它，演示代码保证设备上只有一个录音文件
            file.delete();
        }

        mediaRecorder = new MediaRecorder();
        mediaRecorder.reset();


        //改变保存后的视频文件播放时是否横屏(不加这句，视频文件播放的时候角度是反的)
        mediaRecorder.setOrientationHint(90);

        camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK);
        camera.setDisplayOrientation(90);
        camera.unlock();
        mediaRecorder.setCamera(camera);


        // 设置音频录入源
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        // 设置视频图像的录入源
        mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        // 设置录入媒体的输出格式
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        // 设置音频的编码格式
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        // 设置视频的编码格式
        mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
        mediaRecorder.setVideoFrameRate(50);
        mediaRecorder.setVideoEncodingBitRate(60*1920*1080);
        mediaRecorder.setVideoSize(1920, 1080);// 设置分辨率：
        // 设置录制视频文件的输出路径
        mediaRecorder.setOutputFile(file.getAbsolutePath());
        // 设置捕获视频图像的预览界面
        mediaRecorder.setPreviewDisplay(sv_view.getHolder().getSurface());

        mediaRecorder.setOnErrorListener(new MediaRecorder.OnErrorListener() {

            @Override
            public void onError(MediaRecorder mr, int what, int extra) {
                // 发生错误，停止录制
                mediaRecorder.stop();
                mediaRecorder.release();
                mediaRecorder = null;
                isRecording = false;

                Toast.makeText(RecordVideoActivity.this, "录制出错", 0).show();
            }
        });
        try {
            mediaRecorder.prepare();
            mediaRecorder.start();
            isRecording = true;
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    protected void stop() {
        if (isRecording) {
            // 如果正在录制，停止并释放资源
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
            isRecording = false;

            Toast.makeText(RecordVideoActivity.this, "停止录像，并保存文件", 0).show();
        }
    }

    @Override
    protected void onDestroy() {
        if (isRecording) {
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;

        }
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.end_record:
            {
                stop();
                break;
            }
            case R.id.start_record:
            {
                startRecordVideo();
                break;
            }
            case R.id.play_video:
            {
                Intent intent = new Intent(RecordVideoActivity.this,MediaPlayerVideoActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}
