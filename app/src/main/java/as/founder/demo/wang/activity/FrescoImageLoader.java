package as.founder.demo.wang.activity;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import as.founder.demo.wang.R;
import as.founder.demo.wang.adapter.FrescoRecyclerAdapter;
import as.founder.demo.wang.recyclverview.PagingScrollHelper;

public class FrescoImageLoader extends AppCompatActivity{
RecyclerView frescoRecycler;
    FrescoRecyclerAdapter adapter;
    List<String> data = new ArrayList<String>();
    PagingScrollHelper scrollHelper = new PagingScrollHelper();
    RecyclerView.LayoutManager layoutManager = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco_image_loader);
       // Fresco.initialize(this);
        frescoRecycler = (RecyclerView) findViewById(R.id.fresco_reccylerview);
        data = getList();

        adapter = new FrescoRecyclerAdapter(this,data);
       // PagerSnapHelper snapHelper = new PagerSnapHelper();
//
       // snapHelper.attachToRecyclerView(frescoRecycler);
        frescoRecycler.setAdapter(adapter);


//        scrollHelper.setUpRecycleView(frescoRecycler);
//        scrollHelper.setOnPageChangeListener(this);
       // layoutManager = new HorizontalPageLayoutManager(3,4);

        frescoRecycler.setLayoutManager(new GridLayoutManager(this,3));
       // scrollHelper.updateLayoutManger();
        frescoRecycler.getLayoutManager().smoothScrollToPosition(frescoRecycler,null,data.size()-1);
        frescoRecycler.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);
            }

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(10,10,10,10);
            }

            @Override
            public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDrawOver(c, parent, state);

            }
        });
















    }


//    @Override
//    public void onPageChange(int index,boolean isNext) {
//        getList();
//        adapter.notifyDataSetChanged();
//        Log.i("ddd",data.size()+"");
//        Toast.makeText(getApplicationContext(),"dd",Toast.LENGTH_SHORT).show();
//    }

    public List<String> getList(){


        // xmlparser.requesting(handler, null);
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20141210-YKYD-889-0032&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20091014-m802-w014-004&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20121025-YPT-889-0158&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20141210-YKYD-889-0032&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20120224-YPT-889-0058&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20141210-YKYD-889-0032&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20141210-YKYD-889-0032&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20140808-YKYD-889-0151&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20141210-YKYD-889-0032&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20141210-YKYD-889-0032&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20141210-YKYD-889-0032&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20140922-ZLZX-889-0110&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20141210-YKYD-889-0032&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20141210-YKYD-889-0032&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20141210-YKYD-889-0032&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20141210-YKYD-889-0032&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20070428-m002-w012-046&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20141210-YKYD-889-0032&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20141210-YKYD-889-0032&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20141203-YKYD-889-0078&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20141210-YKYD-889-0032&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20140703-XRT-889-0702&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20141210-YKYD-889-0032&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20140724-XRT-889-0104&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20141210-YKYD-889-0032&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20140417-ZLZX-889-1105&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20141210-YKYD-889-0032&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20141210-YKYD-889-0032&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20140808-YKYD-889-0151&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20141210-YKYD-889-0032&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20141210-YKYD-889-0032&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20141210-YKYD-889-0032&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20140922-ZLZX-889-0110&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20141210-YKYD-889-0032&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20141210-YKYD-889-0032&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20141210-YKYD-889-0032&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20141210-YKYD-889-0032&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20070428-m002-w012-046&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20141210-YKYD-889-0032&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20141210-YKYD-889-0032&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20141203-YKYD-889-0078&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20141210-YKYD-889-0032&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20140703-XRT-889-0702&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20141210-YKYD-889-0032&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20140724-XRT-889-0104&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20141210-YKYD-889-0032&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20140417-ZLZX-889-1105&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20141210-YKYD-889-0032&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20141210-YKYD-889-0032&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20140808-YKYD-889-0151&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20141210-YKYD-889-0032&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20141210-YKYD-889-0032&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20141210-YKYD-889-0032&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20140922-ZLZX-889-0110&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20141210-YKYD-889-0032&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20141210-YKYD-889-0032&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20141210-YKYD-889-0032&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20141210-YKYD-889-0032&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20070428-m002-w012-046&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20141210-YKYD-889-0032&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20141210-YKYD-889-0032&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20141203-YKYD-889-0078&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20141210-YKYD-889-0032&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20140703-XRT-889-0702&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20141210-YKYD-889-0032&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20140724-XRT-889-0104&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20141210-YKYD-889-0032&orgid=apabi");
        data.add("http://www.apabi.com/apaDlibrary/apabi/GetJpgUrl.aspx?type=1&resid=m.20140417-ZLZX-889-1105&orgid=apabi");
        return data;
    }
}
