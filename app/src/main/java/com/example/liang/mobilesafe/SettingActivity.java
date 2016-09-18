package com.example.liang.mobilesafe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity {
    private LinearLayout linearLayout;
    private TextView textView;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
    }

    void initView(){
        linearLayout=(LinearLayout)findViewById(R.id.activity_setting);
        textView=(TextView)findViewById(R.id.tv_desc);
        checkBox=(CheckBox)findViewById(R.id.checkbox);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxState();

            }
        });
    }

    void checkBoxState(){
        if(checkBox.isChecked()){
            checkBox.setChecked(false);
            textView.setText("当前自动升级已经关闭");
        }else {
            checkBox.setChecked(true);
            textView.setText("当前自动升级已经开启");
        }
    }
}
