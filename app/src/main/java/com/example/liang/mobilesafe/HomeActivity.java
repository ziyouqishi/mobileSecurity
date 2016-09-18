package com.example.liang.mobilesafe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import Adapter.HomeAdapter;
import javabeen.HomeIcon;
import utitls.BitmapDeal;

public class HomeActivity extends AppCompatActivity {
    private GridView gridView;
    private ArrayList<HomeIcon>datas;
    private HomeAdapter homeAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        initDatas();
    }

    void initView(){
        gridView=(GridView)findViewById(R.id.list_home);
    }

    void initDatas(){
        datas=new ArrayList<>();
        datas.add(new HomeIcon(new BitmapDeal().readBitmap(this,R.drawable.fangdao),"手机防盗"));
        datas.add(new HomeIcon(new BitmapDeal().readBitmap(this,R.drawable.communications),"通讯卫士"));
        datas.add(new HomeIcon(new BitmapDeal().readBitmap(this,R.drawable.apps),"应用管理"));
        datas.add(new HomeIcon(new BitmapDeal().readBitmap(this,R.drawable.jincheng),"进程管理"));
        datas.add(new HomeIcon(new BitmapDeal().readBitmap(this,R.drawable.liuliang),"流量统计"));
        datas.add(new HomeIcon(new BitmapDeal().readBitmap(this,R.drawable.bingdu),"手机杀毒"));
        datas.add(new HomeIcon(new BitmapDeal().readBitmap(this,R.drawable.huancun),"缓存清理"));
        datas.add(new HomeIcon(new BitmapDeal().readBitmap(this,R.drawable.tools),"高级工具"));
        datas.add(new HomeIcon(new BitmapDeal().readBitmap(this,R.drawable.setting),"设置中心"));
        homeAdapter=new HomeAdapter(this,datas);
        gridView.setAdapter(homeAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 8:
                        Intent intent=new Intent(HomeActivity.this,SettingActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        break;

                }

            }
        });


    }
}
