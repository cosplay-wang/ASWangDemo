package as.founder.demo.wang.recyclverview;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by zhuguohui on 2016/11/9.
 */

public class HorizontalPageLayoutManager extends RecyclerView.LayoutManager implements PageDecorationLastJudge {
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return null;
    }

    int totalHeight = 0;
    int totalWidth = 0;
    int offsetY = 0;
    int offsetX = 0;

    public HorizontalPageLayoutManager(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.onePageSize = rows * columns;
    }

    @Override
    public boolean canScrollHorizontally() {
        return true;
    }

    @Override
    public boolean canScrollVertically() {
        return super.canScrollVertically();
    }


    /**
     * scrollHorizontallyBy() & scrollVerticallyBy()

     在这里你应该实现 content 移动的逻辑。RecyclerView 已经处理了 scrolling 和 flinging (注：Fling: Gross gesture, no on-screen target) 触摸操作，
     不需要处理 MotionEvents 或者 GestureDetectors 这些麻烦事。 你只需要完成下面这三个任务：

     将所有的子视图移动适当的位置 (对的，你得自己做这个)。
     决定移动视图后 添加/移除 视图。
     返回滚动的实际距离。框架会根据它判断你是否触碰到边界。
     */

    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        detachAndScrapAttachedViews(recycler);//recyclerview的缓存分为两级，数据不需要变的，scrap缓存,


        ///Scrap中文就是废料的意思，Recycle对应是回收的意思。这两个缓存有什么作用呢？
        // 首先Scrap缓存是指里面缓存的View是接下来需要用到的，即里面的绑定的数据无需更改，
        // 可以直接拿来用的，是一个轻量级的缓存集合；而Recycle的缓存的View为里面的数据需要重新绑定，
        // 即需要通过Adapter重新绑定数据。

        /**
         * 通常来说， 如果你想要临时整理并且希望稍后在同一布局中重新使用某个 view 的话，
         * 可以对它调用 detachAndScrapView() 。如果基于当前布局 你不再需要某个 view 的话，
         * 对其调用 removeAndRecycleView()。
         */

/**
 * 主要是在我们的代码执行结束之前，我们需要反复去将View移除并且马上又要添加进去时，选择Datach方式，
 * 比如：当我们对View进行重新排序的时候，可以选择Detach，因为屏幕上显示的就是这些position对应的View，
 * 我们并不需要重新去绑定数据，这明显可以提高效率。使用Detach方式可以通过函数detachAndScrapView()实现。
 * 而使用Remove的方式，是当View不在屏幕中有任何显示的时候，你需要将它Remove掉，以备后面循环利用。可以通过函数removeAndRecycleView()实现。
 */

        int newX = offsetX + dx;
        int result = dx;
        if (newX > totalWidth) {
            result = totalWidth - offsetX;
        } else if (newX < 0) {
            result = 0 - offsetX;
        }
        offsetX += result;
        offsetChildrenHorizontal(-result);
        recycleAndFillItems(recycler, state);
        return result;
    }

    private SparseArray<Rect> allItemFrames = new SparseArray<>();

    private int getUsableWidth() {
        return getWidth() - getPaddingLeft() - getPaddingRight();
    }

    private int getUsableHeight() {
        return getHeight() - getPaddingTop() - getPaddingBottom();
    }

    int rows = 0;
    int columns = 0;
    int pageSize = 0;
    int itemWidth = 0;
    int itemHeight = 0;
    int onePageSize = 0;
    int itemWidthUsed;
    int itemHeightUsed;


    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {

        if (getItemCount() == 0) {
            removeAndRecycleAllViews(recycler);
            return;
        }
        if (state.isPreLayout()) {
            return;
        }
        //获取每个Item的平均宽高
        itemWidth = getUsableWidth() / columns;
        itemHeight = getUsableHeight() / rows;

        //计算宽高已经使用的量，主要用于后期测量
        itemWidthUsed = (columns - 1) * itemWidth;
        itemHeightUsed = (rows - 1) * itemHeight;

        //计算总的页数
        pageSize = getItemCount() / onePageSize + (getItemCount() % onePageSize == 0 ? 0 : 1);

        //计算可以横向滚动的最大值
        totalWidth = (pageSize - 1) * getWidth();

        //分离view
        detachAndScrapAttachedViews(recycler);

        int count = getItemCount();
        for (int p = 0; p < pageSize; p++) {
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < columns; c++) {
                    int index = p * onePageSize + r * columns + c;
                    if (index == count) {
                        //跳出多重循环
                        c = columns;
                        r = rows;
                        p = pageSize;
                        break;
                    }

                    View view = recycler.getViewForPosition(index);
                    addView(view);
                    //测量item
                    measureChildWithMargins(view, itemWidthUsed, itemHeightUsed);

                    int width = getDecoratedMeasuredWidth(view);
                    int height = getDecoratedMeasuredHeight(view);
                    //记录显示范围
                    Rect rect = allItemFrames.get(index);
                    if (rect == null) {
                        rect = new Rect();
                    }
                    int x = p * getUsableWidth() + c * itemWidth;
                    int y = r * itemHeight;
                    rect.set(x, y, width + x, height + y);
                    allItemFrames.put(index, rect);


                }
            }
            //每一页循环以后就回收一页的View用于下一页的使用
            removeAndRecycleAllViews(recycler);
        }

        recycleAndFillItems(recycler, state);
    }

    @Override
    public void onDetachedFromWindow(RecyclerView view, RecyclerView.Recycler recycler) {
        super.onDetachedFromWindow(view, recycler);
        offsetX = 0;
        offsetY = 0;
    }

    private void recycleAndFillItems(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (state.isPreLayout()) {
            return;
        }

        Rect displayRect = new Rect(getPaddingLeft() + offsetX, getPaddingTop(), getWidth() - getPaddingLeft() - getPaddingRight() + offsetX, getHeight() - getPaddingTop() - getPaddingBottom());
        Rect childRect = new Rect();
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            childRect.left = getDecoratedLeft(child);
            childRect.top = getDecoratedTop(child);
            childRect.right = getDecoratedRight(child);
            childRect.bottom = getDecoratedBottom(child);
            if (!Rect.intersects(displayRect, childRect)) {
                removeAndRecycleView(child, recycler);
            }
        }

        for (int i = 0; i < getItemCount(); i++) {
            if (Rect.intersects(displayRect, allItemFrames.get(i))) {
                View view = recycler.getViewForPosition(i);
                addView(view);
                measureChildWithMargins(view, itemWidthUsed, itemHeightUsed);
                Rect rect = allItemFrames.get(i);
                layoutDecorated(view, rect.left - offsetX, rect.top, rect.right - offsetX, rect.bottom);
            }
        }

    }


    @Override
    public boolean isLastRow(int index) {
        if (index >= 0 && index < getItemCount()) {
            int indexOfPage = index % onePageSize;
            indexOfPage++;
            if (indexOfPage > (rows - 1) * columns && indexOfPage <= onePageSize) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean isLastColumn(int position) {
        if (position >= 0 && position < getItemCount()) {
            position++;
            if (position % columns == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isPageLast(int position) {
        position++;
        return position % onePageSize == 0;
    }
}
