package com.android.easyArc.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.android.easyArc.R;
import com.android.easyArc.models.DoubanBook;
import com.android.easyArc.view.adapter.viewholder.BookViewHolder;
import com.android.mvp.utils.Log;

import java.util.List;

/**
 * Created by Alex on 17/3/9.
 */

public class DoubanBooksAdapter extends BaseRecyclerAdapter<DoubanBook.Book> {

    private Context context;

    public DoubanBooksAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateContentViewHolder(ViewGroup parent, int viewType) {
        View itemView ;

        switch (viewType){
            case Type.TYPE_CONTENT:
                itemView = inflater.inflate(R.layout.item_book,parent,false);
                BookViewHolder viewHolder = new BookViewHolder(itemView,context);
                return viewHolder;
            default:
                return null;
        }

    }

    @Override
    public void onBindContentViewHolder(RecyclerView.ViewHolder holder, int position) {
        Item item = item_list.get(position);

        switch (item.getType()){
            case Type.TYPE_CONTENT:
                ((BookViewHolder)holder).bindContentView(((DoubanBookItem)item).book);
                break;
        }
    }


    @Override
    public void bind(List<com.android.easyArc.models.DoubanBook.Book> lists) {
        if(lists==null || lists.size()==0){
            notifyDataSetChanged();
            return;
        }

        DoubanBookItem item ;

        for(int i=0;i<lists.size();i++){
            item = new DoubanBookItem();
            item.setType(Type.TYPE_CONTENT);
            item.book = lists.get(i);
            item_list.add(item);
        }

        Log.d("size -- :"+item_list.size());

        notifyDataSetChanged();
    }


    public class DoubanBookItem extends Item{
        public com.android.easyArc.models.DoubanBook.Book book;
    }
}
