package as.founder.demo.wang.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import as.founder.demo.wang.R;
import as.founder.demo.wang.adapter.MainListviewAdapter;

public class ViewDragHelper2Activity extends AppCompatActivity {
    ListView listview;
    List<String> dataList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_drag_helper2);
        listview = (ListView) (ListView) findViewById(R.id.listview);
        getData();
        listview.setAdapter(new MainListviewAdapter(dataList,this));

    }
    private void getData(){
        for(int i=0;i<100;i++){
            dataList.add("ddddddddddddd:"+i);
        }
    }
}
