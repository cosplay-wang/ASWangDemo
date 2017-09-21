package as.founder.demo.wang.recyclverview;

import android.annotation.SuppressLint;
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
 * Created by zhiwei.wang on 2016/12/29.
 */

public class DownViewGroup extends LinearLayout {

    /**
     * 下拉头的View
     */
    private View header;
    /**
     * 上拉的View
     */
    private View footer;
    /**
     * 上拉头的高度
     */

    private int FooterViewHeight;
    /**
     * 下拉头的高度
     */
    private int HeaderViewHeight;
    /**
     * 下拉头中的View
     */
    private TextView header_text;
    /**
     * 上拉头中的View
     */
    private TextView footer_text;
    /**
     * 手指按下时的屏幕纵坐标
     */
    private float yDown;
    /**
     * 手指移动时的屏幕纵坐标
     */
    private float yMove;
    /**
     * 控制上拉显示标识
     */
    private boolean isGetMore = true;
    /**
     * 控制下拉显示标识
     */
    private boolean isNeedRefresh = true;
    /**
     * 拦截时下拉,上拉，正常的状态
     */
    private String PULL_STATE = "NORMAL";
    private String NORMAL = "NORMAL";
    private String UP = "UP";
    private String DOWN = "DOWN";

    private String TAG = "state";
    /**
     * 包裹的list or grid view
     */
    private AdapterView<?> mAdapterView;
    /**
     * 下拉刷新的接口
     */
    private OnHeaderRefreshListener mOnHeaderRefreshListener;

    /**
     * 上拉加载的接口
     */
    private OnFooterGetMoreListener mOnFooterGetMoreListener;
    /**
     * 加载布局的inflater
     */
    LayoutInflater mInflater;

