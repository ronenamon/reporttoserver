package com.proj.android.androidproj;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PopmenuClassTemp extends DialogFragment implements View.OnClickListener{

    View form;
    EditText inserTextByUser;
    Button btnOK;
    Button btnCancel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
        {
            form = inflater.inflate(R.layout.popform,container,false);
            inserTextByUser = (EditText)form.findViewById(R.id.edTextArea);
            btnOK = (Button)form.findViewById(R.id.popOk);
            btnCancel = (Button)form.findViewById(R.id.popCancel);
            btnOK.setOnClickListener(this);//if click is going to onClick Funcation
            btnCancel.setOnClickListener(this);//if click is going to onClick Funcation
            //getDialog().setTitle("  בחר איזור בבקשה ");

            return form;
        }


    @Override
    public void onClick(View v) {
        Button btn = (Button) v;
        if (btn.getText().toString().equals("ביטול")) {
            this.dismiss();
        }else
        {
            if (inserTextByUser.getText().toString().equals("צפון"))
                inserTextByUser.setText("");
                inserTextByUser.setText("תודה");
                this.dismiss();
        }

    }
}
