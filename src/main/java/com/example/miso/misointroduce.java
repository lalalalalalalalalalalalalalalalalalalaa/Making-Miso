package com.example.miso;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class misointroduce extends Fragment {

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_misointroduce, container, false);
        TextView text_sapn;

        text_sapn = view.findViewById(R.id.text_span);


        String content = getString(R.string.misointriduce);
        SpannableString ss = new SpannableString(content);
        //設定字體大小為12，true表示單位是dp，預設是false，表示單位是px
        AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(15, true);
        //窗外的麻雀之後的文字全部改成12dp
        ss.setSpan(absoluteSizeSpan, 2, content.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        text_sapn.setText(ss);


        return  view;
    }


}