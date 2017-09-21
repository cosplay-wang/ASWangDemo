package as.founder.demo.wang.custometextview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.TextView;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * Created by zhiwei.wang on 2017/9/20.
 */

public class MarQueeTextView extends TextView {
    int moveDisR = 0;
    int moveDisB ;
    String text;
    Paint mPaint;
    Paint mPaintR;
    int windowWidth =0;
    int windowHeight =0;
    int textWidth = 0;
    boolean canvasRed = true;
    boolean canvasBlack = false;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

                if(moveDisR - 800 <= -textWidth) {
                    moveDisB = windowWidth;
                    canvasBlack = true;
                    if(moveDisR<=-textWidth){
                        canvasRed = false;
                    }
                    if(moveDisB <= -textWidth){
                        canvasBlack = false;
                    }
                }
                if(moveDisB - 800 <= -textWidth) {
                    moveDisR = windowWidth;
                    canvasRed = true;
                    if(moveDisR<=-textWidth){
                        canvasRed = false;
                    }
                    if(moveDisB <= -textWidth){
                        canvasBlack = false;
                    }
                }

                moveDisR = moveDisR - 100;
                moveDisB = moveDisB - 100;
                invalidate();
            }
    };
    public MarQueeTextView(Context context) {
        super(context);
    }

    public MarQueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        text = getText().toString();
        mPaintR = new Paint();
        mPaint = new Paint();
        mPaintR.setColor(Color.RED);
        mPaint.setColor(Color.BLACK);
        mPaintR.setTextSize(50);
        mPaint.setTextSize(50);
        dp2px(context);
        textWidth = (int)mPaint.measureText(text);
        moveDisB = windowWidth;
    }

    public MarQueeTextView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
            if(canvasRed){
                canvas.drawText(text,moveDisR,100,mPaintR);
            }
            if(canvasBlack){
                canvas.drawText(text,moveDisB,100,mPaint);
            }

        if(textWidth>windowWidth){
            handler.sendEmptyMessageDelayed(1,200);
        }



    }
    private void dp2px(Context context){
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        windowWidth = metrics.widthPixels;
    }
}
