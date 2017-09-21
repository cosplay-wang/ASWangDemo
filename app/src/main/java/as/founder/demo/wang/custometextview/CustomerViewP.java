package as.founder.demo.wang.custometextview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import as.founder.demo.wang.R;

/**
 * Created by zhiwei.wang on 2016/12/13.
 */

public class CustomerViewP extends ImageView {
    Paint mPaint ;
    RectF mRectF;
    public CustomerViewP(Context context) {
        super(context);
        mPaint = new Paint();
        mRectF = new RectF();
    }

    public CustomerViewP(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mRectF = new RectF();
    }

    public CustomerViewP(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void draw(Canvas canvas) {
      //  super.draw(canvas);
       // Drawable drawable = getDrawable();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.book3);
        canvas.drawBitmap(bitmap,0,0,mPaint);
        mPaint.setColor(getResources().getColor(R.color.touming));
        mRectF.set(110,70,150,100);
        canvas.drawRect(mRectF,mPaint);

    }
    public void setmRectF(float left,float top,float right,float bottom){
        mRectF.set(left, top, right, bottom);
    };
}
