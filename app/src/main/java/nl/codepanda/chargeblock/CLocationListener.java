package nl.codepanda.chargeblock;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

/**
 * Created by Sander Geraedts - Codepanda on 20/04/2016.
 */
public class CLocationListener implements LocationListener {

    private Context context;

    public CLocationListener(Context context) {
        this.context = context;
        LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 100, this);
            this.onLocationChanged(null);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        float currentSpeed = location.getSpeed();
        Database database = new Database(context);
        if(currentSpeed >= database.getSpeed()){
            CAudioManager audio = new CAudioManager(context);
            audio.turnOffAudio();
        } else {
            CAudioManager audio = new CAudioManager(context);
            audio.turnAudioBackOn();
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
