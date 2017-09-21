package as.founder.demo.wang.surfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by zhiwei.wang on 2016/9/18.
 * 先生_不靠谱
 */
public class FSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    SurfaceHolder holder;
    uiThread uithread;
    public FSurfaceView(Context context) {
        this(context,null);

    }

    public FSurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        holder = this.getHolder();
        holder.addCallback(this);
        uithread = new uiThread(holder);
    }

    /**
     * 程序可以在该函数中做些和绘制界面相关的初始化工作，
     * 一般情况下都是在另外的线程来绘制界面，
     * 所以不要在这个函数中绘制Surface。
     * @param holder
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        uithread.start();
        uithread.isRun = true;

    }

    /**
     * 当Surface的状态（大小和格式）发生变化的时候会调用该函数，
     * 在surfaceCreated调用后该函数至少会被调用一次。
     * @param holder
     * @param format
     * @param width
     * @param height
     */
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    /**
     * 当Surface被摧毁前会调用该函数，
     * 该函数被调用后就不能继续使用Surface了，
     * 一般在该函数中来清理使用的资源。
     * @param holder
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        uithread.isRun = false;
    }
    class uiThread extends Thread{
        SurfaceHolder holder;
        boolean isRun;


        Paint paint = new Paint();
        public uiThread(SurfaceHolder holder){
            this.holder = holder;
            isRun = true;
        }

        @Override
        public void run() {
            int count = 0;
            super.run();
            while (isRun){
                Canvas canvas = null;
                synchronized (holder) {

                    canvas = holder.lockCanvas();
                    paint.setColor(Color.WHITE);
                   // paint.setTextSize(35);

                   // canvas.drawText("这是第" + "(count++)        秒", 500, 300, paint);
                    canvas.drawRect(50,50,500,500,paint);

                }
                if(canvas!=null){
                    holder.unlockCanvasAndPost(canvas);
                }


            }
        }
    }
}
