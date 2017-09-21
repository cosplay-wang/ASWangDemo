package as.founder.demo.wang.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import as.founder.demo.wang.R;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SqliteActivity extends AppCompatActivity {
    Unbinder unbinder;
    @BindViews({R.id.bt_add,R.id.bt_delete,R.id.bt_change,R.id.bt_search})
    List<Button> listBts;
    @OnClick({R.id.bt_add,R.id.bt_delete,R.id.bt_change,R.id.bt_search})
    public void OnClick(View v){
        switch(v.getId()){
            case R.id.bt_add:
            {
              break;
            }
            case R.id.bt_delete:
            {
                break;
            }
            case R.id.bt_change:
            {
                break;
            }
            case R.id.bt_search:
            {
                break;
            }
            default:

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        unbinder = ButterKnife.bind(SqliteActivity.this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
