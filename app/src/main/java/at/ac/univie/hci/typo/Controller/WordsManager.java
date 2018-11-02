package at.ac.univie.hci.typo.Controller;

import java.util.ArrayList;
import java.util.Arrays;

public class WordsManager {

    public ArrayList<String> getArrayListOfStringsFromWord(String word) {
        ArrayList<String> lettersOfWord = new ArrayList<String>(Arrays.asList(word.split("")));
        lettersOfWord.remove(0);
        return lettersOfWord;
    }

    public int countKeysOfAllWords(ArrayList<String> incorrectWordsList) {
        int counter = 0;
        for (String w : incorrectWordsList) {
            counter += getArrayListOfStringsFromWord(w).size();
        }
        return counter;
    }
}
