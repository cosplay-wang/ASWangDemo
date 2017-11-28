package as.founder.demo.wang.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;

import as.founder.demo.wang.R;

public class BaseTestActivity extends Activity {
    RelativeLayout parentLinearLayout ;
    RelativeLayout rale;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parentLinearLayout = new RelativeLayout(BaseTestActivity.this);
        initContentView(R.layout.activity_base_test);
        hideToast();


    }
    public void  showToast(){
        textView.setText("天天的受到核辐射发布的");
        rale.setVisibility(View.VISIBLE);
    }
    public void  hideToast(){
        rale.setVisibility(View.GONE);
    }

    /**
     * 初始化contentview
     */
    private void initContentView(int layoutResID) {
        ViewGroup viewGroup = (ViewGroup) findViewById(android.R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(parentLinearLayout);
        LayoutInflater.from(this).inflate(layoutResID, viewGroup, true);
        LayoutInflater.from(this).inflate(R.layout.layout, viewGroup, true);
        rale = (RelativeLayout) findViewById(R.id.toast_rela);
        textView = (TextView) findViewById(R.id.dbfsdjbfs);
    }



    @Override
    public void setContentView(int layoutResID) {
        LayoutInflater.from(this).inflate(layoutResID, parentLinearLayout, true);
    }



}
