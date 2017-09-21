package as.founder.demo.wang.gestureDetector;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * Created by zhiwei.wang on 2016/12/8.
 */

public class MoveImageView extends ImageView {
    Context context;
    public MoveImageView(Context context) {
        super(context);
        this.context = context;
    }

    public MoveImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MoveImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MoveImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    int lastX, lastY;
    private int screenWidth;
    private int screenHeight;



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels - 50;
        // TODO Auto-generated method stub

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
              //  lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
              //  int dx = (int) event.getRawX() - lastX;
                int dy = (int) event.getRawY() - lastY;

             //   int left = this.getLeft() + dx;
                int top = this.getTop() + dy;
             //   int right = this.getRight() + dx;
                int bottom = this.getBottom() + dy;
//                // 设置不能出界
//                if (left < 0) {
//                    left = 0;
//                    right = left + this.getWidth();
//                }
//
//                if (right > screenWidth) {
//                    right = screenWidth;
//                    left = right - this.getWidth();
//                }
//
//                if (top < 0) {
//                    top = 0;
//                    bottom = top + this.getHeight();
//                }
//
//                if (bottom > screenHeight) {
//                    bottom = screenHeight;
//                    top = bottom - this.getHeight();
//                }

                this.layout(this.getLeft(), top, this.getRight(), bottom);

                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();

                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;

    }

//    @Override
//    protected void onDraw(Canvas canvas) {
//        // TODO Auto-generated method stub
//        super.onDraw(canvas);
//
//        float r = (float) Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2)
//                * (y1 - y2)) / 2;
//        r = 100 >= r ? 100 : r;
//
//        Paint paint = new Paint();
//        paint.setColor(Color.RED);
//        canvas.drawCircle(x1, y1, r, paint);
//    }

}
