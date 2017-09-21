package as.founder.demo.wang.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import as.founder.demo.wang.R;

public class CustomerViewGroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * 三种模式的父布局（match   wrap   和  只定明确dp的）
         */
        setContentView(R.layout.cus_viewgroup_basic_layout_match);
        //setContentView(R.layout.cus_viewgroup_basic_layout);
        //setContentView(R.layout.cus_viewgroup_basic_layout_wrap);
    }
}
