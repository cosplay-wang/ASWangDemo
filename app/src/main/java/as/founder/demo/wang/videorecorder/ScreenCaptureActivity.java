package as.founder.demo.wang.videorecorder;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import as.founder.demo.wang.R;

public class ScreenCaptureActivity extends AppCompatActivity {
    MediaProjectionManager projectionManager;
    MediaProjection projection;
    VirtualDisplay virtualDisplay;
    ScreenCaptureService screenService;
    Surface mSurface;
    int mScreenDensity;
    int REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_capture);
        initLayout();
    }
    Button startbutton;
    SurfaceView surfaceview;
    void initLayout(){
        startbutton = (Button) findViewById(R.id.start_record_screen);
        projectionManager = (MediaProjectionManager) getSystemService(MEDIA_PROJECTION_SERVICE);
        surfaceview = (SurfaceView) findViewById(R.id.surfaceview);
        mSurface = surfaceview.getHolder().getSurface();
        startbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (screenService.isRunning()) {
                    screenService.stopRecord();
                    startbutton.setText("开始录制");
                } else {
                    Intent captureIntent = projectionManager.createScreenCaptureIntent();
                    startActivityForResult(captureIntent, REQUEST_CODE);

                }
            }
        });
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        mScreenDensity = metrics.densityDpi;
        Intent intent = new Intent(ScreenCaptureActivity.this, ScreenCaptureService.class);
        bindService(intent, connection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            projection = projectionManager.getMediaProjection(resultCode,data);
           // createVirtualDisplay();
            screenService.setMediaProject(projection);
            screenService.startRecord();
            startbutton.setText("结束录制");
        }

    }
    private void createVirtualDisplay() {
        virtualDisplay = projection.createVirtualDisplay(
                "MainScreen",
                surfaceview.getWidth(), surfaceview.getHeight(), mScreenDensity,
                DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR,
                mSurface,
                null, null);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);
            ScreenCaptureService.ScreenCaptureServiceBinder binder = (ScreenCaptureService.ScreenCaptureServiceBinder) service;
            screenService = binder.getScreenCaptureServiceBinder();
            screenService.setConfig(metrics.widthPixels, metrics.heightPixels, metrics.densityDpi);
//            startBtn.setEnabled(true);
            startbutton.setText(screenService.isRunning() ? "停止录制" : "开始录制");
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {}
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }

}
