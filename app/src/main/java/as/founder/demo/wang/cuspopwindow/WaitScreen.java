package as.founder.demo.wang.cuspopwindow;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import as.founder.demo.wang.R;

/**
 * Created by zhiwei.wang on 2018/1/23.
 * wechat 760560322
 * 作用：
 */

public class WaitScreen {
    private PopupWindow popupWindow;
    private Context context;
    private View view;


    public WaitScreen(Activity context) {
        this.context = context;


        view = LayoutInflater.from(context).inflate(R.layout.popwindow_layout, null);

        if (popupWindow==null) {
            popupWindow = new PopupWindow(view,
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        }

        popupWindow.setTouchable(true);
     //   popupWindow.showAtLocation(contentView, Gravity.CENTER,0,0);


//        // 设置好参数之后再show
//        popupWindow.showAsDropDown(contentView);
//
//        view = LayoutInflater.from(context).inflate(R.layout.popwindow_layout , null);
//        popupWindow = new PopupWindow(view,1080,1920);
//        //sdk > 21 解决 标题栏没有办法遮罩的问题
        popupWindow.setClippingEnabled(false);

    }

    /**
     * 弹出等待提示框
     */
    public PopupWindow show() {
        //显示在界面0,0位置上
       // popupWindow.showAtLocation(view, Gravity.NO_GRAVITY,0,0);
        popupWindow.showAsDropDown(view);
        return popupWindow;
    }

    /**
     * 弹出等待提示框
     */
    public PopupWindow show(String message) {
        popupWindow.showAsDropDown(view);

        return popupWindow;
    }
    /**
     * 以动画的方式关闭等待弹屏
     */
    public void close(OnAnimationEnd onAnimationEnd) {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.setFocusable(false);


        }
    }

    /**
     * 以动画的方式关闭等待弹屏
     */
    public void close() {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.setFocusable(false);

        }
    }
    /**
     * 关闭弹屏
     */
    public void dismiss() {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
    }

    public interface OnAnimationEnd{
        void animationEnd();
    }
}
