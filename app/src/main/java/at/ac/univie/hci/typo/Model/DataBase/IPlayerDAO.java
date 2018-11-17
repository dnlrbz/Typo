package at.ac.univie.hci.typo.Model.DataBase;

import java.util.ArrayList;

import at.ac.univie.hci.typo.Model.Player;

public interface IPlayerDAO {
    /**
     * Method to fetch a Player from the database by his id.
     * @param playerName of the Player
     * @return Player
     */
    Player getPlayerByName(String playerName);

    /**
     * Method to fetch all players from the database.
     * @return
     */
    ArrayList<Player> getAllPlayers();

    /**
     * Method to add player to a database
     * @param player
     * @return true or false
     */
    boolean addPlayer(Player player);

    boolean deleteAllPlayers();

    public boolean deletePlayerByName(String name);

}
