package as.founder.demo.wang.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import as.founder.demo.wang.customerviewgroup.LyricsView;

public class LyricsActivity extends AppCompatActivity {
    List<String> stringList = new ArrayList<>();
    int maxSize = 30;
    Timer timer;
    TimerTask task;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(maxSize<=0){
                timer.cancel();
            }
        }
    };
    Handler handler2 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Log.e("timer2",maxSize + "S");
            if(maxSize>0){
                handler2.sendEmptyMessageDelayed(0,1000);
                maxSize --;
            }

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LyricsView lyricsView = new LyricsView(this);
        setData();
        lyricsView.setData(stringList);
        setContentView(lyricsView);
       // timer();
        handerTimer();
        Log.e("reverse",reverseSentense("i love you") +"\n"+ reverseWordsStringBuffer("i love you")+"\n"+reverseWordsString("i love you"));
    }
    private void setData(){
        stringList.add("故事的小黄花 ");
        stringList.add("从出生那飘着 ");
        stringList.add("童年的荡秋千   ");
        stringList.add("随记忆一直晃到现在 ");
        stringList.add("rui sou sou xi dou xi la" +
                "sou la xi xi xi xi la xi la sou " +
                "吹着前奏望着天空" +
                "我想起花瓣试着掉落 " +
                "为你翘课的那一天 " +
                "花落的那一天 ");
        stringList.add("教室的那一间  ");
    }

    private String  reverseSentense(String sentense){
        String returnStr = "";
        String str[] = sentense.split(" ");
        for(int index = str.length -1;index>=0;index--){
            returnStr = returnStr  + str[index]+ " ";
        }
        return  returnStr;
    }
    private String reverseWordsStringBuffer(String words){
        String returnStr = "";
        StringBuffer stringBuffer = new StringBuffer(words);
        returnStr = stringBuffer.reverse().toString();
        return  returnStr;
    }
    private String reverseWordsString(String words){
        String returnStr = "";
        char[] word = words.toCharArray();
        for(int index = words.length()-1;index>=0;index --){
            returnStr = returnStr + word[index];
        }
        return  returnStr;
    }
    private void timer(){
        timer = new Timer();
         task = new TimerTask() {
            @Override
            public void run() {
                Log.e("timer",maxSize + "S");
                handler.sendEmptyMessage(0);
                maxSize = maxSize - 1;
            }
        };
        timer.schedule(task,0,1000);
    }
    private void handerTimer(){
        handler2.sendEmptyMessageDelayed(0,1000);

    }
}
