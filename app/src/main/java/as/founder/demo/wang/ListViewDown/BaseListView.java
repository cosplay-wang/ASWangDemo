package as.founder.demo.wang.ListViewDown;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import as.founder.demo.wang.R;

/**
 * Created by zhiwei.wang on 2016/7/5.
 */
public class BaseListView extends ListView implements AbsListView.OnScrollListener {

    int headHeight, headWidth;
    View headView;
    TextView headTv;
    boolean changeHead = true;
    float downY, moveY, upY;
    private ListViewRefreshListener mListViewRefreshListener;

    public BaseListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        headView = LayoutInflater.from(context).inflate(R.layout.down_list_item_layout, null);
        headTv = (TextView) headView.findViewById(R.id.down_list_item_tv);
        headTv.setText("下拉刷新");
        measureView(headView);
        headHeight = headView.getMeasuredHeight();
        Log.i("YY", headHeight + ":::chushihua");
        headWidth = headView.getMeasuredWidth();
        headView.setPadding(0, -headHeight, 0, 0);
        headView.invalidate();
        addHeaderView(headView);
        setOnScrollListener(this);

    }

    public BaseListView(Context context) {
        super(context, null);

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                moveY = ev.getY();
                int moveVerM = (int) (moveY - downY);
                int moveHeight = -headHeight + moveVerM;
                changeHeadviewHeight(moveHeight);
                break;
            case MotionEvent.ACTION_UP:
                upY = ev.getY();
                int moveVerU = (int) (upY - downY);

                int upHeight = -headHeight + moveVerU;

                if (upHeight >= 0) {
                    if(mListViewRefreshListener != null){
                        reFresh();
                        mListViewRefreshListener.getMoreListView();
                    }
                } else {
                    changeHeadviewHeight(-headHeight);
                }


                headView.invalidate();


                break;
        }


        return super.onTouchEvent(ev);
    }


    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (firstVisibleItem == 0) {
            changeHead = true;
        } else {
            changeHead = false;
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    public void reFresh() {
        headTv.setText("刷新中");
        headView.setPadding(0, 0, 0, 0);
    }

    public void normalFresh() {
        headTv.setText("下拉刷新");
        headView.setPadding(0, -headHeight, 0, 0);
    }


    private void changeHeadviewHeight(int padding) {
        // int meaHeight = -headHeight+(int)Height;
//        if( meaHeight>-headHeight){
//            if(meaHeight>=0){
//                headTv.setText("放开刷新");
//
//            }
//            headView.setPadding(0,( -headHeight+(int)Height)/2, 0, 0);
//
//        }else if(meaHeight<-headHeight){
//            headTv.setText("下拉刷新");
//            headView.setPadding(0, -headHeight, 0, 0);
//
//        }

        headView.setPadding(0, padding, 0, 0);
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

    public void setListViewRefreshListener(ListViewRefreshListener listener) {
        this.mListViewRefreshListener = listener;
    }

    public interface ListViewRefreshListener {
        public void getMoreListView();
    }


}
