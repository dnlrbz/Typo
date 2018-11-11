package at.ac.univie.hci.typo.Model;

import java.util.Comparator;

public class ScoresComparator implements Comparator<Score> {


    @Override
    public int compare(Score o1, Score o2) {
        return o1.getScore() - o2.getScore();
    }

}

