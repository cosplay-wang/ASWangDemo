package as.founder.demo.wang.custometextview;

import android.content.Context;
import android.graphics.Point;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by zhiwei.wang on 2017/3/20.
 */

public class ViewDragHelper1View extends LinearLayout {
    private ViewDragHelper viewDragHelper;
    private Point mAutoBackOriginPos = new Point();
    View view;
    public ViewDragHelper1View(Context context) {
        super(context);
        init();
    }

    public ViewDragHelper1View(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ViewDragHelper1View(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ViewDragHelper1View(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }
    private void init(){
        viewDragHelper = ViewDragHelper.create(this,new ViewDragHelperCallBack());
    }
    class ViewDragHelperCallBack extends ViewDragHelper.Callback
    {
        /**
         * 尝试捕获子view，一定要返回true
         * @param  child 尝试捕获的view
         * @param  pointerId 指示器id？
         * 这里可以决定哪个子view可以拖动
         */
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return true;
        }
        /**
         * 处理水平方向上的拖动
         * @param  child 被拖动到view
         * @param  left 移动到达的x轴的距离
         * @param  dx 建议的移动的x距离
         */
        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            // 两个if主要是为了让viewViewGroup里
            Log.i("horizontal",getPaddingLeft() +"----" +left +"---"+getWidth()+"---"+child.getWidth());
            if(getPaddingLeft() > left) {
                return getPaddingLeft();
            }

            if(getWidth() - child.getWidth() < left) {
                return getWidth() - child.getWidth();
            }

            return left;
        }
        /**
         *  处理竖直方向上的拖动
         * @param  child 被拖动到view
         * @param  top 移动到达的y轴的距离
         * @param  dy 建议的移动的y距离
         */
        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            if(getPaddingTop()>top){
                return  getPaddingTop();
            }
            if(getHeight()-child.getHeight()<top){
                return  getHeight() - child.getHeight();
            }
            return top;
        }

        @Override
        public int getViewHorizontalDragRange(View child) {
            Log.i("horizontal",getMeasuredWidth()- child.getMeasuredWidth()+"----------");
            return getMeasuredWidth()- child.getMeasuredWidth();
        }



        @Override
        public void onViewDragStateChanged(int state) {
            switch (state) {
                case ViewDragHelper.STATE_DRAGGING:  // 正在被拖动
                    break;
                case ViewDragHelper.STATE_IDLE:  // view没有被拖拽或者 正在进行fling/snap
                    break;
                case ViewDragHelper.STATE_SETTLING: // fling完毕后被放置到一个位置
                    break;
            }
            super.onViewDragStateChanged(state);
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            viewDragHelper.settleCapturedViewAt(mAutoBackOriginPos.x, mAutoBackOriginPos.y);
            invalidate();
        }
    }



    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_DOWN:
                viewDragHelper.cancel(); // 相当于调用 processTouchEvent收到ACTION_CANCEL
                break;
        }


        /**
         * 检查是否可以拦截touch事件
         * 如果onInterceptTouchEvent可以return true 则这里return true
         */
        return viewDragHelper.shouldInterceptTouchEvent(event);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mAutoBackOriginPos.x = view.getLeft();
        mAutoBackOriginPos.y = view.getTop();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        /**
         * 处理拦截到的事件
         * 这个方法会在返回前分发事件
         */
        viewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        view = getChildAt(0);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(viewDragHelper.continueSettling(true))
        {
            invalidate();
        }
    }
}
