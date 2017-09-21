package as.founder.demo.wang.touchview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by zhiwei.wang on 2017/2/23.
 */

public class TouchViewGroupLayout extends LinearLayout {


    public TouchViewGroupLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public TouchViewGroupLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TouchViewGroupLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchViewGroupLayout(Context context) {
        super(context);
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i("viewtouch","vp--lindispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i("viewtouch","vp--linonInterceptTouchEvent");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("viewtouch","vp--linonTouchEvent");
        return super.onTouchEvent(event);
    }
}
