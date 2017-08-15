package com.proj.android.androidproj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;


import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void btclick(View view) {
        EditText username = (EditText)findViewById(R.id.editTextUserName); // username edit text
        EditText phonenumber = (EditText)findViewById(R.id.editTextPhoneNumber); // password edit text
        TextView title = (TextView)findViewById(R.id.titleTxt);

        String usernameText = username.getText().toString(); // get the value username as is!
        String phonenumberText = phonenumber.getText().toString(); // get the value password as is!

        //new String("").equals(usernameText)
        //Log.d("text",usernameText);
        if(!(new String("").equals(usernameText)) && phonenumberText.length() >=8)
        {
            //title.setText(String.valueOf(usernameText)); // set the value
            Intent systemIntent = new Intent(this,SystemactActivity.class); // Activity we get to go!
            Bundle bund = new Bundle(); // the data e want to send for the other activity

            bund.putString("username", usernameText);
            bund.putString("phonenumber",phonenumberText);

            systemIntent.putExtras(bund);//all the data set in this intent by bundle object!
            startActivity(systemIntent);// fire the activity we want to!

        }else{
            //title.setText(String.valueOf("Error")); // set the value

            Toast.makeText(this,"הכנס שם ומספר טלפון תקין ",Toast.LENGTH_LONG).show();
        }
    }

    public void forgettxtBtn(View view) {

    }
}
