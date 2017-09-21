package as.founder.demo.wang.customerviewgroup;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zhiwei.wang on 2016/8/23.
 * 先生_不靠谱
 */
public class CustomerFlowViewGroup extends ViewGroup {
    FlowOnClicker flowOnClicker; int posi;

    public CustomerFlowViewGroup(Context context) {
        super(context);
    }

    public CustomerFlowViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomerFlowViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 返回MarginLayoutParams的实例，这样就为我们的ViewGroup指定了其LayoutParams为MarginLayoutParams。
     *
     * 支持margin属性
     */

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
       // super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 获得它的父容器为它设置的测量模式和大小
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        // 如果是warp_content情况下，记录宽和高
        int width = 0;
        int height = 0;
        // 记录每一行的宽度，width不断取最大宽度

        int lineWidth = 0;
        // 每一行的高度，累加至height

        int lineHeight = 0;

        int cCount = getChildCount();

        // 遍历每个子元素
        for (int i = 0; i < cCount; i++)
        {
            View child = getChildAt(i);
            // 测量每一个child的宽和高
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            // 得到child的lp
            MarginLayoutParams lp = (MarginLayoutParams) child
                    .getLayoutParams();
            // 当前子空间实际占据的宽度
            int childWidth = child.getMeasuredWidth() + lp.leftMargin
                    + lp.rightMargin;
            // 当前子空间实际占据的高度
            int childHeight = child.getMeasuredHeight() + lp.topMargin
                    + lp.bottomMargin;
            /*
             * 如果加入当前child，则超出最大宽度，则的到目前最大宽度给width，类加height 然后开启新行
             * */

            if (lineWidth + childWidth > sizeWidth)
            {
                width = Math.max(lineWidth, childWidth);// 取最大的
                lineWidth = childWidth; // 重新开启新行，开始记录
                // 叠加当前高度，
                height += lineHeight;
                // 开启记录下一行的高度
                lineHeight = childHeight;
            } else
            // 否则累加值lineWidth,lineHeight取最大高度
            {
                lineWidth += childWidth;
                lineHeight = Math.max(lineHeight, childHeight);
            }
            // 如果是最后一个，则将当前记录的最大宽度和当前lineWidth做比较
            if (i == cCount - 1)
            {
                width = Math.max(width, lineWidth);
                height += lineHeight;
            }

        }
        setMeasuredDimension((modeWidth == MeasureSpec.EXACTLY) ? sizeWidth
                : width, (modeHeight == MeasureSpec.EXACTLY) ? sizeHeight
                : height);
//        /**
//
//         * 计算出所有的childView的宽和高
//         */
//
//        measureChildren(widthMeasureSpec, heightMeasureSpec);
//        /**
//         * 如果是wrap_content设置为我们计算的值
//         * 否则：直接设置为父容器计算的值
//         */
//        setMeasuredDimension(sizeWidth,sizeHeight);
    }

    public void setData(List<String> datalists,Context context){

        for(int i=0;i<datalists.size();i++){
            posi = i;
           final  LinearLayout lin = new LinearLayout(context);
            lin.setTag(i);
            LinearLayout.LayoutParams param;
            if(datalists.get(i).length()<=4) {

                 param = new LinearLayout.LayoutParams(510, LayoutParams.WRAP_CONTENT);//定义布局管理器的参数
            }else{
                param = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);//定义布局管理器的参数
            }
                param.setMargins(5, 5, 5, 5);
                lin.setOrientation(LinearLayout.HORIZONTAL);//所有组件垂直摆放


                View view = new View(context);
                LinearLayout.LayoutParams paramimage = new LinearLayout.LayoutParams(10, 80);//定义布局管理器的参数
                paramimage.setMargins(0, 5, 5, 5);
                view.setBackgroundColor(Color.parseColor("#eb541a"));

                lin.addView(view, paramimage);


                    TextView tv = new TextView(context);
                    tv.setText(datalists.get(i));
                    tv.setGravity(Gravity.CENTER);
                    tv.setTextSize(24);
                    tv.setLines(1);
            LinearLayout.LayoutParams params;
            if(datalists.get(i).length()<=4) {

                 params = new LinearLayout.LayoutParams(
                        500, LinearLayout.LayoutParams.WRAP_CONTENT);
            }else{
                params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            }


                    params.setMargins(20, 5, 5,20);
                    tv.setLayoutParams(params);
                    lin.addView(tv, params);

            lin.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    flowOnClicker.FlowOnClick((int)lin.getTag());
                }
            });
                addView(lin, param);
            }
        invalidate();
        }

  public  interface  FlowOnClicker{
      void FlowOnClick(int position);
  }
    public void setFlowOnClicker(FlowOnClicker flowOnClicker){
        this.flowOnClicker = flowOnClicker;
    }




    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int viewCount = getChildCount();
        int width = getWidth();
        int counttop = 0;
        int countwidth = 0;
       if(viewCount>0) {

           for (int i = 0; i < viewCount; i++) {
               View childView = getChildAt(i);
               MarginLayoutParams lp = (MarginLayoutParams)childView
                       .getLayoutParams();

               //lp.setMargins(5, 5, 5, 5);
               int childWidth = childView.getMeasuredWidth();
               int childHeight = childView.getMeasuredHeight();

               int left = 0;
               int top = 0;
               int right = 0;
               int bottom = 0;
               left = lp.leftMargin;
               top = lp.topMargin;
               right = lp.rightMargin;
               bottom = lp.bottomMargin;


               if ((countwidth + left + childWidth + right) > width) {

                   countwidth = 0;//换行，把left置零
                   counttop = counttop + top + bottom + childHeight;

               }

               if (countwidth == 0) {
                   countwidth = countwidth + left;
                   counttop = counttop + top;
               }

               childView.layout(countwidth, counttop, (countwidth + childWidth), (counttop + childHeight));
               countwidth = countwidth + right + childWidth;


           }

       }
    }
}
