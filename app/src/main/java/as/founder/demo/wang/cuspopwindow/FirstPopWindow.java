package as.founder.demo.wang.cuspopwindow;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

import java.lang.reflect.Field;

import as.founder.demo.wang.R;

/**
 * Created by zhiwei.wang on 2018/1/23.
 * wechat 760560322
 * 作用：
 */

public class FirstPopWindow extends PopupWindow {


    public FirstPopWindow(Context context) {
        super(context);
        init(context);
    }

    View mPopView;
    /**
     * 初始化
     *
     * @param context
     */
    private void init(Context context) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = LayoutInflater.from(context);
        //绑定布局
        mPopView = inflater.inflate(R.layout.popwindow_layout, null);
        setPopupWindow();

    }

    /**
     * 设置窗口的相关属性
     */
    @SuppressLint("InlinedApi")
    private void setPopupWindow() {

        this.setContentView(mPopView);// 设置View
        this.setWidth(1080);// 设置弹出窗口的宽
        this.setHeight(1920);// 设置弹出窗口的高
        this.setFocusable(true);// 设置弹出窗口可
        this.setClippingEnabled(false);
        this.setBackgroundDrawable(new ColorDrawable(Color.RED));// 设置背景透明
    }
    public void fitPopupWindowOverStatusBar(boolean needFullScreen) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            try {
                Field mLayoutInScreen = PopupWindow.class.getDeclaredField("mLayoutInScreen");
                mLayoutInScreen.setAccessible(true);
                mLayoutInScreen.set(this, needFullScreen);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
