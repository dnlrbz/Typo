package at.ac.univie.hci.typo.Controller;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import at.ac.univie.hci.typo.Model.DataBase.Database;

/**
 * This is a class, that is used when the whole Application is starting
 */
public class MainApplication extends Application {

    public static final String TAG = MainApplication.class.getSimpleName();
    public static Database mDb;
    public static final String CHANNEL_1_ID = "channel1";

    /**
     * Method to create and open a Database for further use as soon as application is started
     */
    @Override
    public void onCreate() {
        super.onCreate();
        mDb = new Database(this);
        mDb.open();
        createNotificationChannels();

    }

    //Notifications work only for API 26 and higher
    public void createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(
                    CHANNEL_1_ID, "Channel 1", NotificationManager.IMPORTANCE_HIGH
            );
            notificationChannel.setDescription("This is channel 1");
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    /**
     * Close the DB as soon as Application is terminated
     */
    @Override
    public void onTerminate() {
        mDb.close();
        super.onTerminate();
    }

}
