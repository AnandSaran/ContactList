package com.contact_list.gregantech.adapter.listener;

import android.view.View;


public interface BaseRecyclerAdapterListener<T> {

    void onClickItem(T data);
    void onClickItem(View itemView, T data);


}
