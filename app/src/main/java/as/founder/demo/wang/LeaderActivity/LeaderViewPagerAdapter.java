package as.founder.demo.wang.LeaderActivity;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by zhiwei.wang on 2016/10/31.
 * 先生_不靠谱
 */

public class LeaderViewPagerAdapter extends PagerAdapter{

    List<View> imageViewList;

    public LeaderViewPagerAdapter(List<View> imageViewList) {
        this.imageViewList = imageViewList;
    }

    @Override
    public int getCount() {
        return imageViewList == null ? 0:imageViewList.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       // super.destroyItem(container, position, object);
        container.removeView(imageViewList.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
       // return super.instantiateItem(container, position);
        container.addView(imageViewList.get(position));
        return  imageViewList.get(position);

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
