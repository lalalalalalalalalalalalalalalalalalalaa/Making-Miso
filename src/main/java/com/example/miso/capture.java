package com.example.miso;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class capture extends Fragment {

    View view;
    ImageView cameraView;
    Bitmap image;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_capture, container,  false );
        cameraView = view.findViewById(R.id.cameraView);
        image = getArguments().getParcelable("sentimage");
        cameraView.setImageBitmap(image);
        //設定分享按鈕
        FloatingActionButton btn_capture = view.findViewById(R.id.btn_capture);
        btn_capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_STREAM, image);
                sendIntent.setType("image/*");
                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(Intent.createChooser(shareIntent, "Share image to"));
            }
        });
        //TODO:要在實機上測試能否分享圖片

        return  view;
    }
}