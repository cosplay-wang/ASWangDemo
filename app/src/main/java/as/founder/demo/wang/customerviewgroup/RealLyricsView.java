package as.founder.demo.wang.customerviewgroup;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Scroller;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by zhiwei.wang on 2018/1/15.
 * wechat 760560322
 * 作用：
 */

public class RealLyricsView extends View {

    private List<LrcEntry> mLrcEntryList = new ArrayList<>();
    private TextPaint mLrcPaint = new TextPaint();
    private float mOffsetY = 0;//
    Scroller scroller;
    private ValueAnimator mAnimator;
    int mDividerHeight = 40;
    private int mCurrentLine;

    public RealLyricsView(Context context) {
        this(context, null);
    }

    public RealLyricsView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RealLyricsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mLrcPaint.setAntiAlias(true);
        mLrcPaint.setTextSize(60);
        mLrcPaint.setTextAlign(Paint.Align.LEFT);

        scroller = new Scroller(context);
    }

    public void chooseLine(long time) {
        int line = findShowLine(time);
        if (line != mCurrentLine) {
            mCurrentLine = line;
            scrollTo(line, 1000l);
        }


    }

    /**
     * 二分法查找当前时间应该显示的行数（最后一个 <= time 的行数）
     */
    private int findShowLine(long time) {
        int left = 0;
        int right = mLrcEntryList.size();
        while (left <= right) {
            int middle = (left + right) / 2;
            long middleTime = mLrcEntryList.get(middle).getTime();

            if (time < middleTime) {
                right = middle - 1;
            } else {
                if (middle + 1 >= mLrcEntryList.size() || time < mLrcEntryList.get(middle + 1).getTime()) {
                    return middle;
                }

                left = middle + 1;
            }
        }

        return 0;
    }

    private void scrollTo(int line, long duration) {
        float offset = getOffset(line);
        endAnimation();
        Log.e("mOffsetY", "mOffsetY" + mOffsetY);
        mAnimator = ValueAnimator.ofFloat(mOffsetY, offset);
        mAnimator.setDuration(duration);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mOffsetY = (float) animation.getAnimatedValue();

                invalidate();
            }
        });
        mAnimator.start();
    }

    private void endAnimation() {
        if (mAnimator != null && mAnimator.isRunning()) {
            mAnimator.end();
        }
    }

    private float getOffset(int line) {
        if (mLrcEntryList.get(line).getOffset() == Float.MIN_VALUE) {
            float offset = getHeight() / 2;
            for (int i = 1; i <= line; i++) {
                offset -= (mLrcEntryList.get(i - 1).getHeight() + mLrcEntryList.get(i).getHeight()) / 2 + mDividerHeight;
            }
            mLrcEntryList.get(line).setOffset(offset);
        }

        return mLrcEntryList.get(line).getOffset();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float y = 0;
        canvas.translate(0, mOffsetY);

        for (int i = 0; i < mLrcEntryList.size(); i++) {
            if (i > 0) {
                y += (mLrcEntryList.get(i - 1).getHeight() + mLrcEntryList.get(i).getHeight()) / 2 + mDividerHeight;
            }
            if(i == mCurrentLine){
                mLrcPaint.setColor(Color.RED);
            }else{
                mLrcPaint.setColor(Color.parseColor("#cccccc"));
            }
            drawText(canvas, mLrcEntryList.get(i).getStaticLayout(), y);
        }
    }

    /**
     * 画一行歌词
     *
     * @param y 歌词中心 Y 坐标
     */
    private void drawText(Canvas canvas, StaticLayout staticLayout, float y) {
        canvas.save();
        canvas.translate(0, y - staticLayout.getHeight() / 2);
        staticLayout.draw(canvas);
        canvas.restore();
    }


    /**
     * 设置歌词file的地址
     *
     * @param lrcFile
     */
    public void setLyrics(File lrcFile) {
        new AsyncTask<File, Integer, List<LrcEntry>>() {
            @Override
            protected List<LrcEntry> doInBackground(File... params) {
                return LrcEntry.parseLrc(params[0]);
            }

            @Override
            protected void onPostExecute(List<LrcEntry> lrcEntries) {
                onLrcLoaded(lrcEntries);

            }

        }.execute(lrcFile);
    }


    /**
     * 加载歌词文件
     *
     * @param lrcText 歌词文本
     */
    public void setLyrics(String lrcText) {
        new AsyncTask<String, Integer, List<LrcEntry>>() {
            @Override
            protected List<LrcEntry> doInBackground(String... params) {
                return LrcEntry.parseLrc(params[0]);
            }

            @Override
            protected void onPostExecute(List<LrcEntry> lrcEntries) {

                onLrcLoaded(lrcEntries);


            }
        }.execute(lrcText);


    }


    private void onLrcLoaded(List<LrcEntry> entryList) {
        if (entryList != null && !entryList.isEmpty()) {
            mLrcEntryList.addAll(entryList);
        }

        initEntryList();
        invalidate();
    }

    private void initEntryList() {
        Collections.sort(mLrcEntryList);

        for (LrcEntry lrcEntry : mLrcEntryList) {
            lrcEntry.init(mLrcPaint, (int) getLrcWidth());
        }

        mOffsetY = getHeight() / 2;
    }

    private float getLrcWidth() {
        return getWidth();
    }
}
