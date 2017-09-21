package as.founder.demo.wang.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.widget.TextView;

import as.founder.demo.wang.R;

public class TextViewSpannableActivity extends AppCompatActivity {
   TextView myTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view_spannable);

        myTextView = (TextView) this.findViewById(R.id.textView6);

        //创建一个 SpannableString对象
        SpannableString sp = new SpannableString("这句话中有百度超链接,有高亮显示，这样，或者这样，还有斜体.");
        //设置超链接
        sp.setSpan(new URLSpan("http://www.baidu.com"), 5, 7,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置高亮样式一
        sp.setSpan(new BackgroundColorSpan(Color.RED), 17 ,19, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置高亮样式二
        sp.setSpan(new ForegroundColorSpan(Color.YELLOW),20,24,Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        //设置斜体
        sp.setSpan(new StyleSpan(android.graphics.Typeface.BOLD_ITALIC), 27, 29, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);


        BackgroundColorSpan colorSpan = new BackgroundColorSpan(Color.parseColor("#AC00FF30"));
        sp.setSpan(colorSpan, 9, sp.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        //SpannableString对象设置给TextView
        myTextView.setText(sp);




        //设置TextView可点击
        myTextView.setMovementMethod(LinkMovementMethod.getInstance());//为了超链接可以点击访问

    }
}
