package as.founder.demo.wang.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import as.founder.demo.wang.R;
import as.founder.demo.wang.adapter.SwipeRecyclerAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AppBarLayoutActivity extends Activity {

    @BindView(R.id.toolbar)
    TextView toolbar;
    @BindView(R.id.appbar_layout)
    AppBarLayout appbarLayout;
    @BindView(R.id.appbar_layout_recyclerview)
    RecyclerView appbarLayoutRecyclerview;
    List<String> stringList = new ArrayList<>();
    SwipeRecyclerAdapter adapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_bar_layout);
        ButterKnife.bind(this);
        setData();
        adapter = new SwipeRecyclerAdapter(stringList,this);
        appbarLayoutRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        appbarLayoutRecyclerview.setAdapter(adapter);
    }
    private void setData(){
        for(int i=0;i<50;i++){
            stringList.add("ooo:"+i);
        }
    }
}
