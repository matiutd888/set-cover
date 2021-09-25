package cover.solvers;

import cover.Query;
import cover.sets.Set;
import cover.Solution;

import java.util.List;

/**
 * Implementacja heurystyki naiwnej rozwiązującej problem pokrycia zbioru..
 *
 * @author Mateusz Nowakowski
 */
public class NaiveSolver implements CoverSolver {
    /**
     * Rozwiązuje problem pokrycia zbioru z użyciem opisanej w poleceniu herystyki naiwnej.
     *
     * @param sets  lista zbiorów używanych do pokrycia, optymalnie gdyby metoda @code get działała w O(1)
     * @param query obiekt reprezentujący zbiór, którego pokrycie próbujemy znaleźć (zbiór zapytania)
     * @return Obiekt reprezentujący rozwiązanie.
     */
    @Override
    public Solution solve(List<Set> sets, Query query) {
        Solution solution = new Solution();
        for (int i = 0; i < sets.size(); i++) {
            if (query.countSameElements(sets.get(i)) > 0) {
                solution.add(i + 1);
                query = query.removeSameElements(sets.get(i));
            }
        }
        if (query.isEmpty()) {
            solution.setAsExisting();
        }
        return solution;
    }
}
