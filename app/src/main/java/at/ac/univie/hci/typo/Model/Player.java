package at.ac.univie.hci.typo.Model;

import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

import at.ac.univie.hci.typo.Model.DataBase.Database;

public class Player {

    //private static final AtomicInteger count = new AtomicInteger(0);

    private String name;

    public Player() {};



    public Player(String name) {
        this.name = name.toUpperCase(Locale.getDefault());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.toUpperCase(Locale.getDefault());
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +

                '}';
    }

}
