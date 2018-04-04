package as.founder.demo.wang.circleImageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;

import as.founder.demo.wang.R;

/**
 * Created by zhiwei.wang on 2018/1/24.
 * wechat 760560322
 * 作用：
 */

public class MatrixImageView extends ImageView {
    Matrix matrix, saveMatrix;
    PointF startPointf = new PointF();
    PointF midPointf = new PointF();
    long lastTouchTime = -1;
    private float initDis;
    float alldx ;
    float alldy ;

    public MatrixImageView(Context context) {
        this(context, null);
    }

    public MatrixImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MatrixImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        matrix = new Matrix();
        saveMatrix = new Matrix();
        setScaleType(ScaleType.MATRIX);
        setImageResource(R.drawable.tbug_333);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                saveMatrix.set(matrix);
                startPointf.set(event.getX(), event.getY());
                long currentTouchTime = System.currentTimeMillis();
                if (lastTouchTime > 0) {

                    if (currentTouchTime - lastTouchTime > 300) {

                    } else {
//                        matrix.set(saveMatrix);
//                        float hhhScale = getScale();
//                        matrix.postScale(hhhScale, hhhScale,startPointf.x,startPointf.y);
//                        setImageMatrix(matrix);
//                        setImageBitmap(getNeedBitmap(((BitmapDrawable)getDrawable()).getBitmap(),scale));
//                        Log.e("Matrix","Matrix:"+alldx+"--------"+alldy);
//                        matrix = new Matrix();
//                        matrix.postTranslate((-alldx)*(1-scale), (-alldy)*(1-scale));
//                        setImageMatrix(matrix);
                        setImageResource(R.drawable.tbug_333_2);
                        matrix.set(saveMatrix);
                      //  float hhhScale = getScale();
                        matrix.postScale(0.5f, 0.5f,0,0);
                        setImageMatrix(matrix);

                        return true;
                    }
                }
                lastTouchTime = currentTouchTime;


                break;
            case MotionEvent.ACTION_MOVE:
                float  dx = event.getX() - startPointf.x;
                float dy = event.getY() - startPointf.y;
                matrix.set(saveMatrix);
                matrix.postTranslate(dx, dy);

              //  Log.e("Matrix", "onTouchEvent:"+dx+"======="+dy);
                break;
//            case MotionEvent.ACTION_POINTER_DOWN:
//                saveMatrix.set(matrix);
//                // 初始的两个触摸点间的距离
//                initDis = spacing(event);
//                // 设置为缩放模式
//                mode = ZOOM;
//                // 多点触摸的时候 计算出中间点的坐标
//                midPoint(midPointf, event);
//                break;
            case MotionEvent.ACTION_UP:
                float  dxx = event.getX() - startPointf.x;
                float dyy = event.getY() - startPointf.y;
                alldx = alldx + dxx / scale;
                alldy = alldy + dyy / scale;
                break;
        }
        setImageMatrix(matrix);
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e("Matrix", "onDraw");
    }
    //取两点的中点
    private void midPoint(PointF point, MotionEvent event) {
        try {
            float x = event.getX(0) + event.getX(1);
            float y = event.getY(0) + event.getY(1);
            point.set(x / 2, y / 2);
        } catch (IllegalArgumentException ex) {

            //这个异常是android自带的，网上清一色的这么说。。。。
            Log.v("TAG", ex.getLocalizedMessage());
        }
    }
    //取两点的距离
    private float spacing(MotionEvent event) {
        try {
            float x = event.getX(0) - event.getX(1);
            float y = event.getY(0) - event.getY(1);
            return (float)Math.sqrt(x * x + y * y);
        } catch (IllegalArgumentException ex) {
            Log.v("TAG", ex.getLocalizedMessage());
            return 0;
        }
    }

    public Bitmap getNeedBitmap(Bitmap defaultBitmap,float scale){
        // 获得图片的宽高
        int width = defaultBitmap.getWidth();
        int height = defaultBitmap.getHeight();
        // 设置想要的大小
        int newWidth = 320;
        int newHeight = 480;
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        // 得到新的图片
        Bitmap newbm = Bitmap.createBitmap(defaultBitmap, 0, 0, width, height, matrix,
                true);
        return newbm;
    }
    float scale = 1f;
    private float getScale(){
        if(scale == 1f){
            scale = 1.2f;
        }else if(scale == 1.2f){
            scale = 1.4f;
        }else if(scale == 1.4f){
            scale = 0.5f;
        }else{
            Toast.makeText(getContext(),"scale:"+scale,Toast.LENGTH_SHORT).show();
        }
        return scale;
    }
}
