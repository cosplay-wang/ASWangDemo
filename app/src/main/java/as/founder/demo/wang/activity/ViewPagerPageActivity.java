package as.founder.demo.wang.activity;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;

import java.util.ArrayList;
import java.util.List;

import as.founder.demo.wang.R;
import as.founder.demo.wang.viewpager.CommonUtils;
import as.founder.demo.wang.viewpager.FTransForm;

public class ViewPagerPageActivity extends BaseTestActivity {
    ViewPager viewPager;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    showToast();
                    break;
                case 2:
                    hideToast();
                    break;
            }

        }
    };
    int[] pics = {R.drawable.aaa1, R.drawable.aaa2, R.drawable.aaa3, R.drawable.aaa4,
            R.drawable.aaa1, R.drawable.aaa2, R.drawable.aaa3, R.drawable.aaa4,
            R.drawable.aaa1, R.drawable.aaa2, R.drawable.aaa3, R.drawable.aaa4,
            R.drawable.aaa1, R.drawable.aaa2, R.drawable.aaa3, R.drawable.aaa1,
            R.drawable.aaa2, R.drawable.aaa3, R.drawable.aaa4, R.drawable.aaa1,
            R.drawable.aaa2, R.drawable.aaa3};
    List<ImageView> imageViews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_view_pager_page);

        getViews();
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        findViewById(R.id.view_lin).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return viewPager.dispatchTouchEvent(event);
            }
        });
//        viewPager.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()){
//                    case MotionEvent.ACTION_DOWN:
//
//                }
//                return false;
//            }
//        });
        viewPager.setPageTransformer(false, new FTransForm());
        //  viewPager.setPageMargin(100);
        viewPager.setOffscreenPageLimit(3);

        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return imageViews.size()*100;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(imageViews.get(position%imageViews.size()));
                return imageViews.get(position%imageViews.size());
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(imageViews.get(position%imageViews.size()));
            }

//            @Override
//            public float getPageWidth(int position) {
//                return 1f;
//            }
        });
        CommonUtils.controlViewPagerSpeed(this, viewPager, 300);//设置你想要的时间
        viewPager.setCurrentItem(imageViews.size()*50);
        handler.sendEmptyMessageDelayed(1,5*1000);
        handler.sendEmptyMessageDelayed(2,10*1000);
    }

    private void getViews() {
        for (int pic : pics) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(pic);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageViews.add(imageView);
        }
    }
}
