package com.contact_list.gregantech.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import com.contact_list.gregantech.R;
import com.contact_list.gregantech.adapter.listener.BaseRecyclerAdapterListener;
import com.contact_list.gregantech.pojo.PhoneNumber;


public class PhoneNumberViewHolder extends BaseViewHolder<PhoneNumber> implements View.OnClickListener {
    private BaseRecyclerAdapterListener<PhoneNumber> listener;
    private TextView tvName;
    private TextView tvPhoneNumber;

    public PhoneNumberViewHolder(View itemView, BaseRecyclerAdapterListener<PhoneNumber> listener) {
        super(itemView);
        this.listener = listener;

        bindHolder();
    }

    private void bindHolder() {
        tvName = itemView.findViewById(R.id.tvUserName);
        tvPhoneNumber = itemView.findViewById(R.id.tvMobileNumber);

    }

    @Override
    void populateData(PhoneNumber data) {
        tvName.setText(data.getName());
        tvPhoneNumber.setText(data.getPhoneNumber());
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.parent:
                break;
            default:
                break;
        }
    }
}
