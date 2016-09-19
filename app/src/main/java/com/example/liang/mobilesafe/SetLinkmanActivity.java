package com.example.liang.mobilesafe;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import Adapter.CommonAdapter;
import Adapter.ViewHolder;
import javabeen.Lineman;

public class SetLinkmanActivity extends AppCompatActivity {
    private ListView listView;
    private List<Lineman> data;
    private EditText editText;
    private Button next;
    private SharedPreferences sp;
    private String safetyNumber;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_linkman);
        initView();
        initData();
    }

    void initView(){
        listView=(ListView)findViewById(R.id.listview);
        editText=(EditText)findViewById(R.id.otherNum);
        next=(Button)findViewById(R.id.button_2);

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next.setVisibility(View.VISIBLE);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                next.setVisibility(View.VISIBLE);
                editText.setText(data.get(position).getNumber());
                safetyNumber=data.get(position).getNumber();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=editText.getText().toString().trim();
                if(!TextUtils.isEmpty(str)){
                    safetyNumber=str;
                }
                sp=getSharedPreferences("config",MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();
                editor.putString("safetyNumber",safetyNumber);
                editor.commit();

            }
        });

    }

    void initData(){
        data=new ArrayList<>();
        getLinemans();
        listView.setAdapter(new CommonAdapter<Lineman>(this,data,R.layout.item_lineman) {
            @Override
            public void convert(ViewHolder helper, Lineman item) {
                helper.setText(R.id.name,item.getName());
                helper.setText(R.id.number,item.getNumber());
            }
        });


    }

    public void chooseLineman(View view){
        listView.setVisibility(View.VISIBLE);
    }

    public void getLinemans(){
        Uri uri = Uri.parse("content://icc/adn");
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        while (cursor.moveToNext()) {

            String id = cursor.getString(cursor.getColumnIndex(Contacts.People._ID));

            String name = cursor.getString(cursor.getColumnIndex(Contacts.People.NAME));

            String phoneNumber = cursor.getString(cursor

                    .getColumnIndex(Contacts.People.NUMBER));

            data.add(new Lineman(name,phoneNumber));


        }
    }
}
