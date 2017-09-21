package as.founder.demo.wang.gestureDetector;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * Created by zhiwei.wang on 2016/12/8.
 */

public class PracticeImageView extends ImageView {

    public static String TAG = "OnGestureListener";
    private PointF startPoint = new PointF();//移动前，手指按下的位置
    private Matrix matrix = new Matrix();
    private Matrix currentMatrix = new Matrix();//目前所在位置
    private final float MIN_DISTANCE = 25f;
    int lastX, lastY;
    private float startDist;
    private PointF midPoint;//两个手指间的中间点
    private int screenWidth;
    private int screenHeight;
    //private float scaleTimes = 1f;

    public PracticeImageView(Context context) {
        super(context);
    }

    public PracticeImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PracticeImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {


        DisplayMetrics dm = getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                lastY = (int) event.getRawY();
                lastX = (int) event.getRawX();
                currentMatrix.set(matrix);//当前的缩放matrix
                startPoint.set(event.getX(), event.getY());
                break;

            case MotionEvent.ACTION_POINTER_DOWN:
                startDist = distance(event);//取两个手指之间的距离
                if (startDist > MIN_DISTANCE) {
                    currentMatrix.set(matrix);//保存当前的缩放结果
                    midPoint = midPoint(event);
                }

                break;

            case MotionEvent.ACTION_MOVE:
                if (event.getPointerCount() > 1) {

                    float dist = distance(event);//取两个手指之间的距离
                    if (dist > MIN_DISTANCE) {

                        float scale = dist / startDist;
                        matrix.set(currentMatrix);//在上次缩放结果的基础上进行缩放

                        Log.i(TAG,midPoint.x+"---------"+midPoint.y);
                        float p[] = new float[9];
                        matrix.getValues(p);

                        if (p[0] < 1f) {
                            //Log.d("", "当前缩放级别:"+p[0]+",最小缩放级别:"+minScaleR);
                            matrix.setScale(1f, 1f, midPoint.x, midPoint.y);
                            Log.i(TAG,"1f::::::"+midPoint.x+"---------"+midPoint.y);
                        }
                        if (p[0] > 4f) {
                            //Log.d("", "当前缩放级别:"+p[0]+",最大缩放级别:"+MAX_SCALE);
                            matrix.set(currentMatrix);
                            Log.i(TAG,"4f::::::"+midPoint.x+"---------"+midPoint.y);
                        }
                        if(p[0] <= 4f && p[0]>=1f) {

                            matrix.postScale(scale, scale, midPoint.x, midPoint.y);
                        }
                    }

                } else {

                    int dx = (int) event.getRawX() - lastX;
                    int dy = (int) event.getRawY() - lastY;

                    int left = this.getLeft() + dx;
                    int top = this.getTop() + dy;
                    int right = this.getRight() + dx;
                    int bottom = this.getBottom() + dy;
//                     // 设置不能出界
                    if (left < 0) {
                        left = 0;
                        right = left + this.getWidth();
                    }

                    if (right > screenWidth) {
                        right = screenWidth;
                        left = right - this.getWidth();
                    }

                    if (top < 0) {
                        top = 0;
                        bottom = top + this.getHeight();
                    }

                    if (bottom > screenHeight) {
                        bottom = screenHeight;
                        top = bottom - this.getHeight();
                    }
                    matrix.postTranslate(dx, dy);
                    //   this.layout(left, top, right, bottom);

                    lastX = (int) event.getRawX();
                    lastY = (int) event.getRawY();
                }

                break;
            case MotionEvent.ACTION_POINTER_UP:


                break;
            case MotionEvent.ACTION_UP:


                break;

        }

        setImageMatrix(matrix);

        return true;
    }

//    /**
//     * 限制最大最小缩放比例，自动居中
//     */
//    private void CheckView() {
//        float p[] = new float[9];
//        matrix.getValues(p);
//
//            if (p[0] < 1f) {
//                //Log.d("", "当前缩放级别:"+p[0]+",最小缩放级别:"+minScaleR);
//                matrix.setScale(minScaleR, minScaleR);
//            }
//            if (p[0] > 4f) {
//                //Log.d("", "当前缩放级别:"+p[0]+",最大缩放级别:"+MAX_SCALE);
//                matrix.set(savedMatrix);
//            }
//
//
//    }


    /**
     * 求两点的距离
     *
     * @param event
     * @return
     */
    private float distance(MotionEvent event) {
        float dx = event.getX(1) - event.getX(0);
        float dy = event.getY(1) - event.getY(0);
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * 计算两点之间的中间点
     *
     * @param event
     * @return
     */
    private PointF midPoint(MotionEvent event) {
        float x = (event.getX(1) + event.getX(0)) / 2;
        float y = (event.getY(1) + event.getY(0)) / 2;
        return new PointF(x, y);
    }



}
