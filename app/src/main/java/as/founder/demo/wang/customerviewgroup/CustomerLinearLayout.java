package as.founder.demo.wang.customerviewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhiwei.wang on 2016/8/22.
 * 先生_不靠谱
 */
public class CustomerLinearLayout extends ViewGroup {

    public CustomerLinearLayout(Context context) {
        super(context);
    }

    public CustomerLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomerLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
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
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec) ;
      /**
         * 获得此ViewGroup上级容器为其推荐的宽和高，以及计算模式
         *
         * mode 代表布局的参数类型，三类
         * EXACTLY：表示设置了精确的值，一般当childView设置其宽、高为精确值、match_parent时，ViewGroup会将其设置为EXACTLY；
         * AT_MOST：表示子布局被限制在一个最大值内，一般当childView设置其宽、高为wrap_content时，ViewGroup会将其设置为AT_MOST；
         * UNSPECIFIED：表示子布局想要多大就多大，一般出现在AadapterView的item的heightMode中、ScrollView的childView的heightMode中；此种模式比较少见。
         * 注：上面的每一行都有一个一般，意思上述不是绝对的，对于childView的mode的设置还会和ViewGroup的测量mode有一定的关系；
         *
         */


       /**

         * 计算出所有的childView的宽和高
         */

        measureChildren(widthMeasureSpec, heightMeasureSpec);
        /**
         * 如果是wrap_content设置为我们计算的值
         * 否则：直接设置为父容器计算的值
         */
        setMeasuredDimension(widthSize,heightSize);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int childViewCount = getChildCount();
        int counttop = 0;
        int countleft = 0;
        for(int i=0;i<childViewCount;i++){
            View childView = getChildAt(i);
            View beforechildView = null;
            if(i!=0){
                beforechildView = getChildAt(i-1);
            }


            int left= 0;
            int top = 0;
            int right = 0;
            int bottom = 0;
            MarginLayoutParams cParams  = (MarginLayoutParams) childView.getLayoutParams();
            left = cParams.leftMargin;
            top = cParams.topMargin;
            right = cParams.rightMargin;
            bottom =cParams.bottomMargin;
            if(i!=0){
                countleft = countleft + left + beforechildView.getMeasuredWidth();
                counttop = counttop + top + beforechildView.getMeasuredHeight();
            }else{
                counttop = counttop + top ;
                countleft = countleft + left;

            }

            childView.layout(countleft, top, countleft+childView.getMeasuredWidth(), top+childView.getMeasuredHeight());
        }
//        int childCount = this.getChildCount();
//        for (int i = 0; i < childCount; i++) {
//            View child = this.getChildAt(i);
//            MarginLayoutParams lParams = (MarginLayoutParams) child.getLayoutParams();
//            Log.i("child",child.getWidth()+"------"+ child.getHeight());
//            child.layout(lParams.leftMargin, lParams.topMargin, lParams.leftMargin + child.getMeasuredWidth(),
//                    lParams.topMargin + child.getMeasuredHeight());
//        }
    }

}
