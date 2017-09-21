package as.founder.demo.wang.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import as.founder.demo.wang.R;

public class TxtActivity extends AppCompatActivity {
TextView txtTV;
    ProgressBar bar;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            bar.setVisibility(View.GONE);
            txtTV.setText(msg.obj.toString());

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_txt);
        txtTV = (TextView) findViewById(R.id.txttv);
        bar = (ProgressBar) findViewById(R.id.txtpro);
        new Thread(new Runnable() {
            @Override
            public void run() {
               String tt =  readTxt();
                Message message = handler.obtainMessage();
                message.obj  = tt;
                message.what = 1;
                handler.sendMessage(message);
            }
        }).start();
    }

    private String readTxt(){
        String txt= "";
        try {
            InputStream in = getResources().getAssets().open("yy.txt");
            byte[] bytes = new byte[1000];
            InputStreamReader inreader = new InputStreamReader(in,"utf-8");
            BufferedReader breader = new BufferedReader(inreader);
            String line = null;
            while((line = breader.readLine())!=null){
                txt = txt + line;
            }



        } catch (IOException e) {
            txt = "exception"+e.toString();
            e.printStackTrace();
        }
        return txt;

    }


}
