package as.founder.demo.wang.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import as.founder.demo.wang.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MatrixActivity extends AppCompatActivity {

    @BindView(R.id.imageview)
    ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix);
        ButterKnife.bind(this);
    }
}
