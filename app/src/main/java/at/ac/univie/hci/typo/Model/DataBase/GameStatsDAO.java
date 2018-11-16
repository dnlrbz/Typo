package at.ac.univie.hci.typo.Model.DataBase;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import at.ac.univie.hci.typo.Model.GameStatistics;
import at.ac.univie.hci.typo.Model.Player;

public class GameStatsDAO extends DBContentProvider implements IGameStatisticsSchema, IGameStatsDAO {

    private Cursor cursor;
    private ContentValues initialValues;

    public GameStatsDAO(SQLiteDatabase db) {
        super(db);
    }



    @Override
    public GameStatistics getGameStatisticsById(int id) {
        final String selectionArgs[] = { String.valueOf(id) };
        final String selection = COLUMN_ID + " = ?";
        GameStatistics gameStatistics = new GameStatistics();
        cursor = super.query(GAME_STATISTICS_TABLE, GAME_STATISTICS_COLUMNS, selection, selectionArgs, COLUMN_ID);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                gameStatistics = cursorToEntity(cursor);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return gameStatistics;
    }

    @Override
    public ArrayList<GameStatistics> getGameStatisticsByPlayerName(String playerName) {
        ArrayList<GameStatistics> gameStatisticsArrayList = new ArrayList<GameStatistics>();

        for (GameStatistics gameStatistics : getAllGameStatistics()) {
            if (gameStatistics.getPlayer().getName().equalsIgnoreCase(playerName)){
                gameStatisticsArrayList.add(gameStatistics);
            }
        }

        return gameStatisticsArrayList;

    }

    @Override
    public ArrayList<GameStatistics> getAllGameStatistics() {
        ArrayList<GameStatistics> gameStatisticsArrayList = new ArrayList<GameStatistics>();
        cursor = super.query(GAME_STATISTICS_TABLE, GAME_STATISTICS_COLUMNS, null,
                null, COLUMN_ID);

        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                GameStatistics gameStatistics = cursorToEntity(cursor);
                gameStatisticsArrayList.add(gameStatistics);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return gameStatisticsArrayList;
    }

    @Override
    public boolean addGameStatistics(GameStatistics gameStatistics) {
        setContentValue(gameStatistics);
        try {
            System.out.println("OK *** database saved statistics for + " + gameStatistics.getPlayer().getName());
            return super.insert(GAME_STATISTICS_TABLE, getContentValue()) > 0;
        } catch (SQLiteConstraintException ex) {
            Log.w("Database", ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteAllGameStatistics() {
        mDb.execSQL("delete from "+ GAME_STATISTICS_TABLE);
        return true;
    }

    @Override
    public boolean deleteGameStatisticsByPlayerName(String playerName) {
        mDb.delete(GAME_STATISTICS_TABLE, COLUMN_PLAYER_NAME +" = ?", new String[]{ playerName.toUpperCase() });
        return true;
    }

    @Override
    public boolean deleteGameStatisticsById(int id) {
        mDb.delete(GAME_STATISTICS_TABLE, COLUMN_ID +" = ?", new String[]{ String.valueOf(id) });
        return true;
    }

    @Override
    protected GameStatistics cursorToEntity(Cursor cursor) {
        GameStatistics gameStatistics = new GameStatistics();

        int idIndex, gameCounterIndex, accuracyIndex,
                keysPerMinuteIndex, mostMissedKeyIndex,
                contextIndex, timeOfGameIndex, playerNameIndex, scoreIndex;

        if (cursor != null) {

            if (cursor.getColumnIndex(COLUMN_GAME_COUNTER) != -1) {
                gameCounterIndex = cursor.getColumnIndexOrThrow(COLUMN_GAME_COUNTER);
                gameStatistics.setGameCounter(cursor.getInt(gameCounterIndex));
            }
            if (cursor.getColumnIndex(COLUMN_ACCURACY) != -1) {
                accuracyIndex = cursor.getColumnIndexOrThrow(COLUMN_ACCURACY);
                gameStatistics.setAccuracy(cursor.getDouble(accuracyIndex));
            }
            if (cursor.getColumnIndex(COLUMN_KEYS_PER_MINUTE) != -1) {
                keysPerMinuteIndex = cursor.getColumnIndexOrThrow(COLUMN_KEYS_PER_MINUTE);
                gameStatistics.setKeysPerMinute(cursor.getInt(keysPerMinuteIndex));
            }
            if (cursor.getColumnIndex(COLUMN_MOST_MISSED_KEY) != -1) {
                mostMissedKeyIndex = cursor.getColumnIndexOrThrow(COLUMN_MOST_MISSED_KEY);
                gameStatistics.setMostMissedKey(cursor.getString(mostMissedKeyIndex));
            }
            if (cursor.getColumnIndex(COLUMN_CONTEXT) != -1) {
                contextIndex = cursor.getColumnIndexOrThrow(COLUMN_CONTEXT);
                gameStatistics.setContext(cursor.getString(contextIndex));
            }
            if (cursor.getColumnIndex(COLUMN_TIME_OF_THE_GAME) != -1) {
                timeOfGameIndex = cursor.getColumnIndexOrThrow(COLUMN_TIME_OF_THE_GAME);
                gameStatistics.setTimeOfTheGame(cursor.getString(timeOfGameIndex));
            }
            if (cursor.getColumnIndex(COLUMN_PLAYER_NAME) != -1) {
                playerNameIndex = cursor.getColumnIndexOrThrow(COLUMN_PLAYER_NAME);
                gameStatistics.setPlayer(new Player(cursor.getString(playerNameIndex)));
            }
            if (cursor.getColumnIndex(COLUMN_ID) != -1) {
                idIndex = cursor.getColumnIndexOrThrow(COLUMN_ID);
                gameStatistics.setId(cursor.getInt(idIndex));
            }
            if (cursor.getColumnIndex(COLUMN_SCORE) != -1) {
                scoreIndex = cursor.getColumnIndexOrThrow(COLUMN_SCORE);
                gameStatistics.setScore(cursor.getInt(scoreIndex));
            }

        }

        return gameStatistics;
    }

    private void setContentValue(GameStatistics gameStatistics){
        initialValues = new ContentValues();
        initialValues.put(COLUMN_GAME_COUNTER, gameStatistics.getGameCounter());
        initialValues.put(COLUMN_ACCURACY, gameStatistics.getAccuracy());
        initialValues.put(COLUMN_KEYS_PER_MINUTE, gameStatistics.getKeysPerMinute());
        initialValues.put(COLUMN_MOST_MISSED_KEY, gameStatistics.getMostMissedKey());
        initialValues.put(COLUMN_CONTEXT, gameStatistics.getContext());
        initialValues.put(COLUMN_TIME_OF_THE_GAME, gameStatistics.getTimeOfTheGame());
        initialValues.put(COLUMN_PLAYER_NAME, gameStatistics.getPlayer().getName());
        initialValues.put(COLUMN_ID, gameStatistics.getId());
        initialValues.put(COLUMN_SCORE, gameStatistics.getScore());
    }

    private ContentValues getContentValue() {
        return initialValues;
    }
}
