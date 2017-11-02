package as.founder.demo.wang.activity;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

import as.founder.demo.wang.R;

public class LoadingActivity extends AppCompatActivity {
    ImageView imageView;
    Timer timer;
    TimerTask timerTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        imageView  = (ImageView) findViewById(R.id.loading);
        final AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
        animationDrawable.start();
//        timerTask =  new TimerTask() {
//            @Override
//            public void run() {
//                animationDrawable.
//                animationDrawable.start();
//            }
//        };
//        timer = new Timer();
//        timer.schedule(timerTask,150,450);
    }
}
