package com.proj.android.androidproj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;

public class SystemactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.systemact);

        TextView usernameTxt = (TextView)findViewById( R.id.usernameTxt);

        Bundle bund = getIntent().getExtras();
        String TitleTextName = bund.getString("username");
        usernameTxt.setText(TitleTextName + " ");


    }

    public void ShowReportList(View view) {
        Intent ViewReportIntent = new Intent(this,ViewReport.class); // Activity we get to go!
        startActivity(ViewReportIntent);
    }
}
