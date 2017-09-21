package as.founder.demo.wang.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import as.founder.demo.wang.R;
import as.founder.demo.wang.adapter.SwipeRecyclerAdapter;
import as.founder.demo.wang.bean.MGsonFormat;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SwipeRefreshLayoutAC extends AppCompatActivity  {
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    List<String> list = new ArrayList<String>();
    SwipeRecyclerAdapter adapter;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
           getData();

            adapter.notifyDataSetChanged();
            swipeRefreshLayout.setRefreshing(false);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh_layout_ac);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        getData();
        adapter  = new SwipeRecyclerAdapter(list,this);
       final  LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        //swipeRefreshLayout.setColorSchemeColors(R.color.orange);
        adapter.setonItemClickListener(new SwipeRecyclerAdapter.onItemClickListener() {
             @Override
             public void onItemClick(View view, int postion) {
                 Toast.makeText(getApplicationContext(),"点击位置："+postion,Toast.LENGTH_SHORT).show();
             }
         });
        swipeRefreshLayout.setColorSchemeResources(R.color.orange,R.color.blue,R.color.green);
        /**
         * 第一种
         * swipe自带的刷新,下拉
         */
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                handler.sendEmptyMessageDelayed(1,5000);
            }
        });

        /**
         * 第一种
         * swipe自带的刷新,上啦
         */
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
               int  lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == adapter.getItemCount()) {
                    //swipeRefreshLayout.setRefreshing(true);
                    // 此处在现实项目中，请换成网络请求数据代码，sendRequest .....
                    handler.sendEmptyMessageDelayed(0, 5000);
                }
            }
        });


    }



    private void getData(){


        for(int i=0;i<20;i++){
            list.add("数据条数"+i);
        }
    }
}
