package as.founder.demo.wang.activity;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import as.founder.demo.wang.R;

public class HtmlAppActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html_app);
        WebView webView = (WebView) findViewById(R.id.htmlapp_web);
        webView.loadUrl("file:///android_asset/js2java.html");






    }
}
