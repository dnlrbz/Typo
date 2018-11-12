package at.ac.univie.hci.typo.Model.Comparators;

import java.util.Comparator;

import at.ac.univie.hci.typo.Model.GameStatistics;

public class StatisticsComparatorByNumber implements Comparator<GameStatistics> {

    @Override
    public int compare(GameStatistics o1, GameStatistics o2) {
        return o1.getGameCounter() - o2.getGameCounter();
    }

}
