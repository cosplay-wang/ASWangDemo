package as.founder.demo.wang.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;

import as.founder.demo.wang.R;

public class SurfaceViewActivity extends AppCompatActivity {
    SurfaceView fsurface_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface_view);
        fsurface_view = (SurfaceView) findViewById(R.id.fsurface_view);
    }
}
