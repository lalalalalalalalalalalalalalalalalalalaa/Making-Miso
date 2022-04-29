package com.example.miso;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class EditFragment extends Fragment {

    EditText edt_Ingredient, edt_Content, edt_formulaname;
    View view;
    Button btn_edt_confirm;
    SQLiteDatabase db;
    int id;

    public EditFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_edit, container, false);
        btn_edt_confirm = view.findViewById(R.id.btn_edt_confirm);
        //取的目前資料內容
        getParentFragmentManager().setFragmentResultListener("Formula_list", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

                id = result.getInt("sentID");
                String data7 = result.getString("sentIngredient");
                String data8 = result.getString("sentContent");
                String data9 = result.getString("sentFormulaName");
                edt_Ingredient = view.findViewById(R.id.edt_Ingredient);
                edt_Content = view.findViewById(R.id.edt_Content);
                edt_formulaname = view.findViewById(R.id.edt_formulaname);
                edt_Ingredient.setText(data7);
                edt_Content.setText(data8);
                edt_formulaname.setText(data9);

            }
        });
        //將更改的內容儲存，並回到上一頁
        btn_edt_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str;
                String data3;
                String data4;
                String data5;
                data3 = edt_formulaname.getText().toString();
                data4 = edt_Ingredient.getText().toString();
                data5 = edt_Content.getText().toString();

                Toast.makeText(getContext(), "id=" + id, Toast.LENGTH_SHORT).show();

                db = getActivity().openOrCreateDatabase("Miso.db", Context.MODE_PRIVATE, null);

                str = "UPDATE Formula SET FormulaName='" + data3 + "', Ingredient='" + data4 +
                        "', Content='" + data5 + "' WHERE _id=" + id;
                db.execSQL(str);
                db.close();

                Bundle edtsent = new Bundle();
                edtsent.putInt("edtsentID", id);
                edtsent.putString("edtsentFormulaName",edt_formulaname.getText().toString());
                edtsent.putString("edtsentIngredient", edt_Ingredient.getText().toString());
                edtsent.putString("edtsentContent", edt_Content.getText().toString());
                getParentFragmentManager().setFragmentResult("Formula_edt",edtsent);
                replaceFragment(new Formula_list());
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