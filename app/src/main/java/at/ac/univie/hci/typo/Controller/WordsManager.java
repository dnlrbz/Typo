package at.ac.univie.hci.typo.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class WordsManager {


    /**
     *
     * @param word
     * @return returns a list with all letters from a word
     */
    public ArrayList<String> getArrayListOfStringsFromWord(String word) {
        ArrayList<String> lettersOfWord = new ArrayList<String>(Arrays.asList(word.split("")));
        lettersOfWord.remove(0);
        return lettersOfWord;
    }


    /**
     *
     * @param incorrectWordsList
     * @return count letters in all words in a list
     */
    public int countKeysOfAllWords(ArrayList<String> incorrectWordsList) {
        int counter = 0;
        for (String w : incorrectWordsList) {
            counter += getArrayListOfStringsFromWord(w).size();
        }
        return counter;
    }

    /**
     *
     * @param min
     * @param max
     * @return random number in range
     */
    public static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
