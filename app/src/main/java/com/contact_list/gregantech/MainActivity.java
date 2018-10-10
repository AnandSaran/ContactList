package com.contact_list.gregantech;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.contact_list.gregantech.adapter.PhoneNumberListAdapter;
import com.contact_list.gregantech.adapter.listener.BaseRecyclerAdapterListener;
import com.contact_list.gregantech.pojo.PhoneNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity implements BaseRecyclerAdapterListener<PhoneNumber> {
    private TreeMap<String, PhoneNumber> treemap = new TreeMap<String, PhoneNumber>();
    private String TAG = MainActivity.class.getSimpleName();
    private RecyclerView rvPhoneList;
    public static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvPhoneList = findViewById(R.id.rvPhoneList);
        requestContacts();



    }

    private void requestContacts() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
        } else {
            getContacts();
            setAdapter();

            //showContacts();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getContacts();
                setAdapter();
            } else {
                Log.e("Permissions", "Access denied");
            }
        }
    }

    private void setAdapter() {
        if (treemap != null && treemap.size() > 0) {
            List<PhoneNumber> list = new ArrayList<PhoneNumber>(treemap.values());

            Collections.sort(list, new Comparator<PhoneNumber>() {
                public int compare(PhoneNumber obj1, PhoneNumber obj2) {
                    // ## Descending order
                    return obj1.getName().compareTo(obj2.getName()); // Ascending by pageposition

                }
            });

            rvPhoneList.setLayoutManager(new LinearLayoutManager(this));
            rvPhoneList.setItemAnimator(new DefaultItemAnimator());
            rvPhoneList.setAdapter(new PhoneNumberListAdapter(list, this));
        }
    }

    private void getContacts() {
        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        while (phones.moveToNext()) {
            String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            PhoneNumber number = new PhoneNumber();
            number.setName(name);
            number.setPhoneNumber(phoneNumber);
            if (phoneNumber != null) {
                treemap.put(phoneNumber, number);
            }

        }

        phones.close();
    }

    @Override
    public void onClickItem(PhoneNumber data) {

    }

    @Override
    public void onClickItem(View itemView, PhoneNumber data) {

    }
}
