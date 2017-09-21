package as.founder.demo.wang.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import as.founder.demo.wang.LeaderActivity.LeaderViewPagerAdapter;
import as.founder.demo.wang.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class LeaderActivity extends Activity {
    Unbinder unbinder;
    @BindView(R.id.leader_viewpager)
    ViewPager leaderViewPager;
    @BindView(R.id.leader_lin)
    LinearLayout leaderLin;

    List<View> viewList = new ArrayList<>();
    List<ImageView> imageViewList = new ArrayList<>();
    LeaderViewPagerAdapter pagerAdapter;
    int startItem = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_leader);
        unbinder = ButterKnife.bind(LeaderActivity.this);
        setLeaderViewPager();
    }

    public void setLeaderData(){
        viewList.add(LayoutInflater.from(LeaderActivity.this).inflate(R.layout.guid_view1,null));
        viewList.add(LayoutInflater.from(LeaderActivity.this).inflate(R.layout.guid_view2,null));
        viewList.add(LayoutInflater.from(LeaderActivity.this).inflate(R.layout.guid_view3,null));
        viewList.add(LayoutInflater.from(LeaderActivity.this).inflate(R.layout.guid_view4,null));
    }
    public void setLeaderImageView(){
        for(int i=0;i< viewList.size();i++){
            ImageView imageView = (ImageView) leaderLin.getChildAt(i);
            imageView.setEnabled(false);
            imageView.setTag(i);
            imageViewList.add(imageView);

        }

        leaderViewPager.addOnPageChangeListener(new ImagePagerChangeListener());
    }
    public void setCurDot(int position){
       for(int i = 0;i<viewList.size();i++){
           if(position == i){
               imageViewList.get(i).setEnabled(true);
           }else{
               imageViewList.get(i).setEnabled(false);
           }

       }

    }

    public void setLeaderViewPager(){
        setLeaderData();
        setLeaderImageView();
        pagerAdapter  = new LeaderViewPagerAdapter(viewList);
        leaderViewPager.setAdapter(pagerAdapter);
        leaderViewPager.setCurrentItem(startItem);
        imageViewList.get(startItem).setEnabled(true);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
    class ImagePagerChangeListener implements ViewPager.OnPageChangeListener{


        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            setCurDot(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
