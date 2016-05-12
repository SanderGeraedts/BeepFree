package nl.codepanda.chargeblock;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sander Geraedts - Codepanda on 28/04/2016.
 */
public class Database {
    public static final String KEY_ID = "ID";
    public static final String KEY_RINGER = "RINGER";
    public static final String KEY_SPEED = "SPEED";

    private static final String DATABASE_NAME = "BEEPFREE";
    private static final String DATABASE_TABLE = "PREFERENCES";
    private static final Integer DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS " + DATABASE_TABLE + "("
            + KEY_ID + "INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_RINGER + "INTEGER, "
            + KEY_SPEED + "DECIMAL);";

    private static final String TABLE_FIRST_INSERT = "INSERT OR REPLACE INTO " + DATABASE_TABLE
            + "(" + KEY_ID + ", " + KEY_RINGER + ", " + KEY_SPEED +") VALUES (1, 2, 3.5);";
    private final Context context;

    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public Database(Context context) {
        this.context = context;
    }

    public Database open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        DBHelper.close();
    }

    public boolean saveSpeed(float speed) {
        try {
            ContentValues args = new ContentValues();
            args.put(KEY_SPEED, speed);
            db.update(DATABASE_TABLE, args, KEY_ID + "=1", null);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean saveRingerMode(int ringermode) {
        try{
            ContentValues args = new ContentValues();
            args.put(KEY_RINGER, ringermode);
            db.update(DATABASE_TABLE, args, KEY_ID + "=1", null);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public float getSpeed() {
        float value = (float) 3.5;
//        Cursor cursor = db.query(true, DATABASE_TABLE, new String[] {KEY_ID, KEY_SPEED}, KEY_ID + "=1", null,null,null,null,null);
//        if(cursor != null) {
//            cursor.moveToFirst();
//        }
//        value = cursor.getFloat(1);
        return value;
    }

    public int getRingerMode() {
        int value = 2;
        Cursor cursor = db.query(true, DATABASE_TABLE, new String[] {KEY_ID, KEY_RINGER}, KEY_ID + "=1", null,null,null,null,null);
        if(cursor != null) {
            cursor.moveToFirst();
        }
        value = cursor.getInt(1);
        return value;
    }

    private static class DatabaseHelper extends SQLiteOpenHelper{
        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try{
                db.execSQL(DATABASE_CREATE);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try{
                db.execSQL(TABLE_FIRST_INSERT);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
