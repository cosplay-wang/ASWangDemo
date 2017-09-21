package as.founder.demo.wang.customerreaderimageview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * Created by zhiwei.wang on 2016/11/23.
 * 先生_不靠谱
 */

public class CustomerImagview extends ImageView {

    private PointF startPoint = new PointF();//移动前，手指按下的位置
    private Matrix matrix = new Matrix();
    private Matrix currentMatrix = new Matrix();//目前所在位置
    private static final int NONE = 0;
    private static final int DRAG = 1;
    private static final int ZOOM = 2;
    private final float MIN_DISTANCE = 25f;
    private int type = NONE;
    private float startDist;
    private float lastDist;
    private PointF midPoint;//两个手指间的中间点

//	private ImageView mView = null;//将要进行手势缩放的view,为了防止内存泄露，这里改用局部变量

    public static boolean mIsZooming = false;//缩放时，有一个手指离开时就认为是缩放的一个阶段，进行重绘。此时依然不能翻页滑动
    public static boolean mZoomDealing = false;//缩放时，全部手指离开才认为是缩放完全结束

    public CustomerImagview(Context context) {
        super(context);
    }

    public CustomerImagview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomerImagview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomerImagview(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
      //  return super.onTouchEvent(event);
        switch (event.getAction()& MotionEvent.ACTION_MASK) {






            case MotionEvent.ACTION_DOWN://只有一个手指按下屏幕时触发的事件
                currentMatrix.set(matrix);
                startPoint.set(event.getX(), event.getY());
                type = DRAG;
                break;

            case MotionEvent.ACTION_MOVE://手指在屏幕移动时触发的事件，该事件在移动过程不断被触发
                if(type == DRAG){
                    break;
                }else if(type == ZOOM){
                    float dist = distance(event);//取两个手指之间的距离
                    if(dist > MIN_DISTANCE){
                        matrix.set(currentMatrix);//在上次缩放结果的基础上进行缩放
                        float scale = dist / startDist;
                        matrix.postScale(scale, scale, midPoint.x, midPoint.y);
                    }
                    lastDist = dist;
                }

                break;

            case MotionEvent.ACTION_POINTER_DOWN://如果已经有一个手指按下屏幕,后续再有手指按下屏幕，就会触发这个事件
                startDist = distance(event);//取两个手指之间的距离

                if(startDist > MIN_DISTANCE){
                    currentMatrix.set(matrix);//保存当前的缩放结果
                    type = ZOOM;
                    mIsZooming = true;
                    mZoomDealing = true;
                    midPoint = midPoint(event);
                }

                break;

            case MotionEvent.ACTION_UP://最后一个手指离开屏幕后触发的事件

               // matrix = new Matrix();
                if (mIsZooming) {
                    mIsZooming = false;
                }
                if (mZoomDealing) {
                    mZoomDealing = false;
                }
                break;
            case MotionEvent.ACTION_POINTER_UP://有一个手指离开了屏幕，但还有手指在屏幕上，此时会触发该事件
                type = NONE;

             //   matrix = new Matrix();
                if(mIsZooming) {
                    mIsZooming = false;
                }
                break;
            default:
                break;
        }
        if(type != DRAG  && mIsZooming){
            setImageMatrix(matrix);
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 画出变换后的图像
        //canvas.drawBitmap(bitmap, matrix, null);
        super.onDraw(canvas);
    }

    /**
     * 求两点的距离
     * @param event
     * @return
     */
    private float distance(MotionEvent event){
        float dx = event.getX(1)- event.getX(0);
        float dy = event.getY(1)- event.getY(0);
        return (float)Math.sqrt(dx * dx + dy * dy);
    }
    /**
     * 计算两点之间的中间点
     * @param event
     * @return
     */
    private PointF midPoint(MotionEvent event){
        float x = (event.getX(1) + event.getX(0)) / 2;
        float y = (event.getY(1) + event.getY(0)) / 2;
        return new PointF(x, y);
    }

//    private float getX(MotionEvent event, int index){
//        return MultiTouchApiInvoker.getInstance().getX(event, index);
//    }
//
//    private float getY(MotionEvent event, int index){
//        return MultiTouchApiInvoker.getInstance().getY(event, index);
//    }
}
