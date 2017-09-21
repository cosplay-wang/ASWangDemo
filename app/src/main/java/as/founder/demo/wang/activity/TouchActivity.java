package as.founder.demo.wang.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import as.founder.demo.wang.R;
import as.founder.demo.wang.yuezhitouch.TouchView;

public class TouchActivity extends AppCompatActivity {
    TouchView touchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch);
    }
}
