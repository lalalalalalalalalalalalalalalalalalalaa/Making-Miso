package com.example.miso;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

public class teach_step extends Fragment {

    View view;

    TextView teach_step_title, teach_step_content;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_teach_step, container,  false );

        teach_step_title = view.findViewById(R.id.teach_step_title);
        teach_step_content = view.findViewById(R.id.teach_step_content);
        //接收teach傳過來的步驟資料，並顯示在畫面上
        getParentFragmentManager().setFragmentResultListener("teachstep", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

                String step = result.getString("step");
                String content = result.getString("content");
                teach_step_title.setText(step);
                teach_step_content.setText(content);
            }
        });
        return  view;
    }
}