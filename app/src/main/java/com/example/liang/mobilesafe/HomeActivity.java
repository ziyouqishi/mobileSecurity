package com.example.liang.mobilesafe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

import Adapter.HomeAdapter;
import javabeen.HomeIcon;
import utitls.BitmapDeal;
import utitls.MD5Utils;

public class HomeActivity extends AppCompatActivity {
    private GridView gridView;
    private ArrayList<HomeIcon>datas;
    private HomeAdapter homeAdapter;
    private SharedPreferences sp;
    private EditText etPassword,passwordEnsure;
    private Button ok,cancle;
    private AlertDialog alertDialog;


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
        sp=getSharedPreferences("config",MODE_PRIVATE);
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
                    case 0:
                        showLostFoundDialog();
                        break;
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

    /**
     * 有密码，则弹出输入密码的对话框，否则弹出设置呢密码的对话框
     */
    void showLostFoundDialog(){
        if(issetUpPsw()){
            showEnterDialog();
        }else {
            showSetupPwdDialog();
        }

    }

    /**
     * 判断是否设置了密码
     * @return
     */
    boolean issetUpPsw(){
        String password=sp.getString("password",null);
        return !TextUtils.isEmpty(password);
    }

    void showSetupPwdDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        View view=View.inflate(this,R.layout.dialog_setuppwd,null);
        passwordEnsure=(EditText) view.findViewById(R.id.password_ensure);
        etPassword=(EditText) view.findViewById(R.id.et_password);
        ok=(Button)view.findViewById(R.id.ok);
        cancle=(Button)view.findViewById(R.id.cancel);

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();

            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password=etPassword.getText().toString().trim();
                String password_confirm=passwordEnsure.getText().toString().trim();

                if(TextUtils.isEmpty(password)||TextUtils.isEmpty(password_confirm)){
                    Toast.makeText(HomeActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                    return;

                }

                if(password.equals(password_confirm)){
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString("password", MD5Utils.encoder(password));
                    editor.commit();
                    Intent intent=new Intent(HomeActivity.this,LostFoundActivity.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                    alertDialog.dismiss();
                }else {
                    Toast.makeText(HomeActivity.this,"密码不一致，请重新输入",Toast.LENGTH_SHORT).show();
                }

            }
        });

        builder.setView(view);
        alertDialog=builder.show();

    }
    void showEnterDialog(){

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        View view=View.inflate(this,R.layout.dialog_enter_pwd,null);
        etPassword=(EditText) view.findViewById(R.id.et_password);
        ok=(Button)view.findViewById(R.id.ok);
        cancle=(Button)view.findViewById(R.id.cancel);

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();

            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password=etPassword.getText().toString().trim();
                String password_save=sp.getString("password",null);

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(HomeActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                    return;

                }

                if(MD5Utils.encoder(password).equals(password_save)){
                    Intent intent=new Intent(HomeActivity.this,LostFoundActivity.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                    alertDialog.dismiss();
                }else {
                    Toast.makeText(HomeActivity.this,"输入的密码不正确，请重新输入",Toast.LENGTH_SHORT).show();
                }

            }
        });

        builder.setView(view);
        alertDialog=builder.show();


    }
}
