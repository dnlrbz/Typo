package at.ac.univie.hci.typo.Controller;

import android.app.Application;

import at.ac.univie.hci.typo.Model.DataBase.Database;

/**
 * This is a class, that is used when the whole Application is starting
 */
public class MainApplication extends Application {

    public static final String TAG = MainApplication.class.getSimpleName();
    public static Database mDb;

    /**
     * Method to create and open a Database for further use
     */
    @Override
    public void onCreate() {
        super.onCreate();
        mDb = new Database(this);
        mDb.open();

    }

    @Override
    public void onTerminate() {
        mDb.close();
        super.onTerminate();
    }

}
