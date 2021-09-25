package cover.sets;

import java.util.ArrayList;

/**
 * Reprezentuje zbiór będący teoriomnogościową sumą zbiorów.
 *
 * @author Mateusz Nowakowski
 */
public class SetUnion implements Set {
    /**
     * Lista zbiorów wchodzących w skład sumy.
     */
    private final ArrayList<Set> subSets;

    public SetUnion() {
        subSets = new ArrayList<>();
    }

    @Override
    public boolean belongs(int number) {
        for (Set s : subSets) {
            if (s.belongs(number))
                return true;
        }
        return false;
    }

    /**
     * Dodaje zbiór do sumy.
     *
     * @param set zbiór, który ma być dodany do sumy.
     */
    public void addSet(Set set) {
        subSets.add(set);
    }
}
