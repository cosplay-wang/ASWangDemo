package as.founder.demo.wang.viewdown;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import as.founder.demo.wang.R;

/**
 * Created by zhiwei.wang on 2016/7/6.
 */
public class PullToRefresh extends LinearLayout{
    View headView;
    TextView headTV;
    int headHeight;

    float downY,moveY,upY;
    AdapterView mAdapterView;

    OnHeaderRefreshListener mOnHeaderRefreshListener;

    public PullToRefresh(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayout(context);
    }

    public PullToRefresh(Context context) {
        super(context,null);

    }
    private void initLayout(Context context){
        setOrientation(LinearLayout.VERTICAL);
        headView = LayoutInflater.from(context).inflate(R.layout.down_list_item_layout,null);
        headTV = (TextView) headView.findViewById(R.id.down_list_item_tv);
        headTV.setText("下拉头部");
        measureView(headView);
        headHeight = headView.getMeasuredHeight();
        headView.setPadding(0,-headHeight,0,0);
        addView(headView);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch(ev.getAction()){

            case MotionEvent.ACTION_DOWN:
                downY = ev.getY();
                Log.i("yy","start："+downY);
                break;
            case MotionEvent.ACTION_MOVE:
                moveY = ev.getY();
                int moveYM = (int)(moveY-downY);
               if(moveYM>0){
                   if(isRefreshViewScroll(moveYM)){
                       return true;
                   }

               }else if(moveYM<=0){
                   return false;
               }
                break;

            case MotionEvent.ACTION_UP:
                upY = ev.getY();
                int moveYU = (int)(upY-downY);
                int paddingU = - headHeight + moveYU;
                if(paddingU >=0){

                }else{
                    changeHeadViewPadding(-headHeight);
                }



                Log.i("yy","up："+upY);

                break;

        }


        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch(event.getAction()){

            case MotionEvent.ACTION_DOWN:
                downY = event.getY();
                Log.i("yy","start："+downY);
                break;
            case MotionEvent.ACTION_MOVE:
                moveY = event.getY();
                int moveYM = (int)(moveY-downY);
                int paddingM = - headHeight + moveYM;
                changeHeadViewPadding(paddingM);
                Log.i("yy","move："+moveY);
                break;

            case MotionEvent.ACTION_UP:
                upY = event.getY();
                int moveYU = (int)(upY-downY);
                int paddingU = - headHeight + moveYU;
                if(paddingU >=0){
                    changeHeadViewPadding(0);
                    if(mOnHeaderRefreshListener!=null){
                        mOnHeaderRefreshListener.onHeaderRefresh(this);
                    }
                }else{
                    changeHeadViewPadding(-headHeight);
                }



                Log.i("yy","up："+upY);

                break;

        }




        return  true;
    }



    public void changeHeadViewPadding(int padding){

        headView.setPadding(0,padding,0,0);
        headView.invalidate();

    }
    public void changeHeadViewPaddingNormal(){

        headView.setPadding(0,-headHeight,0,0);
        headView.invalidate();

    }

    /**
     * 测量得到measure的高度和宽度，
     * onCreate方法执行完了，我们定义的控件才会被度量(measure)
     * 所以想要在之前得到view的信息，要自己测量
     *
     * @param child
     */
    private void measureView(View child) {
        ViewGroup.LayoutParams p = child.getLayoutParams();
        if (p == null) {
            p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width);
        int lpHeight = p.height;
        int childHeightSpec;
        if (lpHeight > 0) {
            childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight,
                    MeasureSpec.EXACTLY);
        } else {
            childHeightSpec = MeasureSpec.makeMeasureSpec(0,
                    MeasureSpec.UNSPECIFIED);
        }
        child.measure(childWidthSpec, childHeightSpec);
    }

    /**
     * init AdapterView like ListView,GridView and so on;or init ScrollView
     * 组件内部的是listview或者gridview之类的
     *
     * @description hylin 2012-7-30下午8:48:12
     */
    private void initContentAdapterView() {
        int count = getChildCount();

        View view = null;
        for (int i = 0; i < count; ++i) {
            view = getChildAt(i);
            if (view instanceof AdapterView<?>) {
                mAdapterView = (AdapterView<?>) view;
            }

        }
        if (mAdapterView == null) {
            throw new IllegalArgumentException(
                    "must contain a AdapterView or ScrollView in this layout!");
        }
    }

    /**
     * 是否应该到了父View,即PullToRefreshView滑动
     *
     * @param deltaY
     *            , deltaY > 0 是向下运动,< 0是向上运动
     * @return
     */
    private boolean isRefreshViewScroll(int deltaY) {
        // 对于ListView和GridView
        if (mAdapterView != null) {

            if (deltaY > 0) {//而且手指滑动的方向是向下的
                // 子view(ListView or GridView)滑动到最顶端
                View child = mAdapterView.getChildAt(0);//拿到listview的第一个item
                if (child == null) {
                    // 如果mAdapterView中没有数据,不拦截
                    return false;
                }
                if (mAdapterView.getFirstVisiblePosition() == 0
                        && child.getTop() == 0) {
                    return true;
                }

            } else if (deltaY < 0) {//而且手指滑动的方向是向上的
                //拿到最后一个item
                View lastChild = mAdapterView.getChildAt(mAdapterView
                        .getChildCount() - 1);
                if (lastChild == null) {
                    // 如果mAdapterView中没有数据,不拦截
                    return false;
                }
                if (mAdapterView.getLastVisiblePosition() == mAdapterView
                        .getCount() - 1) {
                    return true;
                }
            }
        }

        return false;
    }



    /**
     *    我们一般使用View的流程是在onCreate中使用setContentView来设置要显示Layout文件或直接创建一个View
     *    ，在当设置了ContentView之后系统会对这个View进行解析，然后回调当前视图View中的onFinishInflate方法
     *    。只有解析了这个View我们才能在这个View容器中获取到拥有Id的组件，
     *    同样因为系统解析完View之后才会调用onFinishInflate方法，
     *    所以我们自定义组件时可以onFinishInflate方法中获取指定子View的引用。
     *
     *    其实就是在加载完下拉头和listview之后，再加上上拉头。
     */
    @Override
    protected void onFinishInflate() {
        // TODO Auto-generated method stub
        super.onFinishInflate();
        initContentAdapterView();//判断整个组件的结构合理与否，三层的结构，并赋值
    }
    public interface OnHeaderRefreshListener {
        public void onHeaderRefresh(PullToRefresh view);
    }
    public  void setHeaderRefreshListener(OnHeaderRefreshListener listener){
        this.mOnHeaderRefreshListener = listener;
    }


}
