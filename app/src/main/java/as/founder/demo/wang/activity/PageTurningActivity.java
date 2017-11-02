package as.founder.demo.wang.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Picture;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telecom.PhoneAccount;
import android.view.View;

import as.founder.demo.wang.R;

public class PageTurningActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(new ClipView(this));
    }

    private static class SampleView extends View {
        private Paint mPaint;
        private Path mPath;

        public SampleView(Context context) {
            super(context);
            setFocusable(true);

            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setStrokeWidth(6);
            mPaint.setTextSize(16);
            mPaint.setTextAlign(Paint.Align.RIGHT);

            mPath = new Path();
        }

        private void drawScene(Canvas canvas) {
            canvas.clipRect(0, 0, 1000, 1000);

            canvas.drawColor(Color.WHITE);

            mPaint.setColor(Color.RED);
            canvas.drawLine(0, 0, 1000, 1000, mPaint);

            mPaint.setColor(Color.GREEN);
            canvas.drawCircle(300, 700, 300, mPaint);

            mPaint.setColor(Color.BLUE);
            canvas.drawText("Clipping", 1000, 300, mPaint);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawColor(Color.GRAY);
            canvas.save();
            canvas.translate(100, 100);
            drawScene(canvas);
            canvas.restore();
            canvas.save();
            canvas.translate(1600, 100);
            canvas.clipRect(100, 100, 900, 900);
            canvas.clipRect(300, 300, 700, 700, Region.Op.DIFFERENCE);
            drawScene(canvas);
            canvas.restore();

            canvas.save();
            canvas.translate(100, 1600);
            mPath.reset();
            canvas.clipPath(mPath); // makes the clip empty
            mPath.addCircle(500, 500, 500, Path.Direction.CCW);
            canvas.clipPath(mPath, Region.Op.REPLACE);
            drawScene(canvas);
            canvas.restore();

            canvas.save();
            canvas.translate(1600, 1600);
            canvas.clipRect(0, 0, 600, 600);
            canvas.clipRect(400, 400, 1000, 1000, Region.Op.UNION);
            drawScene(canvas);
            canvas.restore();

            canvas.save();
            canvas.translate(100, 3100);
            canvas.clipRect(0, 0, 600, 600);
            canvas.clipRect(400, 400, 1000, 1000, Region.Op.XOR);
            drawScene(canvas);
            canvas.restore();

            canvas.save();
            canvas.translate(1600, 3100);
            canvas.clipRect(0, 0, 600, 600);
            canvas.clipRect(400, 400, 1000, 1000, Region.Op.REVERSE_DIFFERENCE);
            drawScene(canvas);
            canvas.restore();
        }
    }

    private class lineView extends View {
        Paint paint;

        public lineView(Context context) {
            super(context);
            paint = new Paint();
            paint.setColor(Color.BLUE);
            paint.setStrokeWidth(10);
            paint.setAntiAlias(true);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawLine(50, 50, 600, 500, paint);
        }
    }

    private class linesView extends View {
        Paint paint;
        //  float[] ps2 = {50,50,600,500};
        float[] ps = {50, 600, 400, 600,
                400, 600, 400, 50,
                400, 50, 50, 50,
                50, 50, 50, 600};

        public linesView(Context context) {
            super(context);
            paint = new Paint();
            paint.setColor(Color.BLUE);
            paint.setStrokeWidth(10);
            paint.setAntiAlias(true);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            /**
             * drawlines  必须四个四个点组合，四个点，一条直线。
             */
            //  canvas.drawLines(ps,paint);
            /**
             * drawlines  必须四个四个点组合，四个点，一条直线。,4 代表跳过四个点  offset，8   count 代表 画8个点
             */
            canvas.drawLines(ps, 4, 12, paint);


        }
    }

    private class arcView extends View {
        Paint paint;
        RectF rectF;

        public arcView(Context context) {
            super(context);
            paint = new Paint();
            paint.setColor(Color.BLUE);
            paint.setStrokeWidth(10);
            paint.setAntiAlias(true);
            rectF = new RectF(50, 300, 800, 600);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawArc(50, 50, 600, 100, 0, 360, true, paint);
            canvas.drawArc(rectF, 0, 60, false, paint);
        }
    }

    private class ARGBView extends View {
        Paint paint;
        RectF rectF;

        public ARGBView(Context context) {
            super(context);
            paint = new Paint();
            paint.setColor(Color.BLUE);
            paint.setStrokeWidth(10);
            paint.setAntiAlias(true);
            rectF = new RectF(50, 300, 800, 600);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            canvas.drawARGB(120, 200, 96, 85);

        }
    }

    private class ColorView extends View {
        Paint paint;
        RectF rectF;

        public ColorView(Context context) {
            super(context);
            paint = new Paint();
            paint.setColor(Color.BLUE);
            paint.setStrokeWidth(10);
            paint.setAntiAlias(true);
            rectF = new RectF(50, 300, 800, 600);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            canvas.drawColor(Color.BLUE);
        }
    }

    private class CircleView extends View {
        Paint paint;
        RectF rectF;

        public CircleView(Context context) {
            super(context);
            paint = new Paint();
            paint.setColor(Color.BLUE);
            paint.setStrokeWidth(10);
            paint.setAntiAlias(true);
            rectF = new RectF(50, 300, 800, 600);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);


            canvas.drawCircle(500, 500, 300, paint);
        }
    }

    private class OvalView extends View {
        Paint paint;
        RectF rectF;

        public OvalView(Context context) {
            super(context);
            paint = new Paint();
            paint.setColor(Color.BLUE);
            paint.setStrokeWidth(10);
            paint.setAntiAlias(true);
            rectF = new RectF(50, 300, 800, 600);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            canvas.drawOval(50, 50, 600, 300, paint);
            canvas.drawOval(rectF, paint);

        }
    }

    private class BitmapView extends View {
        Paint paint;
        RectF rectF;

        public BitmapView(Context context) {
            super(context);
            paint = new Paint();
            paint.setColor(Color.BLUE);
            paint.setStrokeWidth(10);
            paint.setAntiAlias(true);
            rectF = new RectF(50, 300, 800, 600);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.bookimage);

            canvas.drawBitmap(bmp, 50, 50, paint);


        }
    }

    private class PictureView extends View {//picture 是指录制你绘制的canvas的过程，


        // 1.创建Picture
        private Picture mPicture = new Picture();


        // 2.录制内容方法
        private void recording() {
            // 开始录制 (接收返回值Canvas)
            Canvas canvas = mPicture.beginRecording(500, 500);
            // 创建一个画笔
            Paint paint = new Paint();
            paint.setColor(Color.BLUE);
            paint.setStyle(Paint.Style.FILL);

            // 在Canvas中具体操作
            // 位移
            canvas.translate(250, 250);
            // 绘制一个圆
            canvas.drawCircle(0, 0, 100, paint);

            mPicture.endRecording();
        }


        Paint paint;
        RectF rectF;

        public PictureView(Context context) {
            super(context);
            paint = new Paint();
            paint.setColor(Color.BLUE);
            paint.setStrokeWidth(10);
            paint.setAntiAlias(true);
            rectF = new RectF(50, 300, 800, 600);
            recording();    // 调用录制
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.bookimage);


            // picture.
            canvas.drawPicture(mPicture, new RectF(0, 0, mPicture.getWidth(), 200));


        }
    }

    private class pointView extends View {
        Paint paint;
        RectF rectF;
        float[] ps = {50, 600, 400, 600,
                400, 500, 400, 50,
                400, 50, 50, 50,
                50, 50, 50, 600};

        public pointView(Context context) {
            super(context);
            paint = new Paint();
            paint.setColor(Color.BLUE);
            paint.setStrokeWidth(10);
            paint.setAntiAlias(true);
            rectF = new RectF(50, 300, 800, 600);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            // canvas.drawPoint(50,50,paint);
            //canvas.drawPoints(ps,paint);
            canvas.drawPoints(ps, 2, 4, paint);


        }
    }

    private class RectView extends View {
        Paint paint;
        RectF rectF;
        float[] ps = {50, 600, 400, 600,
                400, 500, 400, 50,
                400, 50, 50, 50,
                50, 50, 50, 600};

        public RectView(Context context) {
            super(context);
            paint = new Paint();
            paint.setColor(Color.BLUE);
            paint.setStrokeWidth(10);
            paint.setAntiAlias(true);
            rectF = new RectF(50, 300, 800, 600);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawRect(rectF, paint);


        }
    }

    private class PathView extends View {
        Paint paint;
        RectF rectF;
        Path path;
        float[] ps = {50, 600, 400, 600,
                400, 500, 400, 50,
                400, 50, 50, 50,
                50, 50, 50, 600};

        public PathView(Context context) {
            super(context);
            paint = new Paint();
            paint.setColor(Color.BLUE);
            paint.setStrokeWidth(10);
            paint.setTextSize(50);
            paint.setAntiAlias(true);
            rectF = new RectF(50, 300, 800, 600);
            path = new Path();
            path.moveTo(100, 100);
//            path.rMoveTo(100,100);
//            path.lineTo(50,50);
//            path.lineTo(60,600);//坐标轴的绝对坐标
//            path.rLineTo(500,200);//相对于前一个点的坐标   r 代表相对位置
            // path.addRect(rectF, Path.Direction.CW);
            //   path.addRoundRect(rectF,50,0, Path.Direction.CW);
         //   path.addCircle(400, 400, 250, Path.Direction.CW);

////绘制椭圆
//            RectF rectF = new RectF(100, 350, 500, 600);
////第一种方法绘制椭圆
//            path.addOval(rectF, Path.Direction.CW);
////第二种方法绘制椭圆
//            path.addOval(600, 350, 1000, 600, Path.Direction.CW);
//
////绘制矩形
//            RectF rect = new RectF(100, 650, 500, 900);
////第一种方法绘制矩形
//            path.addRect(rect, Path.Direction.CW);
////第一种方法绘制矩形
//            path.addRect(600, 650, 1000, 900, Path.Direction.CCW);
//
////绘制圆角矩形
//            RectF roundRect = new RectF(100, 950, 300, 1100);
////第一种方法绘制圆角矩形
//            path.addRoundRect(roundRect, 20, 20, Path.Direction.CW);
////第二种方法绘制圆角矩形
//            path.addRoundRect(350, 950, 550, 1100, 10, 50, Path.Direction.CCW);
////第三种方法绘制圆角矩形
////float[] radii中有8个值，依次为左上角，右上角，右下角，左下角的rx,ry
//            RectF roundRectT = new RectF(600, 950, 800, 1100);
//            path.addRoundRect(roundRectT, new float[]{50, 50, 50, 50, 50, 50, 0, 0}, Path.Direction.CCW);
////第四种方法绘制圆角矩形
//            path.addRoundRect(850, 950, 1050, 1100, new float[]{0, 0, 0, 0, 50, 50, 50, 50}, Path.Direction.CCW);



//            //在(400, 200, 600, 400)区域内绘制一个300度的圆弧
//            RectF rectF = new RectF(400, 200, 600, 400);
//            path.addArc(rectF, 0, 300);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(5f);
//           //在(400, 600, 600, 800)区域内绘制一个90度的圆弧，并且不连接两个点
//            RectF rectFTo = new RectF(400, 600, 600, 800);
//            path.arcTo(rectFTo, 0, 90, false);//,true表示是否强制连接
//            //等价于path.addArc(rectFTo, 0, 90);
//            path.close();//强制闭合，

            //path.quadTo(400,200,500,350);
            path.cubicTo(200,400,500,450,300,600);



        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
          //  canvas.drawTextOnPath("痛苦最好是别人的，快乐才是自己的；麻烦将是暂时的，朋友总是永恒的。", path, 0, 0, paint);
            canvas.drawPath(path, paint);


        }
    }
    private class ClipView extends View {
        Paint paint;
        RectF rectF;
        Path path = new Path();
        float[] ps = {50, 600, 400, 600,
                400, 500, 400, 50,
                400, 50, 50, 50,
                50, 50, 50, 600};

        public ClipView(Context context) {
            super(context);
            paint = new Paint();
            paint.setColor(Color.BLUE);
            paint.setStrokeWidth(10);
            paint.setAntiAlias(true);
            rectF = new RectF(50, 300, 800, 600);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

//                     DIFFERENCE(0), 不同的部分，
//                    INTERSECT(1),   相同的部分
//                    UNION(2),
//                    XOR(3),
//                    REVERSE_DIFFERENCE(4),
//                    REPLACE(5);
            canvas.clipRect(rectF,Region.Op.UNION);
            paint.setColor(Color.RED);
            canvas.drawCircle(150,400,200,paint);


        }
    }
}
