package as.founder.demo.wang.circleImageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

import as.founder.demo.wang.R;

/**
 * Created by zhiwei.wang on 2016/7/13.
 */
public class CircleImageView extends ImageView {
    private Paint paint;
    private int roundWidth = 5;
    private int roundHeight = 5;
    private Paint paint2;
    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if(attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Circleimageview);
            roundWidth= a.getDimensionPixelSize(R.styleable.Circleimageview_roundWidth, roundWidth);
            roundHeight= a.getDimensionPixelSize(R.styleable.Circleimageview_roundHeight, roundHeight);
        }else {
            float density = context.getResources().getDisplayMetrics().density;
            roundWidth = (int) (roundWidth*density);
            roundHeight = (int) (roundHeight*density);
        }
        paint = new Paint();
        paint.setAntiAlias(true);//抗锯齿
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.YELLOW);
//        指定了一个颜色和容差，强制Paint避免在它上面绘图(或者只在它上面绘图)。
//        PixelXorXfermode 当覆盖已有的颜色时，应用一个简单的像素XOR操作。
//        PorterDuffXfermode 这是一个非常强大的转换模式，使用它，可以使用图像合成的16条Porter-Duff规则的任意一条来控制Paint如何与已有的Canvas图像进行交互。
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));

        paint2 = new Paint();
        paint2.setXfermode(null);
    }



    @Override
    public void draw(Canvas canvas) {
        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas2 = new Canvas(bitmap);
        //以上代码是创建立一个画布，用空白图片创建的//fdkgbdfgb
        super.draw(canvas2);
        /**
         * 大概的意思是画出了四个圆角
         */
        drawLiftUp(canvas2);
        drawRightUp(canvas2);
        drawLiftDown(canvas2);
        drawRightDown(canvas2);
        canvas.drawBitmap(bitmap, 0, 0, paint2);
        bitmap.recycle();

    }


    private void drawLiftUp(Canvas canvas) {
        Path path = new Path();
        path.moveTo(0, roundHeight);
        path.lineTo(0, 0);
        path.lineTo(roundWidth, 0);
        //path.arcTo意思是截取椭圆的一部分曲线
       // ，第一个参数是椭圆，第二个是开始截取的角度，第三个是停止的角度
        path.arcTo(new RectF(
                        0,
                        0,
                        roundWidth*2,
                        roundHeight*2),
                -90,
                -90);
        path.close();
        canvas.drawPath(path, paint);
    }

    private void drawLiftDown(Canvas canvas) {
        Path path = new Path();
        path.moveTo(0, getHeight()-roundHeight);
        path.lineTo(0, getHeight());
        path.lineTo(roundWidth, getHeight());
        path.arcTo(new RectF(
                        0,
                        getHeight()-roundHeight*2,
                        0+roundWidth*2,
                        getHeight()),
                90,
                90);
        path.close();
        canvas.drawPath(path, paint);
    }

    private void drawRightDown(Canvas canvas) {
        Path path = new Path();
        path.moveTo(getWidth()-roundWidth, getHeight());
        path.lineTo(getWidth(), getHeight());
        path.lineTo(getWidth(), getHeight()-roundHeight);
        path.arcTo(new RectF(
                getWidth()-roundWidth*2,
                getHeight()-roundHeight*2,
                getWidth(),
                getHeight()), 0, 90);
        path.close();
        canvas.drawPath(path, paint);
    }

    private void drawRightUp(Canvas canvas) {
        Path path = new Path();
        path.moveTo(getWidth(), roundHeight);
        path.lineTo(getWidth(), 0);
        path.lineTo(getWidth()-roundWidth, 0);
        path.arcTo(new RectF(
                        getWidth()-roundWidth*2,
                        0,
                        getWidth(),
                        0+roundHeight*2),
                -90,
                90);
        path.close();
        canvas.drawPath(path, paint);
    }
}
