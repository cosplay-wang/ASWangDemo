package as.founder.demo.wang.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import as.founder.demo.wang.ListViewDown.DownListViewAdapter;
import as.founder.demo.wang.R;
import as.founder.demo.wang.viewdown.PullToRefresh;

public class PullToRefreshActivity extends AppCompatActivity implements PullToRefresh.OnHeaderRefreshListener{
ListView lv;
    List<String> dataList = new ArrayList<String>();
    DownListViewAdapter listAdapter;
    PullToRefresh pullview;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_refresh);
        getListData();
        lv  = (ListView) findViewById(R.id.pull_listview);
        listAdapter = new DownListViewAdapter(getApplicationContext(),dataList);
        lv.setAdapter(listAdapter);
        pullview = (PullToRefresh) findViewById(R.id.pull_view);
        pullview.setHeaderRefreshListener(this);

    }
    private void getListData(){
        for(int i=0  ;i<3;i++){
            dataList.add("数据条数："+i);
        }
    }

    @Override
    public void onHeaderRefresh(final PullToRefresh view) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getListData();
                listAdapter = new DownListViewAdapter(getApplicationContext(),dataList);
                lv.setAdapter(listAdapter);
                view.changeHeadViewPaddingNormal();
            }
        },2000);
    }
}
