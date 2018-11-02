package at.ac.univie.hci.typo.Model;

import java.util.ArrayList;
import java.util.Arrays;

import at.ac.univie.hci.typo.Model.DataBase.WordsInterface;

public class Words implements WordsInterface {

    private final ArrayList<String> wordsList;

    public Words() {
        this.wordsList = new ArrayList<String>(Arrays.asList(WORDS_STRING.split("\n")));
    }


    @Override
    public ArrayList<String> getList() {
        return wordsList;
    }
}
