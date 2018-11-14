package at.ac.univie.hci.typo.Model.Comparators;

import java.util.Comparator;

import at.ac.univie.hci.typo.Model.GameStatistics;

public class StatisticsComparatorByNumber implements Comparator<GameStatistics> {

    /**
     *
     * @param o1
     * @param o2
     * @return compared two Game Statistics by game number
     */
    @Override
    public int compare(GameStatistics o1, GameStatistics o2) {
        return o1.getGameCounter() - o2.getGameCounter();
    }

}
