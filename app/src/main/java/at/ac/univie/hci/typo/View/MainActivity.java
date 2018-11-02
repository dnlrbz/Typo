package at.ac.univie.hci.typo.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Map;

import at.ac.univie.hci.typo.Controller.StatisticsController;
import at.ac.univie.hci.typo.Model.DataBase.Database;
import at.ac.univie.hci.typo.Model.GameStatistics;
import at.ac.univie.hci.typo.Model.Player;
import at.ac.univie.hci.typo.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Database.mPlayerDAO.deleteAllPlayers();
       // Database.mGameStatsDAO.deleteAllGameStatistics();
       // Database.mPlayerDAO.deleteAllPlayers();
        Player v = new Player("Vasya");
        Database.mPlayerDAO.addPlayer(v);

       Database.mPlayerDAO.addPlayer(new Player("Petka"));

       Database.mGameStatsDAO.addGameStatistics(new GameStatistics(v, 1, 95.4,
               210, "Pff", 5.34, "12:34" ));
       Database.mGameStatsDAO.addGameStatistics(new GameStatistics(new Player("Petka"), 1, 95.4,
                210, "Pff", 5.34, "12:34" ));


       // Database.mPlayerDAO.addPlayer(new Player("VASYA"));
        System.out.println("ALL PLAYERS **********" + Database.mPlayerDAO.getAllPlayers().toString());

        System.out.println( "ALL STATS **********" +Database.mGameStatsDAO.getAllGameStatistics().toString());

        System.out.println("STATS BY NAME PETYA: " + Database.mGameStatsDAO.getGameStatisticsByPlayerName("Vasya").toString());

        Database.mPlayerDAO.deleteAllPlayers();


        System.out.println("ALL PLAYERS **********" + Database.mPlayerDAO.getAllPlayers().toString());


         Database.mGameStatsDAO.deleteGameStatisticsByPlayerName("Vasya");

         StatisticsController statisticsController = new StatisticsController();

         String mustBe = "almost";

         String have = "akmost";

         statisticsController.checkMissedKeys(mustBe, have);

         mustBe = "done";
         have = "dine";



        statisticsController.checkMissedKeys(mustBe, have);
         System.out.println("MOST missed key : " + statisticsController.getMostMissedKeyOfTheGame());



     // boolean isSaved = Database.mPlayerDAO.addPlayer(p);
     // boolean isalsosaved = Database.mPlayerDAO.addPlayer(v);



        TextView playerText = (TextView) findViewById(R.id.playerText);

       // playerText.setText(Database.mPlayerDAO.getAllPlayers().toString());


       // Database.mPlayerDAO.getPlayerById(1);

        //playerText.setText(Database.mPlayerDAO.getAllPlayers().toString());
        //db.close();








    }

}
