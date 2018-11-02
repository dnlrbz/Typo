package at.ac.univie.hci.typo.Model.DataBase;

/**
 * Interface to initialize schema of a player table
 */
public interface IPlayerSchema {
    String PLAYER_TABLE = " players ";
    String COLUMN_NAME = "_name";
    //String COLUMN_ID = "_id";

    String PLAYER_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS " +
            PLAYER_TABLE +
            " ( " +
            COLUMN_NAME + " TEXT NOT NULL PRIMARY KEY " +
            //COLUMN_ID + " INTEGER AUTOINCREMENT " +
            " );";
    String[] PLAYER_COLUMNS = new String[] { COLUMN_NAME, /*COLUMN_ID*/ };
}
