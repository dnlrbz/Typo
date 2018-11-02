package at.ac.univie.hci.typo.Model.DataBase;

import java.util.ArrayList;

import at.ac.univie.hci.typo.Model.GameStatistics;

public interface IGameStatsDAO {

    GameStatistics getGameStatisticsById(int id);

    ArrayList<GameStatistics> getGameStatisticsByPlayerName(String playerName);

    ArrayList<GameStatistics> getAllGameStatistics();

    boolean addGameStatistics(GameStatistics gameStatistics);

    boolean deleteAllGameStatistics();

    boolean deleteGameStatisticsByPlayerName(String playerName);



}
