package ru.shatalin89yandex.crm.fragment;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.sql.SQLException;

import ru.shatalin89yandex.crm.R;
import ru.shatalin89yandex.crm.WorkAcitvity;


public class FClientInfo extends Fragment {

    public static final String TAG = "FClInfoTag";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fclientinfo, container, false);
    }

}