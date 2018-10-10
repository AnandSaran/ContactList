package com.contact_list.gregantech.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.contact_list.gregantech.R;
import com.contact_list.gregantech.adapter.listener.BaseRecyclerAdapterListener;
import com.contact_list.gregantech.adapter.viewholder.PhoneNumberViewHolder;
import com.contact_list.gregantech.pojo.PhoneNumber;

import java.util.List;

public class PhoneNumberListAdapter extends BaseRecyclerAdapter<PhoneNumber, PhoneNumberViewHolder> {
    private BaseRecyclerAdapterListener<PhoneNumber> listener;

    public PhoneNumberListAdapter(List<PhoneNumber> data, BaseRecyclerAdapterListener<PhoneNumber> listener) {
        super(data);
        this.listener = listener;

    }


    @Override
    public PhoneNumberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PhoneNumberViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.inflate_phone_number, parent, false), listener);

    }
}
