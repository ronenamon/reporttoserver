package com.proj.android.androidproj;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ReportActivity extends AppCompatActivity {

    final CharSequence[] items = {"צפון", "חיפה", "מרכז", "דרום"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        TextView txtArea = (TextView)findViewById(R.id.AreaText);
        EditText edtext = (EditText)findViewById(R.id.editText);

        Bundle bund = getIntent().getExtras();
        int selectedItem = bund.getInt("AreaSelected");// get area from the bundle
        String Name = bund.getString("name");// get name from the bundle
        String Phone = bund.getString("phone");// get phone  from the bundle
        Log.d("name" , Name);
        Log.d("Phone", Phone);
        Log.d("selectedItem-1", String.valueOf(selectedItem));
        Log.d("selectedItem-2", items[selectedItem].toString());
        Log.d("txtArea-1", String.valueOf(txtArea));
        Log.d("txtArea-2", txtArea.getText().toString());
        txtArea.setText(items[selectedItem]);
        Log.d("txtArea-3", txtArea.getText().toString());

        assert edtext != null;
        edtext.setText("Area : " + items[selectedItem] + " " + "Name : " + Name + " Phone : " + Phone);

    }

    public void btnOpenCamera(View view) {

    }

    public void btnReportClick(View view) {
    }

    public void btnCancelClick(View view) {
    }


}
