package as.founder.demo.wang.custometextview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhiwei.wang on 2016/7/7.
 */
public class CustomerView2 extends View{


    public CustomerView2(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    /**
     * 测量view的高度和宽度
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
