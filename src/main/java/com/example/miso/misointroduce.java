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
        //建立Spannable設定開頭兩個字比較大，其他自正常大小
        String content = getString(R.string.misointriduce);
        SpannableString ss = new SpannableString(content);
        AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(15, true);
        ss.setSpan(absoluteSizeSpan, 2, content.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        text_sapn.setText(ss);
        return  view;
    }


}