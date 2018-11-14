package at.ac.univie.hci.typo.Model.Comparators;

import java.util.Comparator;

import at.ac.univie.hci.typo.Model.Score;

public class ScoresComparator implements Comparator<Score> {

    /**
     *
     * @param o1
     * @param o2
     * @return compared two Score objects by score
     */
    @Override
    public int compare(Score o1, Score o2) {
        return o2.getScore() - o1.getScore();
    }

}

