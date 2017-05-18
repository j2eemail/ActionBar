package com.lxs.actionbar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/5/18.
 */

public class Tab3Fragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view;
        TextView textView;

        view = inflater.inflate(R.layout.tab_layout, container, false);
        textView = (TextView) view.findViewById(R.id.tab_layout_txt);
        textView.setText(R.string.tab_3);
        return view;
    }
}
