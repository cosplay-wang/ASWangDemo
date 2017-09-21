package as.founder.demo.wang.circleImageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhiwei.wang on 2016/7/13.
 */
public class CanvasBase extends View {
    public CanvasBase(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //把整张画布绘制成白色
        canvas.drawColor(Color.WHITE);


        Paint paint = new Paint();

        //去锯齿
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(30);

        //绘制圆形
        canvas.drawCircle(400, 400, 200, paint);
        //绘制矩形
        paint.setColor(Color.YELLOW);
        canvas.drawRect(800,400,1200,800,paint);
        paint.setColor(Color.RED);
        canvas.drawRect(1300,400,1900,800,paint);


       // RectF rel = new RectF(10,240,70,270);
        RectF rel = new RectF(800,100,1200,300);
        RectF rel2 = new RectF();
        //绘制椭圆
        canvas.drawOval(rel, paint);


        //绘制任意多边形(连接各点，不一定为闭合的)
        Path path = new Path();
        path.moveTo(1300,100);
        path.lineTo(1300,300);
        path.lineTo(1800,200);
        canvas.drawPath(path,paint);


        //绘制任意多边形(连接各点，闭合的)
        Path path2 = new Path();
        path2.moveTo(200,900);
        path2.lineTo(300,1200);
        path2.lineTo(400,1000);
        path2.close();
        canvas.drawPath(path2,paint);

        //paint关于画笔的属性，有线，填充的，阴影的
        paint.setAntiAlias(true);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(30);




        Path path3 = new Path();
        paint.setShadowLayer(45, 10, 10, Color.GRAY);
        path3.moveTo(500,900);
        path3.lineTo(600,1200);
        path3.lineTo(800,1000);
        path3.lineTo(700,1000);
        path3.close();
        canvas.drawPath(path3,paint);


        //为Paint设置渐变器
        Shader mShasder = new LinearGradient(0, 0, 40, 60, new int[]{Color.RED,Color.GREEN,Color.BLUE,Color.YELLOW}, null, Shader.TileMode.REPEAT);
        paint.setShader(mShasder);

        Path path4 = new Path();
       // paint.setShadowLayer(45, 10, 10, Color.GRAY);
        path4.moveTo(900,900);
        path4.lineTo(1000,1200);
        path4.lineTo(1100,1000);
        path4.lineTo(900,1000);
        path4.close();
        canvas.drawPath(path4,paint);





        Paint paintupleft = new Paint();

        //去锯齿
        paintupleft.setAntiAlias(true);
        paintupleft.setColor(Color.BLUE);
        paintupleft.setStyle(Paint.Style.FILL);
        paintupleft.setStrokeWidth(1);


        Path pathleftup = new Path();
        int roundHeight = 200;
        int roundWidth = 200;
        pathleftup.moveTo(0, roundHeight);
        pathleftup.lineTo(0, 0);
        pathleftup.lineTo(roundWidth, 0);
        //path.arcTo意思是截取椭圆的一部分曲线
        // ，第一个参数是椭圆，第二个是开始截取的角度，第三个是停止的角度
        pathleftup.arcTo(new RectF(
                        0,
                        0,
                        roundWidth*2,
                        roundHeight*2),
                -90,
                -90);
     //   pathleftup.close();
        canvas.drawPath(pathleftup, paintupleft);







        Path pathleftdown = new Path();
        pathleftdown.moveTo(0, roundHeight);
        pathleftdown.lineTo(0, 2*roundHeight);
        pathleftdown.lineTo(roundWidth, 2*roundHeight);
        pathleftdown.arcTo(new RectF(
                        0,
                        0,
                        roundWidth*2,
                        roundHeight*2),
                -180,
                -90);
     //   pathleftdown.close();
        canvas.drawPath(pathleftdown, paintupleft);




        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas2 = new Canvas(bitmap);

        Path pathrightdown = new Path();
        pathrightdown.moveTo(getWidth()-roundWidth, getHeight());
        pathrightdown.lineTo(getWidth(), getHeight());
        pathrightdown.lineTo(getWidth(), getHeight()-roundHeight);
        pathrightdown.arcTo(new RectF(
                getWidth()-roundWidth*2,
                getHeight()-roundHeight*2,
                getWidth(),
                getHeight()), 0, 90);
        pathrightdown.close();
        canvas2.drawPath(pathrightdown, paintupleft);


//        pathrightdown.moveTo(roundWidth,2* roundHeight);
//        pathrightdown.lineTo(2*roundWidth, 2*roundHeight);
//        pathrightdown.lineTo(2*roundWidth, roundHeight);
//        pathrightdown.arcTo(new RectF(
//                        0,
//                        0,
//                        roundWidth*2,
//                        roundHeight*2),
//                -270,
//                -90);
//        pathrightdown.close();
//        canvas.drawPath(pathrightdown, paintupleft);



        Path pathrightup = new Path();
        pathrightup.moveTo(2*roundWidth,roundHeight);
        pathrightup.lineTo(2*roundWidth, 0);
        pathrightup.lineTo(roundWidth, 0);
        pathrightup.arcTo(new RectF(
                        0,
                        0,
                        roundWidth*2,
                        roundHeight*2),
                -360,
                -90);
        pathrightup.close();
        canvas.drawPath(pathrightup, paintupleft);
    }
}
