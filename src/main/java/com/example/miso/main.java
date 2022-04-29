package com.example.miso;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class main extends Fragment {

    public main() {}

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

        Button complete = view.findViewById(R.id.complete);
        Button start = view.findViewById(R.id.start);
        countday = view.findViewById(R.id.countday);
        String a = "";
        int days = 0;
        //開啟資料庫，查詢是否有開始的時間，若是查詢結果為1999/12/31代表尚未開始製作味增
        db = getActivity().openOrCreateDatabase("Miso.db", Context.MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("SELECT _id, date FROM Date", null);
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
                    String senttext =  getString(R.string.starting1) + " " + days + " " + getString(R.string.starting2);
                    countday.setText(senttext);
                }
            }
        }
        //設定完成，將資料庫的時間重製為1999/12/31，並且關閉伺服器
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
        //設定開始，並將當下時間記錄到資料庫，並且開啟伺服器
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
    //計算天數的方法
    private long findTime(String a){
        long temp = 0;
        try{
            SimpleDateFormat sim = new SimpleDateFormat("yyyy/MM/dd");//定義日期時間格式，一定要進行ParseException的例外處理
            Date f = sim.parse(a);
            long firstmeet = f.getTime();//取得時間的unix時間
            Date now = new Date();//取得目前即時的時間
            long nowtime = now.getTime();//取得時間的unix時間
            temp = (nowtime-firstmeet)/(1000*60*60*24);
        }catch(ParseException e){
        }
        return temp;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        db.close();
    }
}