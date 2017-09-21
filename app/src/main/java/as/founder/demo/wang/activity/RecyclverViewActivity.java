package as.founder.demo.wang.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import as.founder.demo.wang.R;

public class RecyclverViewActivity extends AppCompatActivity  {
    public String TAG = "RECYCLVERVIEW";
    RecyclerView recyclerView;
    List<String> dataList = new ArrayList<>();
    int firstVisibleItem,lastVisibleItem,itemVisibleCount,itemCount;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclver_view);
        getData();
        recyclerView = (RecyclerView) findViewById(R.id.recyclverview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyAdapter();
        recyclerView.setAdapter(myAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem+1>=itemCount-3){
                    Log.i(TAG,"加载更多");


                            try {
                                Thread.sleep(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }




                    loadMore();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                itemVisibleCount = recyclerView.getChildCount();
                itemCount = recyclerView.getLayoutManager().getItemCount();
                lastVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                firstVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                Log.i(TAG,"lastVisibleItem:"+lastVisibleItem+"--firstVisibleItem:"+firstVisibleItem+"--itemVisibleCount:"+itemVisibleCount+"--itemCount:"+itemCount);

            }
        });
    }
    private void getData(){
        for(int i=0;i<30;i++){
            dataList.add("第"+i+"条数据");
        }
    }
    class MyAdapter extends RecyclerView.Adapter{

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if(viewType == 1){
                View view = LayoutInflater.from(RecyclverViewActivity.this).inflate(R.layout.listview_item_layout, null);
                TextView tv = (TextView) view.findViewById(R.id.item_tv);
                tv.setText("正在加载更多");
                footViewHolder footviewHolder = new footViewHolder(view);
                return  footviewHolder;
            }else {
                View view = LayoutInflater.from(RecyclverViewActivity.this).inflate(R.layout.listview_item_layout, null);
                MyViewHolder myViewHolder = new MyViewHolder(view);
                return myViewHolder;
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if(holder instanceof MyViewHolder){
                ((MyViewHolder)holder).tv.setText(dataList.get(position));
            }

        }

        @Override
        public int getItemViewType(int position) {
           if((position+1)==myAdapter.getItemCount()){
               return 1;
           }else{
               return 0;
           }
            //return super.getItemViewType(position);
        }

        @Override
        public int getItemCount() {
            return dataList==null?0:dataList.size()+1;
        }

        class MyViewHolder extends RecyclerView.ViewHolder{
            TextView tv;
            public MyViewHolder(View itemView) {
                super(itemView);
             tv = (TextView)itemView.findViewById(R.id.item_tv);
            }
        }
        class footViewHolder extends RecyclerView.ViewHolder{
            public footViewHolder(View itemView) {
                super(itemView);
            }
        }

    }




    public void loadMore() {
        getData();
        myAdapter.notifyDataSetChanged();
    }
}
