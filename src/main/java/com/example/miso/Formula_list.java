package com.example.miso;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

public class Formula_list extends Fragment {

    View view;
    TextView Ingredient, Content, formulaname;
    int ID;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_formula_list, container, false);
        Ingredient = view.findViewById(R.id.txt_Ingredient);
        Content = view.findViewById(R.id.txt_Content);
        formulaname = view.findViewById(R.id.formulaname);


        getParentFragmentManager().setFragmentResultListener("Formula", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

                String data1 = result.getString("Ingredient");
                String data2 = result.getString("Content");
                String data3 = result.getString("FormulaName");
                ID = result.getInt("ID");
                Ingredient.setText(data1);
                Content.setText(data2);
                formulaname.setText(data3);

            }
        });

        getParentFragmentManager().setFragmentResultListener("Formula_edt", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

                /*Ingredient.setText("");
                Content.setText("");
                formulaname.setText("");*/
                String data1 = result.getString("edtsentIngredient");
                String data2 = result.getString("edtsentContent");
                String data3 = result.getString("edtsentFormulaName");
                ID = result.getInt("edtsentID");
                Ingredient.setText(data1);
                Content.setText(data2);
                formulaname.setText(data3);

            }
        });

        Button btn_edit = view.findViewById(R.id.btn_edit);
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle edttext = new Bundle();
                edttext.putString("sentFormulaName", formulaname.getText().toString());
                edttext.putString("sentIngredient", Ingredient.getText().toString());
                edttext.putString("sentContent", Content.getText().toString());
                edttext.putInt("sentID",ID);

                //Toast.makeText(getContext(), "id=" + ID, Toast.LENGTH_SHORT).show();

                getParentFragmentManager().setFragmentResult("Formula_list",edttext);
                replaceFragment(new EditFragment());
            }
        });

        return view;


    }

    private void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainpart,fragment);
        fragmentTransaction.commit();

    }
}

