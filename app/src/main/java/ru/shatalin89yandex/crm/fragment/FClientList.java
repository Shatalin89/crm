package ru.shatalin89yandex.crm.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.sql.SQLException;

import ru.shatalin89yandex.crm.R;
import ru.shatalin89yandex.crm.WorkAcitvity;


public class FClientList extends Fragment {

    public static final String TAG = "FClListTag";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fclientlist, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        WorkAcitvity wa=(WorkAcitvity)getActivity();

        try {
            wa.getClientList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



