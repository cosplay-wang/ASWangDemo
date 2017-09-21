package as.founder.demo.wang.activity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

import as.founder.demo.wang.R;
import as.founder.demo.wang.circleImageview.WorkBezierCircle;

public class CanvasActivity extends AppCompatActivity {
Handler  handler = new Handler(){
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
       // view.setVisibility(View.VISIBLE);
view.startAnimation(aa);
    }
};
    WorkBezierCircle view;
    AlphaAnimation aa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);
//        ImageView v = (ImageView)findViewById(R.id.cus_iv);//一定要给ImageView加上几个像素的Padding，要不然效果出来不了
//        Paint p= new Paint();
//        p.setColor(Color.RED);//红色的光晕
//        BitmapDrawable bd = (BitmapDrawable) v.getDrawable();
//        Bitmap b = bd.getBitmap();
//        Bitmap bitmap = Bitmap.createBitmap(bd.getIntrinsicWidth(), bd.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(bitmap);
//        canvas.drawBitmap(b.extractAlpha(), 0, 0, p);
//
//        StateListDrawable sld = new StateListDrawable();
//        sld.addState(new int[]{android.R.attr.state_pressed}, new BitmapDrawable(bitmap));
//
//        v.setBackgroundDrawable(sld);
//        view = (WorkBezierCircle) findViewById(R.id.qw);
//        handler.sendEmptyMessageDelayed(1,5000);
//         aa = new AlphaAnimation(1f, 0f);//创建一个AlphaAnimation 对象，渐变从1->0
//        aa.setDuration(5000);//设置持续时间
//
//        aa.setFillAfter(true);//设置最后的动画效果，这里是显示状态（最后能够看到这个View)

    }
}
