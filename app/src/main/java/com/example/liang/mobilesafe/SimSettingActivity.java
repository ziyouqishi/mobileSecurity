package com.example.liang.mobilesafe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SimSettingActivity extends AppCompatActivity {
    private RelativeLayout relativeLayout;
    private TextView textView;
    private CheckBox checkBox;
    private TelephonyManager tm;
    private SharedPreferences sp;
    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sim_setting);
        initView();
    }

    void initView(){
        sp=getSharedPreferences("config",MODE_PRIVATE);
        relativeLayout=(RelativeLayout)findViewById(R.id.layout_blind);
        textView=(TextView)findViewById(R.id.tv_three);
        checkBox=(CheckBox)findViewById(R.id.isBlind);
        tm=(TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        next=(Button)findViewById(R.id.button_2) ;
        checkBox.setClickable(false);

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCheckBoxState();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SimSettingActivity.this,SetLinkmanActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
            }
        });
    }

    void setCheckBoxState(){
        SharedPreferences.Editor editor=sp.edit();
        if(checkBox.isChecked()){
            checkBox.setChecked(false);
            textView.setText("SIM卡没有绑定");
            editor.putString("sim",null);
        }else{
            checkBox.setChecked(true);
            textView.setText("SIM卡已经绑定");
            String sim=tm.getSimSerialNumber();
            editor.putString("sim",sim);
            next.setVisibility(View.VISIBLE);
            //Toast.makeText(this,sim,Toast.LENGTH_SHORT).show();
        }

        editor.commit();
    }
}
