package as.founder.demo.wang.activity;

import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import as.founder.demo.wang.R;
import as.founder.demo.wang.custometextview.BrokenLineView;

public class BrokenLineActivity extends AppCompatActivity {
    BrokenLineView brokenLineView;
    List<PointF> pointFList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broken_line);
        pointFList.add(new PointF(100,100));
        pointFList.add(new PointF(200,300));
        pointFList.add(new PointF(400,400));
        pointFList.add(new PointF(800,700));
        pointFList.add(new PointF(900,500));
        brokenLineView = (BrokenLineView) findViewById(R.id.broken_view);
        brokenLineView.setPointFList(pointFList);
    }
}
