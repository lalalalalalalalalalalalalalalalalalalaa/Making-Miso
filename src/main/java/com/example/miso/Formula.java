package com.example.miso;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class Formula extends Fragment {

    private SQLiteDatabase db;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_formula, container, false);

        Button button01 = view.findViewById(R.id.btn_formula_01);
        Button button02 = view.findViewById(R.id.btn_formula_02);
        Button button03 = view.findViewById(R.id.btn_formula_03);
        Button button04 = view.findViewById(R.id.btn_formula_04);
        Button button05 = view.findViewById(R.id.btn_formula_05);
        Button button06 = view.findViewById(R.id.btn_formula_06);
        Button button07 = view.findViewById(R.id.btn_formula_07);
        Button button08 = view.findViewById(R.id.btn_formula_08);

        button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = getActivity().openOrCreateDatabase("Miso.db", Context.MODE_PRIVATE, null);
                String Ingredient = "";
                String Content = "";
                String FormulaName = "";
                int ID = 1;

                Cursor cursor = db.rawQuery("SELECT _id, FormulaName, Ingredient, Content FROM Formula", null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        ID = cursor.getInt(0);
                        if (ID == 1) {
                            FormulaName = cursor.getString(1);
                            Ingredient = cursor.getString(2);
                            Content = cursor.getString(3);
                            break;
                        }
                    }
                }

                Bundle list = new Bundle();
                list.putInt("ID", ID);
                list.putString("FormulaName",FormulaName);
                list.putString("Ingredient", Ingredient );
                list.putString("Content", Content);
                getParentFragmentManager().setFragmentResult("Formula",list);
                replaceFragment(new Formula_list());

                cursor.close();
                db.close();

            }
        });

        button02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = getActivity().openOrCreateDatabase("Miso.db", Context.MODE_PRIVATE, null);
                String Ingredient = "";
                String Content = "";
                String FormulaName = "";
                int ID = 2;

                Cursor cursor = db.rawQuery("SELECT _id, FormulaName, Ingredient, Content FROM Formula", null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        ID = cursor.getInt(0);
                        if (ID == 2) {
                            FormulaName = cursor.getString(1);
                            Ingredient = cursor.getString(2);
                            Content = cursor.getString(3);
                            break;
                        }
                    }
                }

                Bundle list = new Bundle();
                list.putInt("ID", ID);
                list.putString("FormulaName",FormulaName);
                list.putString("Ingredient", Ingredient );
                list.putString("Content", Content);
                getParentFragmentManager().setFragmentResult("Formula",list);
                replaceFragment(new Formula_list());

                cursor.close();
                db.close();
            }
        });

        button03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = getActivity().openOrCreateDatabase("Miso.db", Context.MODE_PRIVATE, null);
                String Ingredient = "";
                String Content = "";
                String FormulaName = "";
                int ID = 3;

                Cursor cursor = db.rawQuery("SELECT _id, FormulaName, Ingredient, Content FROM Formula", null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        ID = cursor.getInt(0);
                        if (ID == 3) {
                            FormulaName = cursor.getString(1);
                            Ingredient = cursor.getString(2);
                            Content = cursor.getString(3);
                            break;
                        }
                    }
                }

                Bundle list = new Bundle();
                list.putInt("ID", ID);
                list.putString("FormulaName",FormulaName);
                list.putString("Ingredient", Ingredient );
                list.putString("Content", Content);
                getParentFragmentManager().setFragmentResult("Formula",list);
                replaceFragment(new Formula_list());

                cursor.close();
                db.close();
            }
        });

        button04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = getActivity().openOrCreateDatabase("Miso.db", Context.MODE_PRIVATE, null);
                String Ingredient = "";
                String Content = "";
                String FormulaName = "";
                int ID = 4;

                Cursor cursor = db.rawQuery("SELECT _id, FormulaName, Ingredient, Content FROM Formula", null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        ID = cursor.getInt(0);
                        if (ID == 4) {
                            FormulaName = cursor.getString(1);
                            Ingredient = cursor.getString(2);
                            Content = cursor.getString(3);
                            break;
                        }
                    }
                }

                Bundle list = new Bundle();
                list.putInt("ID", ID);
                list.putString("FormulaName",FormulaName);
                list.putString("Ingredient", Ingredient );
                list.putString("Content", Content);
                getParentFragmentManager().setFragmentResult("Formula",list);
                replaceFragment(new Formula_list());

                cursor.close();
                db.close();

            }
        });

        button05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = getActivity().openOrCreateDatabase("Miso.db", Context.MODE_PRIVATE, null);
                String Ingredient = "";
                String Content = "";
                String FormulaName = "";
                int ID = 5;

                Cursor cursor = db.rawQuery("SELECT _id, FormulaName, Ingredient, Content FROM Formula", null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        ID = cursor.getInt(0);
                        if (ID == 5) {
                            FormulaName = cursor.getString(1);
                            Ingredient = cursor.getString(2);
                            Content = cursor.getString(3);
                            break;
                        }
                    }
                }

                Bundle list = new Bundle();
                list.putInt("ID", ID);
                list.putString("FormulaName",FormulaName);
                list.putString("Ingredient", Ingredient );
                list.putString("Content", Content);
                getParentFragmentManager().setFragmentResult("Formula",list);
                replaceFragment(new Formula_list());

                cursor.close();
                db.close();
            }
        });

        button06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = getActivity().openOrCreateDatabase("Miso.db", Context.MODE_PRIVATE, null);
                String Ingredient = "";
                String Content = "";
                String FormulaName = "";
                int ID = 6;

                Cursor cursor = db.rawQuery("SELECT _id, FormulaName, Ingredient, Content FROM Formula", null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        ID = cursor.getInt(0);
                        if (ID == 6) {
                            FormulaName = cursor.getString(1);
                            Ingredient = cursor.getString(2);
                            Content = cursor.getString(3);
                            break;
                        }
                    }
                }

                Bundle list = new Bundle();
                list.putInt("ID", ID);
                list.putString("FormulaName",FormulaName);
                list.putString("Ingredient", Ingredient );
                list.putString("Content", Content);
                getParentFragmentManager().setFragmentResult("Formula",list);
                replaceFragment(new Formula_list());

                cursor.close();
                db.close();

            }
        });

        button07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = getActivity().openOrCreateDatabase("Miso.db", Context.MODE_PRIVATE, null);
                String Ingredient = "";
                String Content = "";
                String FormulaName = "";
                int ID = 7;

                Cursor cursor = db.rawQuery("SELECT _id, FormulaName, Ingredient, Content FROM Formula", null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        ID = cursor.getInt(0);
                        if (ID == 7) {
                            FormulaName = cursor.getString(1);
                            Ingredient = cursor.getString(2);
                            Content = cursor.getString(3);
                            break;
                        }
                    }
                }

                Bundle list = new Bundle();
                list.putInt("ID", ID);
                list.putString("FormulaName",FormulaName);
                list.putString("Ingredient", Ingredient );
                list.putString("Content", Content);
                getParentFragmentManager().setFragmentResult("Formula",list);
                replaceFragment(new Formula_list());

                cursor.close();
                db.close();
            }
        });

        button08.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = getActivity().openOrCreateDatabase("Miso.db", Context.MODE_PRIVATE, null);
                String Ingredient = "";
                String Content = "";
                String FormulaName = "";
                int ID = 8;

                Cursor cursor = db.rawQuery("SELECT _id, FormulaName, Ingredient, Content FROM Formula", null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        ID = cursor.getInt(0);
                        if (ID == 8) {
                            FormulaName = cursor.getString(1);
                            Ingredient = cursor.getString(2);
                            Content = cursor.getString(3);
                            break;
                        }
                    }
                }

                Bundle list = new Bundle();
                list.putInt("ID", ID);
                list.putString("FormulaName",FormulaName);
                list.putString("Ingredient", Ingredient );
                list.putString("Content", Content);
                getParentFragmentManager().setFragmentResult("Formula",list);
                replaceFragment(new Formula_list());

                cursor.close();
                db.close();
            }
        });
        return  view;
    }

    private void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainpart,fragment);
        fragmentTransaction.commit();

    }


}

