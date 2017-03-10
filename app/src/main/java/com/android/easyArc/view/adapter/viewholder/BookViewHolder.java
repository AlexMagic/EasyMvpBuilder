package com.android.easyArc.view.adapter.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.easyArc.R;
import com.android.easyArc.models.DoubanBook;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Alex on 17/3/9.
 */

public class BookViewHolder extends RecyclerView.ViewHolder {

    private Context context;

    @Bind(R.id.book_name)
    TextView textView;

    public BookViewHolder(View itemView, Context context) {
        super(itemView);

        this.context = context;

        ButterKnife.bind(this,itemView);


    }

    public void bindContentView(DoubanBook.Book book){
        if(book!=null) {
            textView.setText(book.getTitle());
        }

    }

}
