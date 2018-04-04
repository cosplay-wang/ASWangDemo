package as.founder.demo.wang.activity;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

import as.founder.demo.wang.R;
import as.founder.demo.wang.cuspopwindow.FirstPopWindow;
import as.founder.demo.wang.cuspopwindow.WaitScreen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PopWindowActivity extends Activity {

    @BindView(R.id.button)
    Button button;

    @OnClick(R.id.button)
    public void buttonClick(View view) {
        waitScreen.show();
      //  popupWindow.showAtLocation(PopWindowActivity.this.findViewById(R.id.all), Gravity.TOP|Gravity.START, 0, getStatusBarHeight(PopWindowActivity.this));
        Toast.makeText(PopWindowActivity.this, "ddddddddddd", Toast.LENGTH_SHORT).show();
    }

    FirstPopWindow popupWindow;
    WaitScreen waitScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
      //  getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_window);
        ButterKnife.bind(this);
//        popupWindow = new FirstPopWindow(this);
//        popupWindow.setClippingEnabled(false);
//        popupWindow.fitPopupWindowOverStatusBar(true);
        waitScreen = new WaitScreen(this);
    }

    /**
     * 获取状态通知栏高度
     *
     * @param activity
     * @return
     */
    public static int getStatusBarHeight(Activity activity) {
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);

        return frame.top;
    }
}
