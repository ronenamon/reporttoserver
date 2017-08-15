package com.proj.android.androidproj;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportActivity extends AppCompatActivity {

    public static final String DBname = "MyReports.db";
    public static final int Version = 1;
    DBConnections db;

    static final int REQUEST_TAKE_PHOTO = 1;

    CharSequence[] items = {"צפון", "חיפה", "מרכז", "דרום"};

    ImageView iv;
    TextView ReportArea;
    TextView ReporterName;
    TextView ReporterPhone;
    EditText edtext;
    String imgPath;
    Button Disable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
       // Disable.setEnabled(true);

         db = new DBConnections(this,DBname,null,Version);

        ReportArea = (TextView)findViewById(R.id.ReportArea);
        ReporterName = (TextView)findViewById(R.id.ReporterName);
        ReporterPhone = (TextView)findViewById(R.id.ReporterPhone);
        edtext = (EditText)findViewById(R.id.editTextToReport);
        iv = (ImageView)findViewById(R.id.imageView);
        Disable = (Button)findViewById(R.id.btnReport);

       init();

    }

    private void init() {

        Intent intent = getIntent();
        Bundle bund = intent.getExtras();

        int selectedItem = bund.getInt("AreaSelected");// get area from the bundle
        String Name = bund.getString("name");// get name from the bundle
        String Phone = bund.getString("phone");// get phone  from the bundle

        ReportArea.setText( items[selectedItem].toString());
        ReporterName.setText(Name);
        ReporterPhone.setText(Phone);
    }

    public void btnOpenCamera(View view) {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);

            // Ensure that there's a camera activity to handle the intent
           // if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                // Create the File where the photo should go
//                File photoFile = null;
//                try {
//                    photoFile = createImageFile();
//                } catch (IOException ex) {
//                    // Error occurred while creating the File
//
//                }
//                // Continue only if the File was successfully created
//                if (photoFile != null) {
                    //Log.d("File Path: ", photoFile.getAbsolutePath());
                    //takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                    //Log.d("EXTRA_OUTPUT: ", MediaStore.EXTRA_OUTPUT.toString());
                    //Log.d("fromFile(photoFile): ",Uri.fromFile(photoFile).toString());


                }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if(data !=null) {
            if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {

                // set the picture in the thumbnail
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");//get the picture
                iv.setImageBitmap(imageBitmap);

                // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
                Uri tempUri = getImageUri(getApplicationContext(), imageBitmap);

                // CALL THIS METHOD TO GET THE ACTUAL PATH
                File finalFile = new File(getRealPathFromURI(tempUri));

               String imageID = finalFile.getAbsolutePath();
               imgPath = imageID;
               Log.d("imageID",imageID);

            }
        }
        else
        {
            Log.d("Intent data: ","NULL");
        }
    }


    // report Button
    public void btnReportClick(View view) {

        if(imgPath==null)
        {
            imgPath = "";
        }
        else
        {

        }
        // //id INTEGER,name TEXT, phone TEXT,area TEXT,img_src TEXT,img_name,desc TEXT,PRIMARY KEY(id));
        db.insertRowReports(ReporterName.getText().toString(), ReporterPhone.getText().toString(), imgPath);



        Log.d("DB Size:",String.valueOf(db.getAllData().size()));

        for (int i = 0;i<db.getAllData().size();i=i+4){
            Log.d("Index :"+ i, String.valueOf(db.getAllData().get(i)) +"-"+ String.valueOf(db.getAllData().get(i+1)) +"-"+ String.valueOf(db.getAllData().get(i+2)+"-"+String.valueOf(db.getAllData().get(i+3))));
        }

        Toast.makeText(this,"הדיווח התקבל תודה",Toast.LENGTH_LONG).show();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("הדיווח התקבל תודה");
        builder.setPositiveButton("אישור", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // OK to do
               // GO();
                dialog.dismiss();
                //Disable.setEnabled(false);
                GO();

            }
        });
        builder.create().show();



                }
        public void GO()
        {
            Intent intent = new Intent(this, ViewReport.class);
            startActivity(intent);
        }
    public void btnCancelClick(View view) {
        finish();
    }



    String mCurrentPhotoPath;
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        Log.d("mCurrentPhotoPath",mCurrentPhotoPath);//file:/storage/emulated/0/Pictures/JPEG_20160611_132609_-888174964.jpg
        return image;
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);

        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

}
