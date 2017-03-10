package com.android.easyArc.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.android.easyArc.R;
import com.android.easyArc.view.adapter.viewholder.FooterViewHolder;
import com.android.mvp.model.BaseResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 16-9-27.
 */

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public List<Item> item_list;
    private int footer_pos = -1;
    private boolean noMoreRefesh = false;
    public LayoutInflater inflater;
    public Context context;

    public BaseRecyclerAdapter(Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);
        item_list = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        switch (viewType){
            case Type.TYPE_FOOTER:
                itemView = inflater.inflate(R.layout.load_more_view,parent,false);
                FooterViewHolder footerViewHolder = new FooterViewHolder(itemView);
                setFooterTips(footerViewHolder);
                return footerViewHolder;
            default:
                return onCreateContentViewHolder(parent,viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Item item = item_list.get(position);
        switch (item.getType()){
            case Type.TYPE_FOOTER:
                ((FooterViewHolder)holder).bindFooterView(isNoMoreRefesh());
                break;
            default:
                onBindContentViewHolder(holder , position);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return item_list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return item_list.get(position).getType();
    }

    public void addFooterView(){
        Item footer_item = new Item();
        footer_item.setType(Type.TYPE_FOOTER);
        item_list.add(footer_item);

        footer_pos = this.getItemCount()-1;

        notifyDataSetChanged();
    }

    public void removeFooterView(){
        if(getItemCount()>0) {
            if (getItemViewType(getItemCount() - 1) == Type.TYPE_FOOTER) {
                item_list.remove(getItemCount() - 1);
            } else if (footer_pos != -1 && footer_pos<getItemCount()-1 && getItemViewType(footer_pos) == Type.TYPE_FOOTER) {
                item_list.remove(footer_pos);
            }
        }

        notifyDataSetChanged();
    }

    public boolean isNoMoreRefesh() {
        return noMoreRefesh;
    }

    public void setNoMoreRefesh(boolean noMoreRefesh) {
        this.noMoreRefesh = noMoreRefesh;
    }

    public class Item{
        public int type;


        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }

    public static class Type{
        static final int TYPE_HEADER = 0;
        static final int TYPE_CONTENT = 1;
        static final int TYPE_FOOTER = 2;
        static final int TYPE_CONTENT_2 = 3;
    }

    public abstract RecyclerView.ViewHolder onCreateContentViewHolder(ViewGroup parent, int viewType);

    public abstract void bind(List<T> t);

    public abstract void onBindContentViewHolder(RecyclerView.ViewHolder holder, int position);

    public void clearList(){
        item_list.clear();
//        list.clear();
        notifyDataSetChanged();
    }

    public void setFooterTips(RecyclerView.ViewHolder holder){
    }

}
