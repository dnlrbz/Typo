package at.ac.univie.hci.typo.Model.DataBase;

public interface IGameStatisticsSchema {
    String GAME_STATISTICS_TABLE = " statistics ";
    String COLUMN_ID = "_id";
    String COLUMN_GAME_COUNTER = "_gamecounter";
    String COLUMN_ACCURACY = "_accuracy";
    String COLUMN_KEYS_PER_MINUTE = "_keys_per_minute";
    String COLUMN_MOST_MISSED_KEY = "_most_missed_key";
    String COLUMN_MAX_GPS_SPEED = "_max_gps_speed";
    String COLUMN_TIME_OF_THE_GAME = "_time_of_the_game";
    String COLUMN_PLAYER_NAME = "_player_name";


    String GAME_STATISTICS_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS " +
            GAME_STATISTICS_TABLE +
            " ( " +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_GAME_COUNTER + " INTEGER NOT NULL, " +
            COLUMN_ACCURACY + " REAL NOT NULL, " +
            COLUMN_KEYS_PER_MINUTE + " INTEGER NOT NULL, " +
            COLUMN_MAX_GPS_SPEED + " REAL NOT NULL, " +
            COLUMN_TIME_OF_THE_GAME + " TEXT NOT NULL, " +
            COLUMN_MOST_MISSED_KEY + " TEXT NOT NULL, " +
            COLUMN_PLAYER_NAME + " TEXT NOT NULL, " +
            "FOREIGN KEY(" + COLUMN_PLAYER_NAME + ") REFERENCES " +
            IPlayerSchema.PLAYER_TABLE + "(" + IPlayerSchema.COLUMN_NAME + ")" + " ON DELETE CASCADE " +
            " );";





    String[] GAME_STATISTICS_COLUMNS = new String[] { COLUMN_ID, COLUMN_GAME_COUNTER, COLUMN_ACCURACY,
    COLUMN_KEYS_PER_MINUTE, COLUMN_MAX_GPS_SPEED, COLUMN_TIME_OF_THE_GAME, COLUMN_PLAYER_NAME, COLUMN_MOST_MISSED_KEY };
}
