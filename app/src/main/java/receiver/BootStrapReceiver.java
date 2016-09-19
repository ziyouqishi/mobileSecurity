package receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.telephony.TelephonyManager;
import android.widget.Toast;

/**
 * Created by liang on 2016/9/18.
 */

public class BootStrapReceiver extends BroadcastReceiver {
    private SharedPreferences sp;
    private TelephonyManager tm;
    @Override
    public void onReceive(Context context, Intent intent) {
        sp=context.getSharedPreferences("config",Context.MODE_PRIVATE);
        tm=(TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        String saveSim=sp.getString("sim","");
        String currentSim=tm.getSimSerialNumber();

        if(saveSim.equals(currentSim)){
            Toast.makeText(context,"sim未变更",Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(context,"sim卡变更了",Toast.LENGTH_SHORT).show();
        }


    }
}
