package as.founder.demo.wang.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import as.founder.demo.wang.R;
import as.founder.demo.wang.custometextview.MarQueeTextView;
import as.founder.demo.wang.custometextview.MarqueeText;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MarQueeActivity extends Activity {


    //MarqueeText marquee;
    MarQueeTextView marQueeTextView;
    Button buttonControl,buttonReset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mar_quee);
//        marquee = (MarqueeText) findViewById(R.id.marquee);
//        marquee.startScroll();
        marQueeTextView = (MarQueeTextView) findViewById(R.id.jjj_marquee);
        buttonControl = (Button) findViewById(R.id.button_control);
        buttonControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(buttonControl.getText().equals("开始滚动")){
                    marQueeTextView.startScroll();
                    buttonControl.setText("关闭滚动");
                }else{
                    marQueeTextView.stopScroll();
                    buttonControl.setText("开始滚动");
                }
            }
        });
        buttonReset = (Button) findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                marQueeTextView.resetScroll();
            }
        });

    }
}
