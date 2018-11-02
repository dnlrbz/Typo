package at.ac.univie.hci.typo.Model.DataBase;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import at.ac.univie.hci.typo.Model.DataBase.DBContentProvider;
import at.ac.univie.hci.typo.Model.DataBase.IPlayerDAO;
import at.ac.univie.hci.typo.Model.DataBase.IPlayerSchema;
import at.ac.univie.hci.typo.Model.Player;

public class PlayerDAO extends DBContentProvider implements IPlayerSchema, IPlayerDAO {

    private Cursor cursor;
    private ContentValues initialValues;

    public PlayerDAO(SQLiteDatabase db) {
        super(db);
    }

    @Override
    protected Player cursorToEntity(Cursor cursor) {
        Player player = new Player();

        int nameIndex;
        //int idIndex;

        if (cursor!=null) {
            /*
            if (cursor.getColumnIndex(COLUMN_ID) != -1) {

                idIndex = cursor.getColumnIndexOrThrow(COLUMN_ID);
                player.setId(Integer.parseInt(cursor.getString(idIndex)));
            }
            */
            if (cursor.getColumnIndexOrThrow(COLUMN_NAME) != -1) {
                nameIndex = cursor.getColumnIndexOrThrow(COLUMN_NAME);
                player.setName(cursor.getString(nameIndex));
            }
        }

        return player;

    }

    @Override
    public Player getPlayerByName(String playerName) {
        final String selectionArgs[] = { String.valueOf(playerName.toUpperCase()) };
        final String selection = COLUMN_NAME + " = ?";
        Player player = new Player();

        cursor = super.query(PLAYER_TABLE, PLAYER_COLUMNS, selection, selectionArgs, COLUMN_NAME);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                player = cursorToEntity(cursor);
                cursor.moveToNext();

            }
            cursor.close();
        }
        return player;
    }

    @Override
    public ArrayList<Player> getAllPlayers() {
        ArrayList<Player> playerList = new ArrayList<Player>();
        cursor = super.query(PLAYER_TABLE, PLAYER_COLUMNS, null, null, COLUMN_NAME);

        if (cursor!=null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Player player = cursorToEntity(cursor);
                playerList.add(player);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return playerList;
    }

    @Override
    public boolean addPlayer(Player player) {
        player.setName(player.getName().toUpperCase());
        setContentValue(player);
        try {
            return super.insert(PLAYER_TABLE, getContentValue()) > 0;
        } catch (SQLiteConstraintException se) {
            Log.w("Database**********", se.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteAllPlayers() {
        mDb.execSQL("delete from "+ PLAYER_TABLE);
        return true;
    }

    private void setContentValue(Player player) {
        initialValues = new ContentValues();

        initialValues.put(COLUMN_NAME, player.getName());
        //initialValues.put(COLUMN_ID, player.id);



    }

    private ContentValues getContentValue() {
        return initialValues;
    }
}
