package as.founder.demo.wang.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import as.founder.demo.wang.R;
import as.founder.demo.wang.bean.MGsonFormat;
import as.founder.demo.wang.bean.wangyi;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by zhiwei.wang on 2016/6/12.
 */
public class OKHTTP extends Activity implements View.OnClickListener {
    TextView okhttpGetTV, show, okhttpSendTV;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {


                wangyi www = getNew(msg.obj.toString(), null);
                List<wangyi.T1348649145984Bean> list = www.getT1348649145984();
                String hh = "";
                if (list != null) {
                    for (int i = 0; i < list.size(); i++) {
                        hh = hh + list.get(i).getPostid() + "\n";
                    }
                    show.setText(hh);
                }


            } else {
//                wangyi www = getNew(msg.obj.toString(), null);
//                List<wangyi.T1348649145984Bean> list = www.getT1348649145984();
//                String hh = "";
//                if (list != null) {
//                    for (int i = 0; i < list.size(); i++) {
//                        hh = hh + list.get(i).getEname() + "\n";
//                    }
//                    show.setText(hh);
//                }
                show.setText(msg.obj.toString()+"");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.okhttp_send_layout);
        okhttpGetTV = (TextView) findViewById(R.id.okhttp_get_tv);
        show = (TextView) findViewById(R.id.okhttp_show_tv);
        okhttpSendTV = (TextView) findViewById(R.id.okhttp_send_tv);
        okhttpGetTV.setOnClickListener(this);
        okhttpSendTV.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.okhttp_get_tv:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String response = getRequest();
                        Message message = new Message();
                        message.obj = response;
                        message.what = 1;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case R.id.okhttp_send_tv:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String response = sendRequest();
                        Message message = new Message();
                        message.obj = response;
                        message.what = 2;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
        }

    }


    private String sendRequest() {
        MediaType JSON
                = MediaType.parse("application/json;charset=utf-8");
        String url = "http://c.m.163.com/nc/article/list/T1348649145984/0-20.html";
        OkHttpClient client = new OkHttpClient();
        String json = "dfsdf";
        RequestBody body = new FormBody.Builder()
                .add("data", "ss").add("token", "ddd")
                .add("system", "sample").build();
        //RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .header("User-Agent", "OkHttp Headers.java")
                .addHeader("Accept", "application/json; q=0.5")
                .addHeader("Accept", "application/vnd.github.v3+json")
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            InputStreamReader reader = new InputStreamReader(response.body().byteStream(), "utf-8");

            BufferedReader in = new BufferedReader(reader);


            StringBuffer buffer = new StringBuffer();
            String line = " ";
            while ((line = in.readLine()) != null) {
                buffer.append(line);
            }
            return buffer.toString();

            //  String urlEncode = new String(reader.toString(), "UTF-8" );
            //  return   reader.toString();
        } catch (IOException E) {
            E.toString();
        }
        return null;
    }


    private String getRequest() {
        String url = "http://c.m.163.com/nc/article/list/T1348649145984/0-20.html";
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            return URLDecoder.decode(response.body().string(), "UTF-8");
        } catch (IOException e) {
            e.toString();
        }
        return null;


    }

    public static List getNews(String jsonString, Class cls) {

        List<MGsonFormat> mlist = new ArrayList<MGsonFormat>();
        Gson gson = new Gson();
        mlist = gson.fromJson(jsonString, new TypeToken<List<MGsonFormat>>() {
        }.getType());
        //mlist = JSON.parseArray(jsonString, cls);
        return mlist;
    }

    public static wangyi getNew(String jsonString, Class cls) {
        wangyi ww = new wangyi();
        Gson gson = new Gson();
        ww = gson.fromJson(jsonString, wangyi.class);
        //mlist = JSON.parseArray(jsonString, cls);
        return ww;
    }

}

