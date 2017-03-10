package com.android.easyArc.view;

import com.android.easyArc.models.DoubanBook;
import com.android.mvp.base.BaseView;

import java.util.List;

/**
 * Created by Alex on 17/3/6.
 */

public interface BooksView extends BaseView {

    void showBooks(List<DoubanBook.Book> books);

    void loadMore();

}
