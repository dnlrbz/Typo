package at.ac.univie.hci.typo.Model.DataBase;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database {

    private static final String TAG = "TypoDataBase";
    private static final String DATABASE_NAME = "typodatabase.db";
    private DatabaseHelper mDbHelper;
    private static final int DATABASE_VERSION = 1;
    private final Context mContext;
    public static PlayerDAO mPlayerDAO;
    public static GameStatsDAO mGameStatsDAO;


    public Database open() throws SQLException {

        mDbHelper = new DatabaseHelper(mContext);
        SQLiteDatabase mDb = mDbHelper.getWritableDatabase();
        mDb.setForeignKeyConstraintsEnabled(true);

        mPlayerDAO = new PlayerDAO(mDb);
        mGameStatsDAO = new GameStatsDAO(mDb);

        return this;
    }

    public void close() {
        System.out.println("Database closed.");
        mDbHelper.close();
    }

    public Database(Context context) {
        this.mContext = context;
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(IPlayerSchema.PLAYER_TABLE_CREATE);
            db.execSQL(IGameStatisticsSchema.GAME_STATISTICS_TABLE_CREATE);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading Database Version from " + oldVersion
                    + " to " + newVersion + " which destroys all old data");

            db.execSQL("DROP TABLE IF EXISTS " + IPlayerSchema.PLAYER_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + IGameStatisticsSchema.GAME_STATISTICS_TABLE);
            onCreate(db);
        }
    };



}
