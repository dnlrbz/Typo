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
    private int gameCounter;
    private double accuracy;
    private int keysPerMinute;
    private String mostMissedKey;
    private double maximumGpsSpeed;
    private String timeOfTheGame;


    public GameStatistics() {
    }

    public GameStatistics(Player player, int gameCounter, double accuracy, int keysPerMinute,
                          String mostMissedKey, double maximumGpsSpeed, String timeOfTheGame) {
        this.player = player;
        this.gameCounter = gameCounter;
        this.accuracy = accuracy;
        this.keysPerMinute = keysPerMinute;
        this.mostMissedKey = mostMissedKey;
        this.maximumGpsSpeed = maximumGpsSpeed;
        this.timeOfTheGame = timeOfTheGame;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
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

    public double getMaximumGpsSpeed() {
        return maximumGpsSpeed;
    }

    public void setMaximumGpsSpeed(double maximumGpsSpeed) {
        this.maximumGpsSpeed = maximumGpsSpeed;
    }

    public String getTimeOfTheGame() {
        return timeOfTheGame;
    }

    public void setTimeOfTheGame(String timeOfTheGame) {
        this.timeOfTheGame = timeOfTheGame;
    }

    @Override
    public String toString() {
        return "GameStatistics{" +
                "player=" + player +
                ", gameCounter=" + gameCounter +
                ", accuracy=" + accuracy +
                ", keysPerMinute=" + keysPerMinute +
                ", mostMissedKey='" + mostMissedKey + '\'' +
                ", maximumGpsSpeed=" + maximumGpsSpeed +
                ", timeOfTheGame='" + timeOfTheGame + '\'' +
                '}';
    }
}

