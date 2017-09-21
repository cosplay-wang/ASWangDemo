package as.founder.demo.wang.customerviewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import as.founder.demo.wang.R;

/**
 * Created by zhiwei.wang on 2016/12/19.
 */

public class CustomerLabelEdittext extends LinearLayout {
    private TextView textView;
    private String labelText;
    private  int labelTextSize;
    private  String labelPosition;

    public CustomerLabelEdittext(Context context, AttributeSet attrs) {
        super(context, attrs);
        int resourceId = attrs.getAttributeResourceValue(null,"labelText",0);
        if(resourceId == 0){
            labelText = attrs.getAttributeValue(null,"labelText");
        }else{
            labelText = getResources().getString(resourceId);
        }
        if(labelText == null){
            throw new RuntimeException("必须设置labelText属性");
        }



        resourceId = attrs.getAttributeResourceValue(null,"labelPosition",0);
        if(resourceId == 0){
            labelPosition = attrs.getAttributeValue(null,"labelPosition");
        }else{
            labelPosition = getResources().getString(resourceId);
        }
        if(labelPosition == null){
            labelPosition = "left";
        }



        resourceId = attrs.getAttributeResourceValue(null,"labelTextSize",0);
        if(resourceId == 0){
            labelTextSize = attrs.getAttributeIntValue(null,"labelTextSize",14);
        }else{
            labelTextSize = getResources().getInteger(resourceId);
        }

        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li;
        li = (LayoutInflater) context.getSystemService(infService);

        if("left".equals(labelPosition)){
            li.inflate(R.layout.edit_text_layout,this);
        }else{
            throw new RuntimeException("不是left");
        }
        textView = (TextView) findViewById(R.id.edit_text_left);
        textView.setText(labelText);
        textView.setTextSize(labelTextSize);



    }
}
