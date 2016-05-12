package nl.codepanda.chargeblock;

import android.content.Context;
import android.media.AudioManager;
import android.provider.ContactsContract;

/**
 * Created by Sander Geraedts - Codepanda on 28/04/2016.
 */
public class CAudioManager {
    private int ringerMode;
    private Context context;
    private AudioManager audio;

    public CAudioManager(Context context) {
        this.ringerMode = 2;
        this.context = context;
        this.audio = (AudioManager) this.context.getSystemService(Context.AUDIO_SERVICE);
    }

    private void writeRingerModeToDB(int r) {
        Database database = new Database(context);
        database.saveRingerMode(r);
    }

    private int getRingerModeFromDB() {
        Database database = new Database(context);
        return database.getRingerMode();
    }

    public void turnOffAudio() {
        ringerMode = audio.getRingerMode();
        writeRingerModeToDB(ringerMode);
        audio.setRingerMode(AudioManager.RINGER_MODE_SILENT);
    }

    public void turnAudioBackOn() {
        ringerMode = getRingerModeFromDB();
        audio.setRingerMode(ringerMode);
    }
}
