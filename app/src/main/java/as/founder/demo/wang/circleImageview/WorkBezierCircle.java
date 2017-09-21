package as.founder.demo.wang.circleImageview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import as.founder.demo.wang.R;

/**
 * Created by zhiwei.wang on 2016/7/26.
 */
public class WorkBezierCircle extends View {
        Bitmap bitmap ;
    public WorkBezierCircle(Context context, AttributeSet attrs) {
        super(context, attrs);
        Resources res = getResources();
        bitmap = BitmapFactory.decodeResource(res, R.drawable.bb);
       // bitmap = Bitmap.createBitmap(R.drawable.a);
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
       // paint.setStyle(Paint.Style.FILL);
       // paint.setShadowLayer(10, 0, 0, Color.RED);
      //  setLayerType(LAYER_TYPE_SOFTWARE, null);
        //绘制圆形
      //  canvas.drawCircle(400, 400, 200, paint);
        canvas.drawBitmap(bitmap.extractAlpha(),600,500,paint);
    }
}
