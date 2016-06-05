package com.proj.android.androidproj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ViewReport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_report);

        //create my Array by the class ListItem
        ArrayList<ListItem> Items = new ArrayList<ListItem>();
        // add items name and desc
        Items.add(new ListItem("Ronen1","Desc 1"));
        Items.add(new ListItem("Ronen2","Desc 2"));
        Items.add(new ListItem("Ronen3","Desc 3"));
        Items.add(new ListItem("Ronen4","Desc 4"));
        Items.add(new ListItem("Ronen5","Desc 5"));

        // create object from class MyCustomAdapter
        // and inject the Array Items We Create Before
        MyCustomAdapter myadapter = new MyCustomAdapter(Items);
        // connect ListView
        ListView lsView = (ListView)findViewById(R.id.listView);
        // inject The Adapter To The Object myadapter
        lsView.setAdapter(myadapter);

        //on click some items
        lsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView txtName = (TextView) view.findViewById(R.id.textName);
                TextView txtDesc = (TextView) view.findViewById(R.id.textDesc);

                // the data e want to send for the other activity
                Bundle bundWithData = new Bundle();

                bundWithData.putInt("position",position);
                bundWithData.putString("Name", txtName.getText().toString());
                bundWithData.putString("Desc",txtDesc.getText().toString());

                //PutExtra And Start The New Intent


                Toast.makeText(getApplicationContext(),txtName.getText() + "  ID : " + position,Toast.LENGTH_LONG).show();
            }
        });

    }

    class MyCustomAdapter extends BaseAdapter{

        ArrayList<ListItem> Items = new ArrayList<ListItem>();
        MyCustomAdapter(ArrayList<ListItem> Items){

            this.Items = Items;
        }
        @Override
        public int getCount() {
            return Items.size();
        }

        @Override
        public String getItem(int position) {
            return Items .get(position).Name;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater linflater = getLayoutInflater();
            View view1 = linflater.inflate(R.layout.row_view, null);
            TextView txtName = (TextView) view1.findViewById(R.id.textName);
            TextView txtDesc = (TextView) view1.findViewById(R.id.textDesc);
            txtName.setText(Items.get(position).Name);
            txtDesc.setText(Items.get(position).Desc);
            return view1;
        }
    }
}
