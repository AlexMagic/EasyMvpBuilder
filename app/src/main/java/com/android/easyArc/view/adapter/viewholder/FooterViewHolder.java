package com.android.easyArc.view.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.easyArc.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chenkaining on 2016/8/11.
 */
public class FooterViewHolder extends RecyclerView.ViewHolder{

    @Bind(R.id.progress_view)
    ProgressBar progress_view;

    @Bind(R.id.tv_content)
    TextView tv_content;

    private String tips = null;

    public FooterViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);

    }

    public void bindFooterView(boolean isNoMoreView){
        if(isNoMoreView){
            setProgressVisible(false);
            if(getTips()!=null){
                tv_content.setText(getTips());
            }else{
                tv_content.setText("没有更多信息了");
            }

        }else{
            setProgressVisible(true);
            tv_content.setText("加载更多...");
        }
    }

    public void setProgressVisible(boolean isVisible){
        if(isVisible)
            progress_view.setVisibility(View.VISIBLE);
        else
            progress_view.setVisibility(View.GONE);
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getTips() {
        return tips;
    }
}
