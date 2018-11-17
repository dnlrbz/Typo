package at.ac.univie.hci.typo.Controller;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import at.ac.univie.hci.typo.Controller.ActivityManagement.Activities;
import at.ac.univie.hci.typo.Model.DataBase.Database;
import at.ac.univie.hci.typo.Model.GameStatistics;
import at.ac.univie.hci.typo.Model.Player;
import at.ac.univie.hci.typo.Model.Score;
import at.ac.univie.hci.typo.Model.Comparators.ScoresComparator;
import at.ac.univie.hci.typo.Model.Comparators.StatisticsComparatorByNumber;

public class StatisticsController {

    WordsManager wordsManager = new WordsManager();



    /**
     * Implementation of Facade and FactoryMethod patterns, returns whole statistics using inside methods
     * @param correctWordsArrayList
     * @param incorrectWordsList
     * @param player
     * @param score of a game
     * @param activities during the game
     * @return complete Game Statistics
     */
    public GameStatistics computeAndGetWholeGameStatistics(ArrayList<String> correctWordsArrayList, ArrayList<String> incorrectWordsList, Player player, int score, ArrayList<String> activities){
        /**
         * defining a most missed key of the game
         */
        for (int i=0; i < correctWordsArrayList.size(); i++) {
            checkMissedKeys(correctWordsArrayList.get(i), incorrectWordsList.get(i));
        }
        String mostMissedKey = getMostMissedKeyOfTheGame();
        int keysPerMinute = wordsManager.countKeysOfAllWords(incorrectWordsList);

        //TODO clear edittext after new word appears

        Double accuracy = computeAccuracy(correctWordsArrayList, incorrectWordsList);
        String context = getContextOfAGame(activities);
        String timeOfGame = getTimeOfTheGame();
        int gameCounter= getGameCounter(player);

        return new GameStatistics(player, gameCounter, accuracy,
                keysPerMinute, mostMissedKey, context, timeOfGame,gameCounter );
    }

    public int getGameCounter(Player player) {
        return Database.mGameStatsDAO.getGameStatisticsByPlayerName(player.getName()).size()+1;
    }

    public String getTimeOfTheGame() {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("HH:mm");
        String timeOfGame =  format.format(date);
        return timeOfGame;
    }


    public String getContextsList(ArrayList<String> activities) {
        ArrayList<String> contextList= new ArrayList<String>();
        String result = "";

        if (contextList.size()==0) {
            contextList.add(Activities.IN_VEHICLE_ACTIVITY);
        }

        for (String activity: activities) {
            if (!contextList.contains(activity)) {
                contextList.add(activity);
            }
        }

        for (int i = 0; i< contextList.size(); i++) {
            if (i == contextList.size()-1) {
                result += contextList.get(i);
            }
            else {
                result += contextList.get(i) + "\n";
            }
        }




        return result;

    }

    /**
     *
     * @param activities
     * @return the most frequent activity during the game. If there is only once the "in vehicle" action, then it will be returned, because it takes 20-40 seconds to determine the "in vehicle" activity
     */
    public String getContextOfAGame(ArrayList<String> activities) {

        assert activities.size() > 0 : "THERE IS NO ACTIVITIES!";

        if (activities.size() == 0) {
            return Activities.STILL_ACTIVITY;
        }
        for (String activity: activities) {
            if (activity.equalsIgnoreCase(Activities.IN_VEHICLE_ACTIVITY)) {
                return Activities.IN_VEHICLE_ACTIVITY;
            }
        }

        int count = 1, tempCount;
        String popular = activities.get(0);
        String temp = null;
        for (int i = 0; i < (activities.size() - 1); i++)
        {
            temp = activities.get(i);

            tempCount = 0;
            for (int j = 1; j < activities.size(); j++)
            {

                if (temp.equalsIgnoreCase(activities.get(j)))
                    tempCount++;
            }
            if (tempCount > count)
            {
                popular = temp;
                count = tempCount;
            }
        }
        return popular;
    }

