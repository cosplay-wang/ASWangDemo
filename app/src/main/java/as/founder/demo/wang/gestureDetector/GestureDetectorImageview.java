package as.founder.demo.wang.gestureDetector;

import android.content.Context;
import android.content.res.Configuration;
import android.gesture.Gesture;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

/**
 * Created by zhiwei.wang on 2016/12/7.
 */

public class GestureDetectorImageview extends ImageView  implements GestureDetector.OnGestureListener,GestureDetector.OnDoubleTapListener, ViewTreeObserver.OnGlobalLayoutListener,ScaleGestureDetector.OnScaleGestureListener{
    public static String TAG = "OnGestureListener";
    GestureDetector gestureDetector;
    ScaleGestureDetector scaleGestureDetector;
    boolean isScale = false;
    boolean isVertical = false;
    boolean isCheckLeftAndRight = false;
    boolean isCheckTopAndBottom = false;
    private Matrix scaleMatrix = new Matrix();
    boolean isPagingBack = false;

    private boolean once = true;


    public static final float SCALE_MAX = 4.0f;
    /**
     * 初始化时的缩放比例，如果图片宽或高大于屏幕，此值将小于0
     */
    private float initScale = 1.0f;

    /**
     * 用于存放矩阵的9个值
     */
    private final float[] matrixValues = new float[9];
    public GestureDetectorImageview(Context context) {


        super(context);
        gestureDetector = new GestureDetector(context,this);
        scaleGestureDetector = new ScaleGestureDetector(context,this);

    }

    public GestureDetectorImageview(Context context, AttributeSet attrs) {


        super(context, attrs);
        gestureDetector = new GestureDetector(context,this);
        scaleGestureDetector = new ScaleGestureDetector(context,this);
    }

    public GestureDetectorImageview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetector.onTouchEvent(event);
        this.scaleGestureDetector.onTouchEvent(event);
        return true;

        //return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        Log.i(TAG,"onDown");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.i(TAG,"onShowPress");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.i(TAG,"onSingleTapUp");
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//        if(isScreenChange()){
//            isVertical = false;
//            Log.i(TAG,"横屏");
//        }else{
//            isVertical = true;
//            Log.i(TAG,"竖屏");
//        }
        if(isScale){
//            if(distanceX>20){
//                isPagingBack = false;
//            }else if(distanceX<-20){
//                isPagingBack = true;
//            }


        }else {
            if(isVertical){
                Log.i(TAG, "onScroll");

            }else {
                RectF rectF = getMatrixRectF();
                isCheckLeftAndRight = isCheckTopAndBottom = true;
                // 如果宽度小于屏幕宽度，则禁止左右移动
                if (rectF.width() < getWidth())
                {
                    distanceX = 0;
                    isCheckLeftAndRight = false;
                }
                // 如果高度小雨屏幕高度，则禁止上下移动
                if (rectF.height() < getHeight())
                {
                    distanceY = 0;
                    isCheckTopAndBottom = false;
                }

                scaleMatrix.postTranslate(-distanceX,-distanceY);
                checkMatrixBounds();
                setImageMatrix(scaleMatrix);
                Log.i(TAG, "onScrollY:"+distanceY);
                Log.i(TAG, "onScroll:X"+distanceX);
            }
        }
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.i(TAG,"onLongPress");

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.i(TAG,"onFling");
        if(isPagingBack){
            Log.i(TAG,"后翻页");
        }else{
            Log.i(TAG,"前翻页");
        }
        return true;
    }

