package as.founder.demo.wang.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import as.founder.demo.wang.LeaderActivity.LeaderViewPagerAdapter;
import as.founder.demo.wang.R;
import as.founder.demo.wang.viewpager.FixedSpeedScroller;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class LeaderActivity extends Activity {
    Unbinder unbinder;
    @BindView(R.id.leader_viewpager)
    ViewPager leaderViewPager;
    @BindView(R.id.leader_lin)
    LinearLayout leaderLin;
    FixedSpeedScroller  scroller;
    List<View> viewList = new ArrayList<>();
    List<ImageView> imageViewList = new ArrayList<>();
    LeaderViewPagerAdapter pagerAdapter;
    int startItem = 0;
    private int currentPosition;
    Timer timer = new Timer();
    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            leaderViewPager.post(new Runnable() {
                @Override
                public void run() {
                    if (!isTouching) {
                        leaderViewPager.setCurrentItem(leaderViewPager.getCurrentItem() + 1);
                    }
                }
            });

        }
    };
    private boolean isTouching;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_leader);
        unbinder = ButterKnife.bind(LeaderActivity.this);
        setLeaderViewPager();
        if (imageViewList.size() > 1) {
            leaderViewPager.setCurrentItem(1);
            timer.schedule(timerTask, 3000, 3000);
        }
        leaderViewPager.setOnTouchListener(new MyViewPagerOntoucher());

    }

    public void setLeaderData() {

        viewList.add(LayoutInflater.from(LeaderActivity.this).inflate(R.layout.guid_view4, null));
        viewList.add(LayoutInflater.from(LeaderActivity.this).inflate(R.layout.guid_view1, null));
        viewList.add(LayoutInflater.from(LeaderActivity.this).inflate(R.layout.guid_view2, null));
        viewList.add(LayoutInflater.from(LeaderActivity.this).inflate(R.layout.guid_view3, null));
        viewList.add(LayoutInflater.from(LeaderActivity.this).inflate(R.layout.guid_view4, null));
        viewList.add(LayoutInflater.from(LeaderActivity.this).inflate(R.layout.guid_view1, null));
    }

    public void setLeaderImageView() {
        for (int i = 0; i < viewList.size(); i++) {
            // ImageView imageView = (ImageView) leaderLin.getChildAt(i);
            ImageView imageView = (ImageView) LayoutInflater.from(this).inflate(R.layout.point_image, null);
            imageView.setEnabled(false);
            imageView.setTag(i);
            imageViewList.add(imageView);
            if (i == 0 || i == viewList.size() - 1) {
                imageView.setVisibility(View.GONE);
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0F);
            leaderLin.addView(imageView, layoutParams);

        }

        leaderViewPager.addOnPageChangeListener(new ImagePagerChangeListener());
    }

    public void setCurDot(int position) {
        for (int i = 0; i < viewList.size(); i++) {
            if (position == i) {
                imageViewList.get(i).setEnabled(true);
            } else {
                imageViewList.get(i).setEnabled(false);
            }

        }

    }

    public void setLeaderViewPager() {
        setLeaderData();
        setLeaderImageView();
        pagerAdapter = new LeaderViewPagerAdapter(viewList);
        leaderViewPager.setAdapter(pagerAdapter);
        try {
            // 通过class文件获取mScroller属性
            Field mField = ViewPager.class.getDeclaredField("mScroller");
            mField.setAccessible(true);
            scroller = new FixedSpeedScroller(leaderViewPager.getContext(),new AccelerateInterpolator());
            mField.set(leaderViewPager, scroller);
        } catch (Exception e) {
            e.printStackTrace();
        }
        leaderViewPager.setCurrentItem(startItem);
         scroller.setmDuration(1000);// 切换时间，毫秒值
        imageViewList.get(startItem).setEnabled(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
        leaderViewPager.setAdapter(null);
        leaderViewPager.setOnTouchListener(null);
        pagerAdapter = null;
        unbinder.unbind();
    }

    class ImagePagerChangeListener implements ViewPager.OnPageChangeListener {


        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            currentPosition = position;
            setCurDot(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
             // 若viewpager滑动未停止，直接返回
            // if (state != ViewPager.SCROLL_STATE_IDLE) return;
            //  若当前为第一张，设置页面为倒数第二张
            if (imageViewList.size() > 1) {


                if (currentPosition == 0) {
                    leaderViewPager.setCurrentItem((imageViewList.size() - 2), false);
                } else if (currentPosition == imageViewList.size() - 1) {
                    // 若当前为倒数第一张，设置页面为第二张
                    leaderViewPager.setCurrentItem(1, false);
                }

            } else {

            }
        }
    }

    private class MyViewPagerOntoucher implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    isTouching = true;
                    break;
                case MotionEvent.ACTION_UP:
                    isTouching = false;
                    break;
            }
            return false;
        }
    }
}