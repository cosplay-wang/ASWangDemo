package as.founder.demo.wang.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import as.founder.demo.wang.R;

public class VolleyAC extends AppCompatActivity implements View.OnClickListener {
    TextView tv_volley_get, tv_volley_send, tv_show;
    RequestQueue requestQueue;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 1){
                tv_show.setText("GET"+"\n"+msg.obj.toString());
            }else{
                tv_show.setText("POST"+"\n"+msg.obj.toString());
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttpsend);
        tv_volley_get = (TextView) findViewById(R.id.volley_get_tv);
        tv_volley_send = (TextView) findViewById(R.id.volley_send_tv);
        tv_show = (TextView) findViewById(R.id.volley_show_tv);
        requestQueue = Volley.newRequestQueue(VolleyAC.this);//这里的this指的是Context
        tv_volley_get.setOnClickListener(this);
        tv_volley_send.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.volley_get_tv:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        volleyGet();
                    }
                }).start();
                break;
            case R.id.volley_send_tv:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        volleyPost();
                    }
                }).start();
                break;
            default:
                break;

        }
    }

    private void volleyGet() {
        String url = "http://www.apabi.com/wxxqlib/mobile.mvc?api=commendlist&page=1&pagesize=20&token=AABB12346SS";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Message message = new Message();
                message.what = 1;
                message.obj = s;
                handler.sendMessage(message);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Message message = new Message();
                message.what = 1;
                message.obj = volleyError.toString();
                handler.sendMessage(message);
            }
        });
        requestQueue.add(stringRequest);
    }
    private void  volleyPost(){
        String url = "http://www.apabi.com/wxxqlib/mobile.mvc?api=commendlist&page=1&pagesize=20&token=AABB12346SS";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Message message = new Message();
                message.what = 2;
                message.obj = s;
                handler.sendMessage(message);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Message message = new Message();
                message.what = 2;
                message.obj = volleyError.toString();
                handler.sendMessage(message);
            }
        }){
            // 携带参数
            @Override
            protected HashMap<String, String> getParams()
                    throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("un", "852041173");
                hashMap.put("pw", "852041173abc");
                return hashMap;
            }
            @Override
            // Volley请求类提供了一个 getHeaders（）的方法，重载这个方法可以自定义HTTP 的头信息。（也可不实现）
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return headers;
            }
        };
        requestQueue.add(stringRequest);
    }
}
