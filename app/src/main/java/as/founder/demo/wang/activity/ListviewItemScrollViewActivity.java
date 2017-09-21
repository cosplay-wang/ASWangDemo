package as.founder.demo.wang.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import as.founder.demo.wang.R;
import as.founder.demo.wang.adapter.ListItemScrollAdapter;
import as.founder.demo.wang.bean.ListScrollItemBean;

public class ListviewItemScrollViewActivity extends AppCompatActivity {
    List<ListScrollItemBean> dataList = new ArrayList<>();
    ListItemScrollAdapter adapter;
    ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_item_scroll_view);
        initData();
        adapter = new ListItemScrollAdapter(this,dataList);
        listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"点击了"+position+"个item",Toast.LENGTH_SHORT).show();
            }
        });
       // setContentView(R.layout.scroll_layout);

    }
    void initData(){
        for(int i=0;i<40;i++){
            ListScrollItemBean itm;
            if(i % 3  == 2)
            {
                itm  = new ListScrollItemBean("第"+ (i+1)+"个item",true);
            } else
            {
                itm  = new ListScrollItemBean("第"+ (i+1)+"个item",false);
            }
            dataList.add(itm);
        }
    }
}
