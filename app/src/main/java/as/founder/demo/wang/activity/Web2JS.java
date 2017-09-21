package as.founder.demo.wang.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.Toast;

import as.founder.demo.wang.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class Web2JS extends AppCompatActivity {

    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.activity_web2_js)
    RelativeLayout activityWeb2Js;
    String test1= "SCHEME:";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web2_js);
        ButterKnife.bind(this);
        if(getIntent().getData()!=null){


        Uri uri = getIntent().getData();
         test1= test1+uri.getQueryParameter("arg0")+ uri.getQueryParameter("arg1");
        }

        webView.loadUrl("file:///android_asset/js2java.html");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new JsInterface(),"control");
        webView.setWebViewClient(new WebviewClient());
        webView.setWebChromeClient(new WebviewChromeClient());

    }
    class WebviewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {


           // view.loadUrl("http://baidu.com");
            view.loadUrl(url);
//            if(url.startsWith("file")){
//                startActivity(new Intent(Web2JS.this, HtmlAppActivity.class));
//            }

            return true;//webview对于这个请求是否处理，不拦截就返回false
        }


    }
    class WebviewChromeClient extends WebChromeClient{

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            Web2JS.this.setTitle(test1);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
           // Web2JS.this.setTitle(title);
        }

        @Override
        public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
//            final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
//
//            builder.setTitle("对话框")
//                    .setMessage(message)
//                    .setPositiveButton("确定",new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog,int which) {
//                            result.confirm();
//                        }
//                    })
//                    .setNeutralButton("取消", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            result.cancel();
//                        }
//                    });
//            builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
//                @Override
//                public void onCancel(DialogInterface dialog) {
//                    result.cancel();
//                }
//            });
//            // 屏蔽keycode等于84之类的按键，避免按键后导致对话框消息而页面无法再弹出对话框的问题
//            builder.setOnKeyListener(new DialogInterface.OnKeyListener() {
//                @Override
//                public boolean onKey(DialogInterface dialog, int keyCode,KeyEvent event) {
//                    Log.v("onJsConfirm", "keyCode==" + keyCode + "event="+ event);
//                    return true;
//                }
//            });
//            // 禁止响应按back键的事件
//            // builder.setCancelable(false);
//            AlertDialog dialog = builder.create();
//            dialog.show();
            result.confirm();
            return true;
            //    return super.onJsAlert(view, url, message, result);
        }

        @Override
        public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
            return super.onJsConfirm(view, url, message, result);
        }

        @Override
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
            Toast.makeText(Web2JS.this, url, Toast.LENGTH_SHORT).show();
            result.confirm("");
            return true;
        }
    }
    class JsInterface{
        @JavascriptInterface
        public void showToast(String toast) {
            Toast.makeText(Web2JS.this, toast, Toast.LENGTH_SHORT).show();
            log("show toast success");
        }

        public void log(final String msg){
            webView.post(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl("javascript: log(" + "'" + msg + "'" + ")");
                }
            });
        }
    }
}
