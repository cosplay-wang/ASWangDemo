package as.founder.demo.wang.yuezhitouch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhiwei.wang on 2016/10/19.
 * 先生_不靠谱
 */

public class TouchView extends View {


    List<MotionElement> pointDounbleList = new ArrayList<>();
    List<MotionElement> pointList = new ArrayList<>();


    float doubleDistance = 30;
    float moreMoveDistance = 50;

    boolean isSingleMove = true;
    boolean isSlide = true;
    boolean isScale = true;


    private float startDiatance = 0f;
    private float moveingDiatance = 0f;


    private float mLastFocusX;
    private float mLastFocusY;


    public TouchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TouchView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        super.setOnClickListener(l);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int pointSize = event.getPointerCount();
        float sumX = 0f;
        float sumY = 0f;
        float focusX = 0f;
        float focusY = 0f;

        for(int i=0;i<pointSize-1;i++){
            sumX = event.getX(i)+sumX;
            sumY = event.getY(i)+sumY;
        }
        focusX = sumX / pointSize;
        focusY = sumY / pointSize;


        switch (event.getActionMasked()) {//event.getAction() & MotionEvent.ACTION_MASK
            case MotionEvent.ACTION_DOWN:
                /**
                 * 判断双击事件
                 */
                if (pointDounbleList.size() == 0) {
                    pointDounbleList.add(new MotionElement(event.getAction(), System.currentTimeMillis(), event.getX(0), event.getY(0), 0));
                } else {
                    if ((System.currentTimeMillis() - pointDounbleList.get(0).motionCickTime < 300) &&
                            (Math.abs(event.getX() - pointDounbleList.get(0).motionx)) < doubleDistance &&
                            (Math.abs(event.getY() - pointDounbleList.get(0).motiony) < doubleDistance)) {
                        Log.i("touchWANG", "双击事件");
                        pointDounbleList.clear();
                    } else {
                        pointDounbleList.clear();
                        pointDounbleList.add(new MotionElement(event.getAction(), System.currentTimeMillis(), event.getX(0), event.getY(0), 0));
                    }
                }


                mLastFocusX = focusX;
                mLastFocusY = focusY;
                pointList.add(new MotionElement(event.getAction(), System.currentTimeMillis(), event.getX(0), event.getY(0), 0));
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                isSingleMove = false;
                startDiatance = distance(event);
                mLastFocusX = focusX;
                mLastFocusY = focusY;
                break;
            case MotionEvent.ACTION_POINTER_UP:
                mLastFocusX = focusX;
                mLastFocusY = focusY;
                if (event.getPointerCount() >= 2) {


                    moveingDiatance = distance(event);
                    if (moveingDiatance > startDiatance + moreMoveDistance) {
                         if(isScale) {
                             Log.i("touchWANG", "多指缩放");
                         }
                    }

                }
                isScale = false;
                isSingleMove = false;
                isSlide = false;
                break;
            case MotionEvent.ACTION_MOVE:
                if (event.getPointerCount() == 1) {
                    if (Math.abs(event.getX(0) - pointList.get(0).motionx) > 100 && isSingleMove) {
                        isSingleMove = false;
                        Log.i("touchWANG", "单指滑动");

                    }
                } else {

                    final float scrollX = mLastFocusX - focusX;
                    final float scrollY = mLastFocusY - focusY;

                    moveingDiatance = distance(event);

                    if (moveingDiatance < startDiatance + doubleDistance && moveingDiatance > startDiatance - doubleDistance && isSlide && Math.sqrt(scrollX*scrollX+scrollY*scrollY)>50) {
                        Log.i("touchWANG", "多指滑动");
                        isSlide = false;
                        isScale = false;


                    }

                }

                break;
            case MotionEvent.ACTION_UP:
                pointList.clear();
                isSingleMove = true;
                startDiatance = 0f;
                moveingDiatance = 0f;
                isSlide = true;
                isScale = true;
                break;
        }

        return true;

    }


    protected class MotionElement {
        int motionType;
        long motionCickTime;
        float motionx;
        float motiony;
        int pointerId;

        public MotionElement(int type, long time, float x, float y, int index) {
            this.motionType = type;
            this.motionCickTime = time;
            this.motionx = x;
            this.motiony = y;
            this.pointerId = index;
        }
    }

    // 计算两个触摸点之间的距离
    private float distance(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }

    // 计算两个触摸点滑动的坐标距离
    private int moveSame(MotionEvent event) {
        float x0 = event.getX(0) - pointList.get(0).motionx;
        float x1 = event.getX(1) - pointList.get(1).motionx;
        float y0 = event.getY(0) - pointList.get(0).motiony;
        float y1 = event.getY(1) - pointList.get(1).motiony;
        Log.i("touchWANG", "--x0:" + event.getX(0) + "--x001:" + pointList.get(0).motionx + "--x1:" + event.getX(1) + "--x1:" + pointList.get(1).motionx + "--");
        // Log.i("touchWANG","--x0:"+x0+"--x1:"+x1+"--y0:"+y0+"--y1:"+y1+"--");
        if ((x0 > 30 && x1 > 30) || (x0 <= -30 && x1 <= -30) || (y0 > 30 && y1 > 30) || (y0 <= -30 && y1 <= -30)) {
            return 1;

        }
//        else if (Math.abs(x0) > 30 || Math.abs(x1) > 30 || Math.abs(y0) > 30 || Math.abs(y1) > 30) {
//            return 2;
//        }
        return 2;
    }
}