//
//    按下（onDown）： 刚刚手指接触到触摸屏的那一刹那，就是触的那一下。
//    抛掷（onFling）： 手指在触摸屏上迅速移动，并松开的动作。
//    长按（onLongPress）： 手指按在持续一段时间，并且没有松开。
//    滚动（onScroll）： 手指在触摸屏上滑动。
//    按住（onShowPress）： 手指按在触摸屏上，它的时间范围在按下起效，在长按之前。
//    抬起（onSingleTapUp）：手指离开触摸屏的那一刹那。





    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        Log.i(TAG,"onSingleTapConfirmed");

        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Log.i(TAG,"onDoubleTap");
        //解释：双击的第二下down时触发
        //解释：双击的第二下down和up都会触发，可用e.getAction()区分。
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        Log.i(TAG,"onDoubleTapEvent");
        return true;
    }

    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        Log.i(TAG,"onScale"+detector.getScaleFactor());
      //  matrix.setScale(detector.getScaleFactor(),detector.getScaleFactor(),detector.getFocusX(),detector.getFocusY());
        float scale = getScale();
        float scaleFactor = detector.getScaleFactor();
        /**
         * 缩放的范围控制
         */

        if ((scale < SCALE_MAX && scaleFactor > 1.0f)
                || (scale > initScale && scaleFactor < 1.0f)) {
            /**
             * 最大值最小值判断
             */
            if (scaleFactor * scale < initScale) {
                scaleFactor = initScale / scale;
            }
            if (scaleFactor * scale > SCALE_MAX) {
                scaleFactor = SCALE_MAX / scale;
            }

            scaleMatrix.postScale(scaleFactor,scaleFactor, detector.getFocusX(), detector.getFocusY());
            setImageMatrix(scaleMatrix);


        }
        return true;
    }
    private Bitmap drawableToBitamp(Drawable drawable)
    {
        if (drawable instanceof BitmapDrawable)
        {
            BitmapDrawable bd = (BitmapDrawable) drawable;
            return bd.getBitmap();
        }
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }
/**
 * 获得当前的缩放比例
 *
 * @return
 */
        public final float getScale()
        {
            scaleMatrix.getValues(matrixValues);
            return matrixValues[Matrix.MSCALE_X];
        }


    /**
     * 根据当前图片的Matrix获得图片的范围
     *
     * @return
     */
    private RectF getMatrixRectF()
    {
        Matrix matrix = scaleMatrix;
        RectF rect = new RectF();
        Drawable d = getDrawable();
        if (null != d)
        {
            rect.set(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
            matrix.mapRect(rect);
        }
        return rect;
    }

    /**
     * 移动时，进行边界判断，主要判断宽或高大于屏幕的
     */
    private void checkMatrixBounds()
    {
        RectF rect = getMatrixRectF();

        float deltaX = 0, deltaY = 0;
        final float viewWidth = getWidth();
        final float viewHeight = getHeight();
        // 判断移动或缩放后，图片显示是否超出屏幕边界
        if (rect.top > 0 && isCheckTopAndBottom)
        {
            deltaY = -rect.top;
        }
        if (rect.bottom < viewHeight && isCheckTopAndBottom)
        {
            deltaY = viewHeight - rect.bottom;
        }
        if (rect.left > 0 && isCheckLeftAndRight)
        {
            deltaX = -rect.left;
        }
        if (rect.right < viewWidth && isCheckLeftAndRight)
        {
            deltaX = viewWidth - rect.right;
        }
        scaleMatrix.postTranslate(deltaX, deltaY);
    }


    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        Log.i(TAG,"onScaleBegin");
        isScale = true;
        return true;
    }
    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {
        isScale = false;
        Log.i(TAG,"onScaleEnd");
    }
    @Override
    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeGlobalOnLayoutListener(this);
    }

    @Override
    public void onGlobalLayout() {
        if (once) {
            Drawable d = getDrawable();
            if (d == null)
                return;
            Log.e(TAG, d.getIntrinsicWidth() + " , " + d.getIntrinsicHeight());
            int width = getWidth();
            int height = getHeight();
            // 拿到图片的宽和高
            int dw = d.getIntrinsicWidth();
            int dh = d.getIntrinsicHeight();
            float scale = 1.0f;
            // 如果图片的宽或者高大于屏幕，则缩放至屏幕的宽或者高
            if (dw > width && dh <= height) {
                scale = width * 1.0f / dw;
            }
            if (dh > height && dw <= width) {
                scale = height * 1.0f / dh;
            }
            // 如果宽和高都大于屏幕，则让其按按比例适应屏幕大小
            if (dw > width && dh > height) {
                scale = Math.min(width * 1.0f / dw, height * 1.0f / dh);
            }
            initScale = scale;

            Log.e(TAG, "initScale = " + initScale);
            scaleMatrix.postTranslate((width - dw) / 2, (height - dh) / 2);
            scaleMatrix.postScale(scale, scale, getWidth() / 2,
                    getHeight() / 2);
            // 图片移动至屏幕中心
            setImageMatrix(scaleMatrix);
            once = false;
        }
    }
    public boolean isScreenChange() {

        Configuration mConfiguration = this.getResources().getConfiguration(); //获取设置的配置信息
        int ori = mConfiguration.orientation ; //获取屏幕方向

        if(ori == mConfiguration.ORIENTATION_LANDSCAPE){
            //横屏
            return true;
        }else if(ori == mConfiguration.ORIENTATION_PORTRAIT){
            //竖屏
            return false;
        }
        return false;
    }


}
