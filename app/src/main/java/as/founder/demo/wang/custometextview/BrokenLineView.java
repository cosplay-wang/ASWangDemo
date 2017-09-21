package as.founder.demo.wang.custometextview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhiwei.wang on 2017/4/12.
 */

public class BrokenLineView extends View {
    Paint paint,paintPoint;
    Path path;
    List<PointF> pointFList = new ArrayList<>();
    public void setPointFList(List<PointF> points){
        pointFList = points;
        invalidate();
    }
    public BrokenLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        pointFList.add(new PointF(100,100));
//        pointFList.add(new PointF(200,300));
//        pointFList.add(new PointF(400,400));
//        pointFList.add(new PointF(800,700));
//        pointFList.add(new PointF(900,500));


        paintPoint = new Paint();
        paintPoint.setColor(Color.RED);
        paintPoint.setStyle(Paint.Style.FILL);
        paintPoint.setAntiAlias(true); //去锯齿

        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true); //去锯齿

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        path = new Path();
        if(pointFList == null || pointFList.size() == 0){
            throw  new NullPointerException("-----------折线图没有设置点------------");
        }
        path.moveTo(pointFList.get(0).x,pointFList.get(0).y);
        for(int i=1;i<pointFList.size();i++){
            path.lineTo(pointFList.get(i).x,pointFList.get(i).y);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path,paint);
        for(int i=0;i<pointFList.size();i++){
            //canvas.drawPoint(pointFList.get(i).x,pointFList.get(i).y,paintPoint);
            canvas.drawCircle(pointFList.get(i).x,pointFList.get(i).y,15,paintPoint);
        }


    }
}
