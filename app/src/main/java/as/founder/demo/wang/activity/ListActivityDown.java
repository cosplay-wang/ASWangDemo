package as.founder.demo.wang.activity;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

import as.founder.demo.wang.ListViewDown.BaseListView;
import as.founder.demo.wang.ListViewDown.DownListViewAdapter;
import as.founder.demo.wang.R;

public class ListActivityDown extends Activity implements BaseListView.ListViewRefreshListener{

    BaseListView downListView;
    List<String> dataList = new ArrayList<String>();
    DownListViewAdapter listAdapter;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_activity_down);
        downListView = (BaseListView) findViewById(R.id.down_listview);
        getListData();
        listAdapter = new DownListViewAdapter(getApplicationContext(),dataList);
        downListView.setAdapter(listAdapter);
        downListView.setListViewRefreshListener(this);

//        downListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                dataList.remove(position-1);
//                listAdapter.notifyDataSetChanged();
//                return true;
//            }
//        });
    }
    private void getListData(){
        for(int i=0  ;i<10;i++){
            dataList.add("数据条数："+i);
        }
    }

    @Override
    public void getMoreListView() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getListData();
                listAdapter = new DownListViewAdapter(getApplicationContext(),dataList);
                downListView.setAdapter(listAdapter);
                downListView.normalFresh();
            }
        },1000);
    }
}
