package com.proj.android.androidproj;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;

import java.io.IOException;
import java.io.InputStream;

//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.DefaultHttpClient;


import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;



public class ViewReport extends AppCompatActivity {

    TextView txtStatus;
    ArrayList<ListItem> Items;
    ListView lsView;
    String status;
    ImageView img;
    DBConnections db;
    private static LayoutInflater linflater=null;
    //LayoutInflater linflater;
    public static final String DBname = "MyReports.db";
    public static final int Version = 1;
    MyCustomAdapter myadapter;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_report);
        txtStatus = (TextView)findViewById(R.id.TxtStatus);
        context=this;
        String url = "http://www.dodev.info:8000/ronen/app/app.php";
        //new AsyncTaskgetReports().execute(url);

        ini();

    }//SOF

    private void ini() {
       // Intent intent = getIntent();
        Items =new ArrayList<ListItem>();//init
       // ArrayList list = (ArrayList)intent.getSerializableExtra("ArrayList");
        ArrayList list;

        db = new DBConnections(this,DBname,null,Version);
        list = db.getAllData();

        if(list!=null && list.size()>0)
        {
            ListItem TempItem;
            for (int i = 0;i<list.size();i=i+4){
                Log.d("Data Number : "+ i, String.valueOf(list.get(i)));
               //id INTEGER,name TEXT, phone TEXT,area TEXT,img_src TEXT,img_name,desc TEXT,PRIMARY KEY(id));
                TempItem = new ListItem(Integer.parseInt(list.get(i).toString()),list.get(i+1).toString(),list.get(i+2).toString(),"",list.get(i+3).toString(),"","");
                Items.add(TempItem);//the temporery item

            }
        }else{
            Log.d("list null","list.size");
        }
        myadapter = new MyCustomAdapter(Items);
        lsView = (ListView) findViewById(R.id.listView);
        lsView.setAdapter(myadapter);

        //on click some items
        lsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView txtName = (TextView) view.findViewById(R.id.textName);
                TextView txtDesc = (TextView) view.findViewById(R.id.textDesc);
                Toast.makeText(getApplicationContext(), txtName.getText() + "  ID : " + position, Toast.LENGTH_LONG).show();
            }
        });




    }

    class MyCustomAdapter extends BaseAdapter {


        ArrayList<ListItem> Items = new ArrayList<ListItem>();

        MyCustomAdapter(ArrayList<ListItem> Items) {

            this.Items = Items;
        }

        @Override
        public int getCount() {
            return Items.size();
        }

        @Override
        public String getItem(int position) {
            return Items.get(position).Name;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view1;

            linflater = getLayoutInflater();

             view1 = linflater.inflate(R.layout.row_view,null);

            TextView txtName = (TextView) view1.findViewById(R.id.textName);
            TextView txtDesc = (TextView) view1.findViewById(R.id.textDesc);
            img = (ImageView)view1.findViewById(R.id.ImageViewInList);

            txtName.setText(Items.get(position).Name);
            txtDesc.setText(Items.get(position).Phone);
            File imgFile = new File(Items.get(position).img_src);

            if(imgFile.exists()){
                Log.d("file exist!",imgFile.getAbsolutePath());
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                img.setImageBitmap(myBitmap);
            }

            return view1;
        }
    }//SOF


    public class AsyncTaskgetReports extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute(){
            Items =new ArrayList<ListItem>();//init

            status = "מצב";//reset

           // MyCustomAdapter myadapter = new MyCustomAdapter(Items);
            // connect ListView
            //ListView lsView = (ListView) findViewById(R.id.listView);
            // inject The Adapter To The Object myadapter
            //lsView.setAdapter(myadapter);
        }

        @Override
        protected String doInBackground(String... params) {

            publishProgress("open connection" );
            Log.d("open connection", "ok");
            try
            {
                URL url = new URL(params[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                publishProgress("start  read buffer" );
                status=Stream2String(in);
                Log.d("statusAfterStrem2String", status);

                ////////////
                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(status);
                    Log.d("length",String.valueOf(jsonArray.length()));
                    JSONObject object;
                    JSONArray array;

                    //cources college;
                    ListItem TempItem;

                    //collegeList = new ArrayList<cources>();

                    // Fill The Array Of Items
                    for(int i=0; i<jsonArray.length(); i++)
                    {

                        object = jsonArray.getJSONObject(i);//get the current object
                        // ListItem(int id,String Name,String Phone,String Area,String img_src,String img_name,String Desc)
                        //init the value
                        TempItem = new ListItem(object.getInt("id"),
                                                object.getString("name"),
                                                object.getString("phone"),
                                                object.getString("area"),
                                                object.getString("img_src"),
                                                object.getString("img_name"),
                                                object.getString("desc_report"));
                        Items.add(TempItem);//the temporery item
                    }

                }catch (JSONException e ){
                  e.printStackTrace();
                }

                /////////////
                in.close();


            } catch (Exception e) {
                publishProgress("cannot connect to server");
            }

            return null;
        }

        protected void onProgressUpdate(String... progress) {

            try {
                Log.d("txtStatus", txtStatus.getText().toString());
                txtStatus.setText(progress[0]);




            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

        @Override
        protected void onPostExecute(String  result2){


            Log.d("onPostExecute", "OK");
           // txtStatus.setText(status);
            Log.d("After onPostExecute", txtStatus.getText().toString());

            MyCustomAdapter myadapter = new MyCustomAdapter(Items);
            lsView = (ListView) findViewById(R.id.listView);
            lsView.setAdapter(myadapter);

            //on click some items
            lsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TextView txtName = (TextView) view.findViewById(R.id.textName);
                    TextView txtDesc = (TextView) view.findViewById(R.id.textDesc);
                    Toast.makeText(getApplicationContext(), txtName.getText() + "  ID : " + position, Toast.LENGTH_LONG).show();
                }
            });
        }

        public String Stream2String(InputStream inputStream) {
            BufferedReader bureader=new BufferedReader( new InputStreamReader(inputStream));
            String line ;
            String Text="";
            try{
                while((line=bureader.readLine())!=null) {
                    Text+=line;
                }
                inputStream.close();
            }catch (Exception ex){}
            return Text;
        }
    }//SOF

    public void getImagePath(){
        ///storage/emulated/0/Pictures/1465651317879.jpg
        ///sdcard/Images/test_image.jpg
        File imgFile = new File("storage/emulated/0/Pictures/1465651317879.jpg");
        if(imgFile.exists()){
            Log.d("file exist!",imgFile.getAbsolutePath());
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
           img.setImageBitmap(myBitmap);
        }
    }
}


