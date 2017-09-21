package as.founder.demo.wang.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import as.founder.demo.wang.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewFlowActivity extends AppCompatActivity {

    @BindView(R.id.flow_recycler)
    RecyclerView flowRecycler;
    @BindView(R.id.activity_recycler_view_flow)
    RelativeLayout activityRecyclerViewFlow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_flow);
        ButterKnife.bind(this);
    }
}