    public DownViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        InitHeadview(context);
        // TODO Auto-generated constructor stub
    }

    /**
     * 加载下拉头,测量下拉头高度，并同时为topmargin赋值
     *
     * @param context
     */
    @SuppressLint("InflateParams")
    private void InitHeadview(Context context) {
        setOrientation(LinearLayout.VERTICAL);
        mInflater = LayoutInflater.from(context);
        header = mInflater.inflate(R.layout.pulltorefresh, null);
        header_text = (TextView) header.findViewById(R.id.down_text);
        /**
         * 没有画出来的view，不能直接拿到宽和高
         */
        measureView(header);// 作用是让头部的子布局显示出来
        HeaderViewHeight = header.getMeasuredHeight();
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
                HeaderViewHeight);
        params.topMargin = -HeaderViewHeight;
        addView(header, params);
    }

    /**
     * 加载上拉头,测量上拉头高度，并同时为topmargin赋值，但是是为下拉头设置topmargin，有思路为为上拉头设置bottommargin，
     * 但是未成功
     *
     * @param
     */
    @SuppressLint("InflateParams")
    private void InitFootview() {
        footer = mInflater.inflate(R.layout.pulltorefresh, null);
        footer_text = (TextView) footer.findViewById(R.id.down_text);
        measureView(footer);
        FooterViewHeight = footer.getMeasuredHeight();
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
                FooterViewHeight);
        /**
         * 没有必要为footer设置在底部，因为中间的list或者gridview会是match的高度，footer自然会排在屏幕之外
         */
        // params.bottomMargin = - FooterViewHeight;
        addView(footer, params);
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
     * 留给外部的设置能否下拉的控制方法
     *
     * @param refresh
     */
    public void isNeedRefresh(boolean refresh) {
        if (!refresh) {
            isNeedRefresh = false;
            // removeView(header);
        }

    }

    /**
     * 留给外部的设置能否上拉的控制方法
     *
     * @param
     */
    public void isNeedGetMore(boolean more) {
        if (!more) {
            isGetMore = false;
            // removeView(footer);
        }

    }

    /**
     * 我们一般使用View的流程是在onCreate中使用setContentView来设置要显示Layout文件或直接创建一个View
     * ，在当设置了ContentView之后系统会对这个View进行解析，然后回调当前视图View中的onFinishInflate方法
     * 。只有解析了这个View我们才能在这个View容器中获取到拥有Id的组件，
     * 同样因为系统解析完View之后才会调用onFinishInflate方法，
     * 所以我们自定义组件时可以onFinishInflate方法中获取指定子View的引用。
     *
     * 其实就是在加载完下拉头和listview之后，再加上上拉头。
     */
    @Override
    protected void onFinishInflate() {
        // TODO Auto-generated method stub
        super.onFinishInflate();
        InitFootview();
        initContentAdapterView();// 判断整个组件的结构合理与否，三层的结构，并赋值
    }

    /**
     * init AdapterView like ListView,GridView and so on;or init ScrollView
     * 组件内部的是listview或者gridview之类的
     *
     * @description hylin 2012-7-30下午8:48:12
     */
    private void initContentAdapterView() {
        int count = getChildCount();
        if (count < 3) {
            throw new IllegalArgumentException(
                    "this layout must contain 3 child views,and AdapterView or ScrollView must in the second position!");
        }
        View view = null;
        for (int i = 0; i < count - 1; ++i) {
            Log.i(TAG, " pull coubt!:" + i);
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

            if (deltaY > 0) {// 而且手指滑动的方向是向下的
                // 子view(ListView or GridView)滑动到最顶端
                View child = mAdapterView.getChildAt(0);// 拿到listview的第一个item
                if (child == null) {
                    // 如果mAdapterView中没有数据,不拦截
                    PULL_STATE = NORMAL;
                    return false;
                }
                if (mAdapterView.getFirstVisiblePosition() == 0
                        && child.getTop() == 0) {
                    PULL_STATE = DOWN;
                    return true;
                }

            } else if (deltaY < 0) {// 而且手指滑动的方向是向上的
                // 拿到最后一个item
                View lastChild = mAdapterView.getChildAt(mAdapterView
                        .getChildCount() - 1);
                if (lastChild == null) {
                    // 如果mAdapterView中没有数据,不拦截
                    PULL_STATE = NORMAL;
                    return false;
                }
                if (mAdapterView.getLastVisiblePosition() == mAdapterView
                        .getCount() - 1) {
                    PULL_STATE = UP;
                    return true;
                }
            }
        }

        PULL_STATE = NORMAL;
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                yDown = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                yMove = event.getRawY();
                float moveTo = yMove - yDown;// moveTo > 0 是向下运动,< 0是向上运动
                int move = (int) moveTo;
                if (isNeedRefresh && moveTo > 0) {
                    if (isRefreshViewScroll(move)) {
                        return true;
                    }
                }
                if (isGetMore && moveTo < 0) {
                    if (isRefreshViewScroll(move)) {
                        return true;
                    }
                }

                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return false;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // yDown = event.getRawY();,因为在拦截方法中已经得到过了
                break;
            case MotionEvent.ACTION_MOVE:
                float MoveY = event.getRawY();
                int deltaY = (int) (MoveY - yDown);
                if (deltaY != 0) {
                    float topmargin = changingHeaderViewTopMargin(deltaY);
                    if (topmargin >= 0) {
                        header_text.setText("放开刷新");
                    } else if (topmargin < 0 && topmargin > -HeaderViewHeight) {
                        header_text.setText("下拉刷新");
                    } else if (topmargin < -HeaderViewHeight
                            && topmargin > -(HeaderViewHeight + FooterViewHeight)) {
                        footer_text.setText("上拉加载更多");
                    } else if (topmargin <= -(HeaderViewHeight + FooterViewHeight)) {
                        footer_text.setText("放开加载更多");
                    }
                }
                yDown = MoveY;// 重新赋值，很重要。。在你一次由下拉变成上滑的时候。。
                break;
            case MotionEvent.ACTION_UP:
                int headerTopmargin = getHeaderTopMargin(header);
                if (headerTopmargin >= 0) {
                    // 开始刷新
                    headerRefreshing();
                } else if (headerTopmargin < 0
                        && headerTopmargin > -HeaderViewHeight) {
                    // 没有到达下拉的头部，不刷新，收回下拉头
                    setHeaderTopMargin(-HeaderViewHeight, header);
                } else if (headerTopmargin <= -HeaderViewHeight
                        && headerTopmargin > -(HeaderViewHeight + FooterViewHeight)) {
                    // 没有到达上拉的底部，不加载，收回上拉头
                    setHeaderTopMargin(-HeaderViewHeight, header);
                } else if (headerTopmargin <= -(HeaderViewHeight + FooterViewHeight)) {
                    // 开始加载
                    footerRefreshing();
                } else {
                    // 回到原始状态
                    setHeaderTopMargin(-HeaderViewHeight, header);
                }
                break;

        }

        return super.onTouchEvent(event);
    }

    /**
     * 修改Header view top margin的值 来控制下拉头和上拉头的显示
     *
     * @description
     * @param deltaY
     * @return hylin 2012-7-31下午1:14:31
     */
    private float changingHeaderViewTopMargin(int deltaY) {
        LayoutParams params = (LayoutParams) header.getLayoutParams();
        float newTopMargin = params.topMargin + deltaY * 0.5f;

        if (newTopMargin < -HeaderViewHeight) {// 上拉效果
            if (PULL_STATE == UP) {
                if (newTopMargin <= -(HeaderViewHeight + FooterViewHeight)) {
                    newTopMargin = -(HeaderViewHeight + FooterViewHeight);
                }
            } else {
                newTopMargin = -HeaderViewHeight;
            }

        } else if (newTopMargin > -HeaderViewHeight) {// 下拉效果
            if (PULL_STATE == DOWN) {
                if (newTopMargin >= 0) {
                    newTopMargin = 0;
                }
            } else {
                newTopMargin = -HeaderViewHeight;
            }

        }
        params.topMargin = (int) newTopMargin;
        header.setLayoutParams(params);
        invalidate();
        return params.topMargin;
    }

    /**
     * header refreshing
     *
     * @description hylin 2012-7-31上午9:10:12
     */
    private void headerRefreshing() {
        header_text.setText("放开刷新");
        setHeaderTopMargin(0, header);
        if (mOnHeaderRefreshListener != null) {
            mOnHeaderRefreshListener.onHeaderRefresh(this);
        }
    }

    /**
     * header refreshing
     *
     * @description hylin 2012-7-31上午9:10:12
     */
    private void footerRefreshing() {
        footer_text.setText("放开加载更多");
        setHeaderTopMargin(-(HeaderViewHeight + FooterViewHeight), header);
        if (mOnFooterGetMoreListener != null) {
            mOnFooterGetMoreListener.onFooterRefresh(this);
        }
    }

    /**
     * 获取当前header view 的topMargin
     *
     * @description
     * @return hylin 2012-7-31上午11:22:50
     */
    private int getHeaderTopMargin(View view) {
        LayoutParams params = (LayoutParams) view.getLayoutParams();
        return params.topMargin;
    }

    /**
     * 设置header view 的topMargin的值
     *
     * @description
     * @param topMargin
     *            ，为0时，说明header view 刚好完全显示出来； 为-mHeaderViewHeight时，说明完全隐藏了
     *            hylin 2012-7-31上午11:24:06
     */
    private void setHeaderTopMargin(int topMargin, View view) {
        LayoutParams params = (LayoutParams) view.getLayoutParams();
        params.topMargin = topMargin;
        view.setLayoutParams(params);
        invalidate();
    }

    /**
     * header view 完成更新后恢复初始状态
     *
     * @description hylin 2012-7-31上午11:54:23
     */
    public void onHeaderRefreshComplete() {
        header_text.setText("下拉刷新");
        setHeaderTopMargin(-HeaderViewHeight, header);

    }

    /**
     * header view 完成更新后恢复初始状态
     *
     * @description hylin 2012-7-31上午11:54:23
     */
    public void onFooterRefreshComplete() {
        footer_text.setText("加载更多");
        setHeaderTopMargin(-HeaderViewHeight, header);
    }

    public void setOnHeaderRefreshListener(
            OnHeaderRefreshListener headerRefreshListener) {
        mOnHeaderRefreshListener = headerRefreshListener;
    }

    /**
     * Interface definition for a callback to be invoked when list/grid header
     * view should be refreshed.
     */
    public interface OnHeaderRefreshListener {
        public void onHeaderRefresh(DownViewGroup view);
    }

    public void setOnFooterGetMoreListener(
            OnFooterGetMoreListener footerRefreshListener) {
        mOnFooterGetMoreListener = footerRefreshListener;
    }

    /**
     * Interface definition for a callback to be invoked when list/grid footer
     * view should be refreshed.
     */
    public interface OnFooterGetMoreListener {
        public void onFooterRefresh(DownViewGroup view);
    }
    
}
