package com.android.easyArc.presenter;

import android.content.Context;

import com.android.easyArc.models.DoubanBook;
import com.android.easyArc.net.HttpRequestApi;
import com.android.easyArc.view.BooksView;
import com.android.mvp.base.BasePresenter;
import com.android.mvp.interactor.DefaultSubscriber;
import com.android.mvp.interactor.DefaultUseCase;
import com.android.mvp.net.okhttp.RequestParams;

/**
 * Created by Alex on 17/3/6.
 */

public class SearchBooksPresenter implements BasePresenter {

    private BooksView view;
    private DefaultUseCase<DoubanBook> defaultUseCase;

    public SearchBooksPresenter(BooksView view){
        this.view = view;
    }

    @Override
    public void onPresenter(Context context) {

    }

    public void getDoubanBooks(int start , String q){
        RequestParams params = new RequestParams(RequestParams.ConnType.GET);
        params.put("start",start);
        params.put("q",q);
        params.put("count",50);

        defaultUseCase = new DefaultUseCase<DoubanBook>(HttpRequestApi.DOUBAN_BOOKS,params);
        defaultUseCase.execute(new DoubanBookSubscriber(),DoubanBook.class);
    }


    private final class DoubanBookSubscriber extends DefaultSubscriber<DoubanBook>{

        @Override
        public void onCompleted() {
            super.onCompleted();
            view.hideLoading();
        }

        @Override
        public void onSuccess(DoubanBook doubanBook) {
            super.onSuccess(doubanBook);
            view.showBooks(doubanBook.getBooks());
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            view.hideLoading();
        }
    }



}
