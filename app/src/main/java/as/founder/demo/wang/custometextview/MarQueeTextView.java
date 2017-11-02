package as.founder.demo.wang.custometextview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;

/**
 * Created by zhiwei.wang on 2017/9/20.
 */

public class MarQueeTextView extends android.support.v7.widget.AppCompatTextView {
    int moveDisR = 0;
    String text;
    Paint mPaint;
    int windowWidth = 0;
    String space = "         ";
    int textWidth = 0;
    boolean isScroll = false;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if ((-moveDisR) >= (int) mPaint.measureText(getText() + space)) {
                moveDisR = 0;
            }
            moveDisR = moveDisR - 2;
            invalidate();
        }
    };

    public MarQueeTextView(Context context) {
        super(context);
    }

    public MarQueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        text = getText().toString();
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(50);
        dp2px(context);
        textWidth = (int) mPaint.measureText(text);
        text = text + space + text;
        textWidth = (int) mPaint.measureText(text);
    }

    public MarQueeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawText(text, moveDisR, 100, mPaint);
        if(isScroll){
            startScroll();
        }
    }

    private void dp2px(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        windowWidth = metrics.widthPixels;
    }
    public void startScroll(){
        if (textWidth > windowWidth) {
            handler.sendEmptyMessageDelayed(1, 25);
            isScroll = true;
        }
    }
    public void stopScroll(){
        isScroll = false;
         handler.removeMessages(1);
    }
    public void resetScroll(){
        isScroll = false;
        handler.removeMessages(1);
        moveDisR = 0;
        invalidate();
    }
}
