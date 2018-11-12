package at.ac.univie.hci.typo.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import at.ac.univie.hci.typo.Model.DataBase.Database;
import at.ac.univie.hci.typo.Model.GameStatistics;
import at.ac.univie.hci.typo.Model.Player;
import at.ac.univie.hci.typo.Model.Score;
import at.ac.univie.hci.typo.Model.Comparators.ScoresComparator;
import at.ac.univie.hci.typo.Model.Comparators.StatisticsComparatorByNumber;

public class StatisticsController {
    /**
     * Enum, representing letters with their counters (how many times it was misstyped) and their String Values.
     */
    WordsManager wordsManager = new WordsManager();


    /**
     * Implementation of Facade and FactoryMethod patterns, returns whole statistics using inside methods
     * @return
     */
    public GameStatistics computeAndGetWholeGameStatistics(ArrayList<String> correctWordsArrayList, ArrayList<String> incorrectWordsList, Player player){
        /**
         * defining a most missed key of the game
         */
        for (int i=0; i < correctWordsArrayList.size(); i++) {
            checkMissedKeys(correctWordsArrayList.get(i), incorrectWordsList.get(i));
        }
        String mostMissedKey = getMostMissedKeyOfTheGame();
        int keysPerMinute = wordsManager.countKeysOfAllWords(incorrectWordsList);
        int gameCounter = Database.mGameStatsDAO.getGameStatisticsByPlayerName(player.getName()).size() + 1;









        return null;
    }







    /**
     *
     * @return element of Enum of Letters, that is most misstyped through the whole game
     */
    public String getMostMissedKeyOfTheGame() {
        int max = 0;

        MissedLetters mostMissedLetter = null;

        for (MissedLetters l : MissedLetters.values()){
            if (max <= l.getCounter()) {
                max = l.getCounter();
                mostMissedLetter = l;
            }
        }
        if (max>0){

            //Set all counters to Zero after the game and after
            // the result is saved in variable mostMissedLetter
            setMissedKeyCounterToZero();

            return mostMissedLetter.getName();
        }
        return null;
    }

    private enum MissedLetters {
        A (0,"A"), B(0,"B"), C(0,"C"), D(0,"D"), E(0,"E"), F(0,"F"), G(0,"G"), H(0,"H"), I(0,"I"),
        J(0,"J"), K(0,"K"), L(0,"L"), M(0,"M"), N(0,"N"), O(0,"O"), P(0,"P"), Q(0,"Q"), R(0,"R"),
        S(0,"S"), T(0,"T"), U(0,"U"), V(0,"V"), W(0,"W"), X(0,"X"), Y(0,"Y"), Z(0,"Z");

        private int counter;
        private String name;

        private MissedLetters(int counter,String name) {
            this.counter = 0;
            this.name = name;
        }

        private MissedLetters() {}

        public int getCounter() {
            return this.counter;
        }

        public void setCounter(int counter){
            this.counter = 0;
        }

        public String getName(){
            return this.name;
        }

        public void incrementCounter() {
            this.counter++;
        }

    };


    public void checkMissedKeys(String correct, String incorrect) {
        assert correct.length()==incorrect.length() : new IllegalArgumentException("Strings have different length");
        ArrayList<String> mustBeList = wordsManager.getArrayListOfStringsFromWord(correct);
        ArrayList<String> haveList = wordsManager.getArrayListOfStringsFromWord(incorrect);

        for (int i = 0; i < mustBeList.size(); i++) {
            if (mustBeList.get(i).equalsIgnoreCase(haveList.get(i)) == false) {
                System.out.println("Comparing two letters: " + mustBeList.get(i) + " and " + haveList.get(i));
                String temp = new String(mustBeList.get(i));
                getMissedLetterByItsStringValue(temp).incrementCounter();
            }
        }

    }




    private MissedLetters getMissedLetterByItsStringValue(String l) {
        try {
            for (MissedLetters letter : MissedLetters.values()) {
                if (l.equalsIgnoreCase(letter.getName())) {
                    return letter;
                }
            }
        } catch (NullPointerException nl) {
            System.out.println("Letter " + l.toUpperCase() + " NOT FOUND");
        }
        return null;
    }

    private void setMissedKeyCounterToZero() {
            for (MissedLetters letters: MissedLetters.values()){
                letters.setCounter(0);
            }
    }

    public int getHighScore() {

        int max = 0;

        for (GameStatistics gameStatistics : Database.mGameStatsDAO.getAllGameStatistics()) {
            if (max < gameStatistics.getScore()) {
                max = gameStatistics.getScore();
            }
        }
        return max;

    }


    public List<Score> getScoresList() {
        List<Score> scoreList = new ArrayList<Score>();

        for (Player player: Database.mPlayerDAO.getAllPlayers()) {
            Score score = new Score(player, getMaxScoreOfPlayer(player));
            scoreList.add(score);
        }

        Collections.sort(scoreList, new ScoresComparator());
        return scoreList;
    }


    private int getMaxScoreOfPlayer(Player player) {
        int max = 0;
        for (GameStatistics gameStatistics: Database.mGameStatsDAO.getGameStatisticsByPlayerName(player.getName())) {
            if (max < gameStatistics.getScore()) {
                max = gameStatistics.getScore();
            }
        }
        return max;
    }


    public List<GameStatistics> getStatisticsList(String playerName) {
        List<GameStatistics> statsList = Database.mGameStatsDAO.getGameStatisticsByPlayerName(playerName);


        Collections.sort(statsList, new StatisticsComparatorByNumber());
        return statsList;
    }




}
