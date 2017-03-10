package com.android.easyArc.view.activity;

import android.content.Context;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.easyArc.R;
import com.android.easyArc.models.DoubanBook;
import com.android.easyArc.presenter.SearchBooksPresenter;
import com.android.easyArc.view.BooksView;
import com.android.easyArc.view.adapter.DoubanBooksAdapter;
import com.android.easyArc.view.widget.LoadMoreRecyclerView;
import com.android.easyArc.view.widget.MySwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements BooksView{

    @Bind(R.id.swipeRefreshLayout)
    MySwipeRefreshLayout mSwipeRefreshLayout;

    @Bind(R.id.recyclerView)
    LoadMoreRecyclerView recyclerView;

    private int mPageNo = 0;

    private SearchBooksPresenter presenter;

    private DoubanBooksAdapter adapter;
    private ArrayList<DoubanBook.Book> list = new ArrayList<>();

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        presenter = new SearchBooksPresenter(this);
        adapter = new DoubanBooksAdapter(this);

        initView();

    }

    public void initView() {
        mSwipeRefreshLayout.setLoadMoreRecyclerView(recyclerView);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);

        mSwipeRefreshLayout.setOnRefreshListener(onRefreshListener);

        recyclerView.setLayoutManager(new LinearLayoutManager(context()));
        recyclerView.addOnScrollListener(new LoadMoreRecyclerView.onLoadMoreScrollListener());
        recyclerView.setAdapter(adapter);

        recyclerView.setOnLoadMoreListener(new LoadMoreRecyclerView.onLoadMoreListener() {
            @Override public void onLoadMore() {
                loadMore();
            }

            @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

            }
        });


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showLoading();
                presenter.getDoubanBooks(mPageNo,"python入门");
            }
        },1000);
    }

    //下拉刷新
    private SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override public void onRefresh() {
        presenter.getDoubanBooks(mPageNo,"python入门");
        }
    };


    @Override
    public void showBooks(final List<DoubanBook.Book> books) {
        if(books!=null){
            if(mPageNo == 0){
                mPageNo = 0;
                adapter.clearList();
                list.clear();
            }

            mPageNo++;

            handler.post(new Runnable() {
                @Override public void run() {
                    if(books!=null) {
                        if (books.size() < 50) {
                            adapter.setNoMoreRefesh(true);
                        } else {
                            adapter.setNoMoreRefesh(false);
                        }
                    }else{
                        adapter.setNoMoreRefesh(true);
                    }
                    recyclerView.setLoadingMore(false);
                }
            });

            list.addAll(books);

            adapter.bind(books);
        }else{
            Toast.makeText(this,"系统繁忙，请重试获取",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void loadMore() {
        if(!adapter.isNoMoreRefesh()){
            recyclerView.setLoadingMore(true);

        }else{
            recyclerView.setLoadingMore(false);
        }
    }

    @Override
    public void setPresenter(Object presenter) {

    }

    @Override
    public void showLoading() {
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void hideLoading() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public Context context() {
        return this;
    }
}
