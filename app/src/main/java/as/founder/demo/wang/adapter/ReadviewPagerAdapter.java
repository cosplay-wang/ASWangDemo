package as.founder.demo.wang.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by zhiwei.wang on 2016/11/22.
 * 先生_不靠谱
 */

public class ReadviewPagerAdapter extends PagerAdapter {
    public ReadviewPagerAdapter(List<View> viewLists) {
        this.viewLists = viewLists;
    }

    List<View> viewLists;
    @Override
    public int getCount() {
        return viewLists==null?0:viewLists.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
     //   return super.instantiateItem(container, position);
        View view = viewLists.get(position);
        container.addView(viewLists.get(position));
        return  view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
        container.removeView(viewLists.get(position));
    }
}
