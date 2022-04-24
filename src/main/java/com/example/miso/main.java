package com.example.miso;

import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class main extends Fragment {

    private Dialog dlg_settings;


    public main() {

    }

    View view;
    SQLiteDatabase db;
    TextView countday;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);



        Button setting = view.findViewById(R.id.setting);
        Button complete = view.findViewById(R.id.complete);
        Button start = view.findViewById(R.id.start);
        countday = view.findViewById(R.id.countday);


        db = getActivity().openOrCreateDatabase("Miso.db", Context.MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("SELECT _id, date FROM Date", null);

        String a = "";
        int days = 0;

        if (cursor != null) {
            while (cursor.moveToNext()) {
                a = cursor.getString(1);
                days = (int)findTime(a);
                if( a.equals("1999/12/31") ){
                    start.setVisibility(View.VISIBLE);
                    complete.setVisibility(View.INVISIBLE);
                    countday.setText(getString(R.string.before_start));
                }else {
                    start.setVisibility(View.INVISIBLE);
                    complete.setVisibility(View.VISIBLE);
                    String senttext =  getString(R.string.starting1) + days + getString(R.string.starting2);
                    countday.setText(senttext);

                }
            }
        }
        //建立訊息通知
        Notification.Builder builder =new Notification.Builder(getContext());
        NotificationManager notificationManager = (NotificationManager)getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        Intent push =new Intent(getContext(),MainService.class);
        PendingIntent contentIntent = PendingIntent.getActivity(getContext(),0,push,0);
        builder
                .setContentTitle(getString(R.string.notify_ttitle))
                .setContentText(getString(R.string.notify_1_content) + days + getString(R.string.notify_2_content))
                .setContentIntent(contentIntent)
                .setTicker("新訊息")
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher_foreground))
                .setDefaults(Notification.DEFAULT_ALL);


        Notification notify = builder.build();
        if ( (days <= 180) && (days%30 == 0) ){
            notificationManager.notify(1,notify);
        }else if( findTime(a)%180 == 0 ){
            notificationManager.notify(1,notify);
        }

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dlg_settings = new Dialog(getContext());
                dlg_settings.setCancelable(false);
                dlg_settings.setContentView(R.layout.setting);
                dlg_settings.setTitle(getString(R.string.setting));
                dlg_settings.show();

                Button dlg_btn_back = dlg_settings.findViewById(R.id.dlg_btn_back);
                Button dlg_btn_twch = dlg_settings.findViewById(R.id.dlg_btn_twch);
                Button dlg_btn_chch = dlg_settings.findViewById(R.id.dlg_btn_chch);
                Button dlg_btn_eng = dlg_settings.findViewById(R.id.dlg_btn_eng);

                dlg_btn_back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dlg_settings.dismiss();
                    }
                });

                //--TODO:設定所有的文字


            }
        });








        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO:建立確認
                String restart = "1999/12/31";
                String str = "UPDATE Date SET date='" + restart + "' WHERE _id=" + 1;
                db.execSQL(str);
                start.setVisibility(View.VISIBLE);
                complete.setVisibility(View.INVISIBLE);
                countday.setText(getString(R.string.before_start));
                Intent intent = new Intent(getContext(), MainService.class);
                getActivity().stopService(intent);
            }
        });


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dateformat = "yyyy/MM/dd";
                SimpleDateFormat df = new SimpleDateFormat(dateformat);
                Date now = new Date();
                String Insertstartdate;
                Insertstartdate = df.format(now.getTime());
                String str = "UPDATE Date SET date='" + Insertstartdate + "' WHERE _id=" + 1;
                db.execSQL(str);
                start.setVisibility(View.INVISIBLE);
                complete.setVisibility(View.VISIBLE);
                countday.setText(getString(R.string.btn_starting));
                Intent intent = new Intent(getContext(), MainService.class);
                getActivity().startService(intent);
            }
        });

        return view;
    }

    private long findTime(String a){
        long temp = 0;
        try{
            SimpleDateFormat sim = new SimpleDateFormat("yyyy/MM/dd");//定義日期時間格式，一定要進行ParseException的例外處理
            Date f = sim.parse(a);
            long firstmeet = f.getTime();//取得時間的unix時間
            Date now = new Date();//取得目前即時的時間
            long nowtime = now.getTime();//取得時間的unix時間
            temp = (nowtime-firstmeet)/(1000*60*60*24);
            //Toast.makeText(getContext(), "time= " + f, Toast.LENGTH_SHORT).show();
        }catch(ParseException e){
            //Toast.makeText(getContext(),"Something is wrong", Toast.LENGTH_SHORT).show();
            //Toast.makeText(getContext(),"date0:" + temp , Toast.LENGTH_SHORT).show();
        }
        return temp;
    }



    @Override
    public void onDetach() {
        super.onDetach();
        db.close();
    }
}