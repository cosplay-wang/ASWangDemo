package as.founder.demo.wang.ijkplayer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.io.IOException;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * Created by zhiwei.wang on 2018/1/9.
 * wechat 760560322
 * 作用：
 */

public class IjkLayout extends FrameLayout {
    SurfaceView surfaceView;
    IMediaPlayer iMediaPlayer = null;
    String path = "";
    Context context;
    boolean isCreateSurface = false;
    VideoPlayerListener listener;

    public IjkLayout(@NonNull Context context) {
        this(context, null);
    }

    public IjkLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
    }

    public IjkLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs);
    }

    void createSurfaceView() {
        surfaceView = new SurfaceView(context);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, Gravity.CENTER);
        surfaceView.setLayoutParams(layoutParams);
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                load();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });
        addView(surfaceView);
        isCreateSurface = true;
    }

    public void setVideoPath(String videoPath) {
        if (!isCreateSurface) {
            createSurfaceView();
        }
        path = videoPath;
        load();
    }

    /**
     * 加载视频
     */
    private void load() {
        //每次都要重新创建IMediaPlayer
        createPlayer();
        try {
            iMediaPlayer.setDataSource(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //给mediaPlayer设置视图
        iMediaPlayer.setDisplay(surfaceView.getHolder());
        iMediaPlayer.prepareAsync();
    }



    private void createPlayer() {
        if (iMediaPlayer != null) {
            iMediaPlayer.stop();
            iMediaPlayer.setDisplay(null);
            iMediaPlayer.release();
        }
        IjkMediaPlayer ijkMediaPlayer = new IjkMediaPlayer();
        ijkMediaPlayer.native_setLogLevel(IjkMediaPlayer.IJK_LOG_DEBUG);

        //开启硬解码
        // ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec", 1);

        iMediaPlayer = ijkMediaPlayer;
        if (listener != null) {
            iMediaPlayer.setOnPreparedListener(listener);
            iMediaPlayer.setOnInfoListener(listener);
            iMediaPlayer.setOnSeekCompleteListener(listener);
            iMediaPlayer.setOnBufferingUpdateListener(listener);
            iMediaPlayer.setOnErrorListener(listener);


        }

    }

    public void setListener(VideoPlayerListener listener) {
        this.listener = listener;
        if (iMediaPlayer != null) {
            iMediaPlayer.setOnPreparedListener(listener);
        }
    }

    /**
     * -------======--------- 下面封装了一下控制视频的方法
     */

    public void start() {
        if (iMediaPlayer != null) {
            iMediaPlayer.start();
        }
    }

    public void release() {
        if (iMediaPlayer != null) {
            iMediaPlayer.reset();
            iMediaPlayer.release();
            iMediaPlayer = null;
        }
    }

    public void pause() {
        if (iMediaPlayer != null) {
            iMediaPlayer.pause();
        }
    }

    public void stop() {
        if (iMediaPlayer != null) {
            iMediaPlayer.stop();
        }
    }


    public void reset() {
        if (iMediaPlayer != null) {
            iMediaPlayer.reset();
        }
    }


    public long getDuration() {
        if (iMediaPlayer != null) {
            return iMediaPlayer.getDuration();
        } else {
            return 0;
        }
    }


    public long getCurrentPosition() {
        if (iMediaPlayer != null) {
            return iMediaPlayer.getCurrentPosition();
        } else {
            return 0;
        }
    }


    public void seekTo(long l) {
        if (iMediaPlayer != null) {
            iMediaPlayer.seekTo(l);
        }
    }




}