    /**
     *
     * @param correctWordsArrayList
     * @param incorrectWordsList
     * @return Computed Accuracy with formula accuracy = (correct input letters/all input letters)
     */
    public Double computeAccuracy(ArrayList<String> correctWordsArrayList, ArrayList<String> incorrectWordsList) {
        int allCharsInWordsInArrayCounter = 0;

        int correctCharsInWordsInArrayCounter = 0;

        for (int i = 0; i < correctWordsArrayList.size(); i++) {
            String inCorrectWord = incorrectWordsList.get(i);
            String correctWord = correctWordsArrayList.get(i);
            allCharsInWordsInArrayCounter = allCharsInWordsInArrayCounter + correctWord.length();

            assert correctWord.length()==inCorrectWord.length() : "WORDS LENGTH IS NOT EQUAL EXCEPTION";

            for (int j = 0; j < correctWord.length(); j++) {
                if(correctWord.charAt(j) == inCorrectWord.charAt(j)) {
                    correctCharsInWordsInArrayCounter++;
                }
            }
        }

        return (correctCharsInWordsInArrayCounter*100.0)/allCharsInWordsInArrayCounter;

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
        return mostMissedLetter!=null? mostMissedLetter.getName() : "NOT EXIST";
    }


    /**
     * Enum, representing letters with their counters (how many times it was misstyped) and their String Values.
     */
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

    /**
     * A method compares all words and determines which letter is most frequently missing
     * @param correct list of correct words
     * @param incorrect list of incorrect words
     */
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


    /**
     *
     * @param l
     * @return element of an ENUM, which string value is l
     */
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

    /**
     * sets all counters in enum to zero
     */
    private void setMissedKeyCounterToZero() {
            for (MissedLetters letters: MissedLetters.values()){
                letters.setCounter(0);
            }
    }

    /**
     *
     * @return the highest score of all games
     */
    public int getHighScore() {

        int max = 0;

        for (GameStatistics gameStatistics : Database.mGameStatsDAO.getAllGameStatistics()) {
            if (max < gameStatistics.getScore()) {
                max = gameStatistics.getScore();
            }
        }
        return max;

    }

    /**
     * Creates a List with Scores from all Players
     * @return List with all scores
     */
    public List<Score> getScoresList() {
        List<Score> scoreList = new ArrayList<Score>();

        for (Player player: Database.mPlayerDAO.getAllPlayers()) {
            Score score = new Score(player, getMaxScoreOfPlayer(player));
            scoreList.add(score);
        }

        Collections.sort(scoreList, new ScoresComparator());
        return scoreList;
    }

    /**
     *
     * @param player
     * @return a score of a particular player
     */
    private int getMaxScoreOfPlayer(Player player) {
        int max = 0;
        for (GameStatistics gameStatistics: Database.mGameStatsDAO.getGameStatisticsByPlayerName(player.getName())) {
            if (max < gameStatistics.getScore()) {
                max = gameStatistics.getScore();
            }
        }
        return max;
    }

    /**
     *
     * @param playerName
     * @return sorted list by gameNumber with statistics for player
     */
    public List<GameStatistics> getStatisticsListForPlayer(String playerName) {
        List<GameStatistics> statsList = Database.mGameStatsDAO.getGameStatisticsByPlayerName(playerName);


        Collections.sort(statsList, new StatisticsComparatorByNumber());
        return statsList;
    }

    public boolean playerExists(Player player) {
        boolean exists = false;
        for (Player player1: Database.mPlayerDAO.getAllPlayers()) {
            if (player.getName().equalsIgnoreCase(player1.getName())) {
                exists = true;
            }
        }
        return exists;
    }

    public static DecimalFormat getDecimalFormat() {
        DecimalFormatSymbols dfs = DecimalFormatSymbols.getInstance();
        dfs.setDecimalSeparator('.');
        return new DecimalFormat("0.0", dfs);
    }




}
