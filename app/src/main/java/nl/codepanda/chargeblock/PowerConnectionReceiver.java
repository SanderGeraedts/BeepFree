package nl.codepanda.chargeblock;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.BatteryManager;
import android.util.Log;

/**
 * Created by Sander Geraedts - Codepanda on 02/04/2016.
 */
public class PowerConnectionReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        ConnectivityManager conn = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = conn.getActiveNetworkInfo();

        if(wifi.isConnected() && wifi.getType() == conn.TYPE_WIFI) {
            //Do stuffs...
        }
    }
}
