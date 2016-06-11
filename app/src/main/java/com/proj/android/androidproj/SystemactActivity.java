package com.proj.android.androidproj;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

public class SystemactActivity extends AppCompatActivity {

    String ReporterName;
    String ReporterPhone;
    int areaSelected;
    final CharSequence[] items = {"צפון", "חיפה", "מרכז", "דרום"};// setSingleChoiceItems
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.systemact);

        TextView usernameTxt = (TextView)findViewById( R.id.usernameTxt);

        Bundle bund = getIntent().getExtras();
        String TitleTextName = bund.getString("username");// get name from the bundle
        usernameTxt.setText(TitleTextName); // set the name title just

        ReporterName = TitleTextName;
        ReporterPhone = bund.getString("phonenumber");//get phone number from the bundle



    }

    // go the list of all the report
    public void ShowReportList(View view) {
        Intent ViewReportIntent = new Intent(this,ViewReport.class); // Activity we get to go!
        startActivity(ViewReportIntent);
    }

    public void btnEvent1Click(View view) {


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.popUpArea)
                .setSingleChoiceItems(items, 1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int item) {
                        Toast.makeText(getApplicationContext(), items[item] + " - " + item, Toast.LENGTH_SHORT).show();
                        areaSelected = item;
                    }
                });
        builder.setPositiveButton("אישור", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // OK to do
                dialog.dismiss();
                GoWithData(areaSelected ,ReporterName,ReporterPhone);

            }
        }) .setNegativeButton("ביטול", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // cancel to do
                dialog.dismiss();
            }
        });

        builder.create().show();


        /*
        // this use PopmenuClass instance
        // if we need get text from user or some else

        FragmentManager manager = getFragmentManager();
        PopmenuClass popEvent1Instance = new PopmenuClass();
        popEvent1Instance.show(manager,null);
        */

    }

    public void btnEvent2Click(View view) {
        final CharSequence[] items = {"צפון", "חיפה", "מרכז", "דרום"};// setSingleChoiceItems

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.popUpArea)
                .setSingleChoiceItems(items, 1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int item) {
                        Toast.makeText(getApplicationContext(), items[item] + " - " + String.valueOf(item), Toast.LENGTH_SHORT).show();
                        areaSelected = item;
                    }
                });
        builder.setPositiveButton("אישור", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // OK to do
                dialog.dismiss();
                GoWithData(areaSelected ,ReporterName,ReporterPhone);


            }
        }) .setNegativeButton("ביטול", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // cancel to do
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

    public void btnEvent3Click(View view) {

        final CharSequence[] items = {"צפון", "חיפה", "מרכז", "דרום"};// setSingleChoiceItems

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.popUpArea)
                .setSingleChoiceItems(items, 1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int item) {
                        Toast.makeText(getApplicationContext(), items[item] + " - " + String.valueOf(item), Toast.LENGTH_SHORT).show();
                        areaSelected = item;
                    }
                });
        builder.setPositiveButton("אישור", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // OK to do
                dialog.dismiss();
                GoWithData(areaSelected, ReporterName, ReporterPhone);

            }
        }) .setNegativeButton("ביטול", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // cancel to do
                dialog.dismiss();
            }
        });

        builder.create().show();


    }

    public void btnEvent4Click(View view) {

        final CharSequence[] items = {"צפון", "חיפה", "מרכז", "דרום"};// setSingleChoiceItems

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.popUpArea)
                .setSingleChoiceItems(items, 1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int item) {
                        Toast.makeText(getApplicationContext(), items[item] + " - " + String.valueOf(item), Toast.LENGTH_SHORT).show();
                        areaSelected =item;
                    }
                });
        builder.setPositiveButton("אישור", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // OK to do
                dialog.dismiss();
                GoWithData(areaSelected, ReporterName, ReporterPhone);

            }
        }) .setNegativeButton("ביטול", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // cancel to do
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

    public void GoWithData(int selecetedItemNumber, String name ,String phone)
    {

        Intent OpenIntentToReport = new Intent(getApplicationContext(),ReportActivity.class); // Activity we get to go!
        Bundle bund = new Bundle(); // the data we want to send for the other activity

        bund.putInt("AreaSelected", selecetedItemNumber);
        bund.putString("name", name);
        bund.putString("phone",phone);

        OpenIntentToReport.putExtras(bund);//all the data set in this intent by bundle object!

        startActivity(OpenIntentToReport);// fire the activity we want to!
    }
}
