package as.founder.demo.wang.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import as.founder.demo.wang.R;
import as.founder.demo.wang.adapter.ReadviewPagerAdapter;
import as.founder.demo.wang.customerreaderimageview.CustomerImagview;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ReadViewActivity extends AppCompatActivity {
    Unbinder unbinder;
    @BindView(R.id.page_view)
    ViewPager viewPager;
    List<View> viewLists = new ArrayList<>();
    ReadviewPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_read_view);
        unbinder = ButterKnife.bind(ReadViewActivity.this);
        getData();
        adapter = new ReadviewPagerAdapter(viewLists);
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
    private void getData(){
        for(int i=0;i<1050;i++){
            View view = LayoutInflater.from(this).inflate(R.layout.read_view,null);
            CustomerImagview iv = (CustomerImagview) view.findViewById(R.id.read_image);
            if(i%6==0){
                iv.setImageResource(R.drawable.a);
            }else if(i%6==1){
                iv.setImageResource(R.drawable.list_111);
            }else if(i%6==2){
                iv.setImageResource(R.drawable.b);
            }else if(i%6==3){
                iv.setImageResource(R.drawable.book3);
            }else if(i%6==4){
                iv.setImageResource(R.drawable.c);
            }else if(i%6==5){
                iv.setImageResource(R.drawable.bb);
            }
            viewLists.add(view);
        }



    }
}
