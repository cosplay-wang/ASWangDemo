package as.founder.demo.wang.customerreaderimageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by zhiwei.wang on 2017/1/4.
 */

public class JointImageView extends ImageView {



    public JointImageView(Context context) {
        super(context);
    }

    public JointImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Bitmap OriBitmap = ((BitmapDrawable)getDrawable()).getBitmap();
        int width = OriBitmap.getWidth() + OriBitmap.getWidth();
        int height = Math.max(OriBitmap.getHeight(), OriBitmap.getHeight());
        Bitmap result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Bitmap topresult = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas topCanvas = new Canvas(topresult);
        Paint paint  = new Paint();
        paint.setColor(Color.RED);
        topCanvas.drawRect(new RectF(100,100,200,200),paint);


        Canvas Scanvas = new Canvas(result);
        Scanvas.drawBitmap(OriBitmap, 0, 0, null);
        Scanvas.drawBitmap(topresult, 0, 0, null);
        canvas.drawBitmap(result,0,0,new Paint());
    }
    /**
     *
     * 将两张位图拼接成一张(横向拼接)
     *
     * @param first
     * @param second
     * @return
     */
    private Bitmap add2Bitmap(Bitmap first, Bitmap second) {
        int width = first.getWidth() + second.getWidth();
        int height = Math.max(first.getHeight(), second.getHeight());
        Bitmap result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(first, 0, 0, null);
        canvas.drawBitmap(second, first.getWidth(), 0, null);
        return result;
    }
}
