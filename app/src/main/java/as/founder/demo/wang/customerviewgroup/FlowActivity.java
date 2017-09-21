package as.founder.demo.wang.customerviewgroup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import as.founder.demo.wang.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FlowActivity extends AppCompatActivity implements CustomerFlowViewGroup.FlowOnClicker{


    List<String> datalists = new ArrayList<>();
    @BindView(R.id.ddddddd)
    CustomerFlowViewGroup ddddddd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow);
        ButterKnife.bind(this);
        getData();
        ddddddd.setData(datalists,this);
        ddddddd.setFlowOnClicker(this);
    }

    private void getData() {
        for (int i = 0; i < 5; i++) {
            if (i %4==0) {
                datalists.add("我的图书");
            } else if(i%4==1) {
                datalists.add("图书");
            }else if(i%4==2){
                datalists.add("是大的吧图书");
            }else if(i%4==3){
                datalists.add("是大的吧图书是大的吧图书");
            }

        }
    }

    @Override
    public void FlowOnClick(int position) {
        Toast.makeText(getApplicationContext(),""+position,Toast.LENGTH_SHORT).show();
    }
}
