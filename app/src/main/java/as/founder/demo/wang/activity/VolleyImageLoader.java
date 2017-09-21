package as.founder.demo.wang.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import as.founder.demo.wang.R;
import as.founder.demo.wang.adapter.RecyclerViewAdapter;

public class VolleyImageLoader extends AppCompatActivity {
RecyclerView recyclerViewH,recyclerViewV;
RecyclerViewAdapter adapter;
    List<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_image_loader);
		recyclerViewH = (RecyclerView) findViewById(R.id.hrecyclerView);
		recyclerViewV = (RecyclerView) findViewById(R.id.vrecyclerView);
        list = getList();
        adapter = new RecyclerViewAdapter(this,list);
	//	recyclerView.setLayoutManager(new GridLayoutManager(this,4));
		LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
		mLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
		recyclerViewH.setLayoutManager(mLinearLayoutManager);

		//recyclerView.setLayoutManager(new GridLayoutManager(this,4));
		//添加分割线
//		recyclerView.addItemDecoration(new DividerItemDecoration(
//				getActivity(), DividerItemDecoration.HORIZONTAL_LIST));
		recyclerViewH.setAdapter(adapter);


		recyclerViewV.setLayoutManager(new GridLayoutManager(this,4));
		recyclerViewV.setAdapter(adapter);
    }
    public List<String> getList(){
        List<String> data = new ArrayList<String>();

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
