package as.founder.demo.wang.viewpager;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

/**
 * Created by zhiwei.wang on 2017/11/2.
 */

public class FTransForm implements ViewPager.PageTransformer {
    @Override
    public void transformPage(View view, float position) {
        view.setRotationX(0);
        view.setAlpha(1);
        view.setRotationY(-30*position);
        float scaleFactor = Math.max(0.7f, 1 - Math.abs(position));
        view.setScaleY(scaleFactor);
        view.setScaleX(scaleFactor);
        if(position<=0){

        }else if(position<=1){

        }
    }
}
