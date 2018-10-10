package com.contact_list.gregantech.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;



public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder{

    public T data;
    String TAG = getClass().getSimpleName();

    BaseViewHolder(View itemView) {
        super(itemView);
    }

    public void setData(T data) {
        this.data = data;
        populateData(data);
    }

    abstract void populateData(T data);
}
