package com.example.liang.mobilesafe;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    private TextView versionName;
    private static final int ENTER=1;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==ENTER){
                Intent intent=new Intent(SplashActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        enterHomeActivity();
    }

    void initView(){
        versionName=(TextView)findViewById(R.id.version_name);
        versionName.setText(getVersionName());
    }

    private String getVersionName(){
        PackageManager packageManager=getPackageManager();

        try {
            PackageInfo packageInfo=packageManager.getPackageInfo(getPackageName(),0);
            String name="版本号："+packageInfo.versionName;
            return name;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

  void enterHomeActivity(){
      new Thread(new Runnable() {
          @Override
          public void run() {
              SystemClock.sleep(3000);
              Message message=Message.obtain();
              message.what=ENTER;
              handler.sendMessage(message);

          }
      }).start();
  }
}
