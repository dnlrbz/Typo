package at.ac.univie.hci.typo.Model;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class GameStatistics {

    private Player player;
    private int id;
    private int gameCounter;
    public int score;
    private double accuracy;
    private int keysPerMinute;
    private String mostMissedKey;
    private String context;
    private String timeOfTheGame;


    public GameStatistics() {
    }




    public GameStatistics(Player player, int gameCounter, double accuracy, int keysPerMinute,
                          String mostMissedKey, String context, String timeOfTheGame, int id, int score) {
        this.id = id;
        this.player = player;
        this.score = score;

        this.gameCounter = gameCounter;
        this.accuracy = accuracy;
        this.keysPerMinute = keysPerMinute;
        this.mostMissedKey = mostMissedKey;
        this.context = context;
        this.timeOfTheGame = timeOfTheGame;
    }

    //Constructor without ID
    public GameStatistics(Player player, int gameCounter, Double accuracy, int keysPerMinute,
                          String mostMissedKey, String context, String timeOfTheGame, int score) {
        this.score = score;
        this.player = player;
        this.gameCounter = gameCounter;
        this.accuracy = accuracy;
        this.keysPerMinute = keysPerMinute;
        this.mostMissedKey = mostMissedKey;
        this.context = context;
        this.timeOfTheGame = timeOfTheGame;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getGameCounter() {
        return gameCounter;
    }

    public void setGameCounter(int gameCounter) {
        this.gameCounter = gameCounter;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public int getKeysPerMinute() {
        return keysPerMinute;
    }

    public void setKeysPerMinute(int keysPerMinute) {
        this.keysPerMinute = keysPerMinute;
    }

    public String getMostMissedKey() {
        return mostMissedKey;
    }

    public void setMostMissedKey(String mostMissedKey) {
        this.mostMissedKey = mostMissedKey;
    }



    public String getTimeOfTheGame() {
        return timeOfTheGame;
    }

    public void setTimeOfTheGame(String timeOfTheGame) {
        this.timeOfTheGame = timeOfTheGame;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "GameStatistics{" +
                "player=" + player +
                ", id=" + id +
                ", gameCounter=" + gameCounter +
                ", accuracy=" + accuracy +
                ", keysPerMinute=" + keysPerMinute +
                ", mostMissedKey='" + mostMissedKey + '\'' +
                ", context='" + context + '\'' +
                ", timeOfTheGame='" + timeOfTheGame + '\'' +
                '}';
    }
}

