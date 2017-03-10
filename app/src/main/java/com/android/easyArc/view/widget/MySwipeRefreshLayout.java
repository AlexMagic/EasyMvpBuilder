package com.android.easyArc.view.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Aspsine on 2015/3/11.
 */
public class MySwipeRefreshLayout extends SwipeRefreshLayout {
    private float xDistance, yDistance, xLast, yLast;

    private LoadMoreRecyclerView loadMoreRecyclerView = null;


    boolean isPull = true;

    public MySwipeRefreshLayout(Context context) {
        super(context);
    }

    public MySwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDistance = yDistance = 0f;
                xLast = ev.getX();
                yLast = ev.getY();
                if(loadMoreRecyclerView != null) {
                    if (loadMoreRecyclerView.ismIsFirstItemVisible()) {
                        isPull = true;
                    } else {
                        isPull = false;
                    }
                }

                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                final float curX = ev.getX();
                final float curY = ev.getY();

                xDistance += Math.abs(curX - xLast);
                yDistance += Math.abs(curY - yLast);
                xLast = curX;
                yLast = curY;


                if(loadMoreRecyclerView != null){

                    if(loadMoreRecyclerView.ismIsFirstItemVisible()&& isPull){
                        if(loadMoreRecyclerView.getLoadingMore()){
                            return false;
                        }else
                            return super.onInterceptTouchEvent(ev);
                    }
                    else {
                        return false;
                    }
                }
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean canChildScrollUp() {

        return super.canChildScrollUp();
    }

    public void setLoadMoreRecyclerView(
        LoadMoreRecyclerView loadMoreRecyclerView) {
        this.loadMoreRecyclerView = loadMoreRecyclerView;
    }
}
