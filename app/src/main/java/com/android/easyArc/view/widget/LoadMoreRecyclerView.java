package com.android.easyArc.view.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.android.easyArc.view.adapter.BaseRecyclerAdapter;


/**
 * Created by Aspsine on 2015/3/20.
 * <p/>
 * be careful! current version just support LinearLayoutManager
 * <p/>
 * Version 1.0 beta
 */
public class LoadMoreRecyclerView extends RecyclerView {
    private static final String TAG = LoadMoreRecyclerView.class.getSimpleName();
    private boolean mIsLoadingMore = false;
    private static boolean mIsFirstItemVisible = true;
    private onLoadMoreListener mListener;

    private BaseRecyclerAdapter adapter;

    private boolean isRefreshing = false;

    public LoadMoreRecyclerView(Context context) {
        this(context, null);
    }

    public LoadMoreRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public LoadMoreRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    void init() {

        this.setOnScrollListener(new onLoadMoreScrollListener());
    }

    public static class onLoadMoreScrollListener extends OnScrollListener {

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            LoadMoreRecyclerView view = (LoadMoreRecyclerView) recyclerView;
            onLoadMoreListener onLoadMoreListener = view.getOnLoadMoreListener();

            //onLoadMoreListener.onScrolled(recyclerView, dx, dy);

            LinearLayoutManager layoutManager = (LinearLayoutManager) view.getLayoutManager();
            int lastVisibleItem = layoutManager.findLastCompletelyVisibleItemPosition();
            int itemCount = layoutManager.getItemCount();
            int firstVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition();

            if(firstVisibleItem > 0){
                mIsFirstItemVisible = false;
            }else{
                mIsFirstItemVisible = true;
            }

            if (lastVisibleItem >= itemCount - 1 && !view.getLoadingMore() && itemCount>=50) {
                if(!view.isRefreshing() ){
                    if(onLoadMoreListener!=null)
                        onLoadMoreListener.onLoadMore();
                }
                //super.onScrolled(recyclerView, dx, dy);
            } else {
                super.onScrolled(recyclerView, dx, dy);
            }
        }
    }


    public void setOnLoadMoreListener(onLoadMoreListener listener) {
        this.mListener = listener;
        init();
    }

    public onLoadMoreListener getOnLoadMoreListener() {
        return this.mListener;
    }



    //TODO 上拉加载刷新超时或者异常处理 ，增加参数isError
    public void setLoadingMore(boolean isLoading) {
        if(isLoading){
            adapter.addFooterView();

            if(adapter.isNoMoreRefesh()) {
                adapter.removeFooterView();
            }

        }else{
            adapter.removeFooterView();

            if(adapter.isNoMoreRefesh()) {
                adapter.addFooterView();
            }
        }
        mIsLoadingMore = isLoading;
    }

    public boolean ismIsFirstItemVisible() {
        return mIsFirstItemVisible;
    }

    public static void setmIsFirstItemVisible(boolean mIsFirstItemVisible) {
        LoadMoreRecyclerView.mIsFirstItemVisible = mIsFirstItemVisible;
    }

    public void setmIsLoadingMore(boolean mIsLoadingMore) {
        this.mIsLoadingMore = mIsLoadingMore;
    }

    public boolean getLoadingMore() {
        return mIsLoadingMore;
    }

    public interface onLoadMoreListener {
        public void onLoadMore();

        public void onScrolled(RecyclerView recyclerView, int dx, int dy);
    }

    @Override public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);

        this.adapter = (BaseRecyclerAdapter) adapter;

    }


    @Override
    public void setLayoutManager(LayoutManager layout) {
        if (layout instanceof LinearLayoutManager) {
            super.setLayoutManager(layout);
        } else {
            throw new IllegalArgumentException("LoadMoreRecyclerView must have a LinearLayoutManager!");
        }
    }

    public boolean isRefreshing() {
        return isRefreshing;
    }

    public void setIsRefreshing(boolean isRefreshing) {
        this.isRefreshing = isRefreshing;
    }
}
