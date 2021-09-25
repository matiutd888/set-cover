package cover.solvers;

import cover.Query;
import cover.sets.Set;
import cover.Solution;

import java.util.List;

/**
 * Udostępnia metodę znajdującą optymalne rozwiązanie problemu pokrycia zbioru.
 *
 * @author Mateusz Nowakowski
 */
public class ExactSolver implements CoverSolver {
    /**
     * Znajduje optymalne rozwiązanie pokrycia zbioru z użyciem określonych zbiorów.
     * Znajduje najlepsze możliwe ("lepszośc" rozwiązań
     * roztrzygając na zasadzie podanej w poleceniu) rozwiązanie
     * pokrycia zbioru zapytania {@code q} dodając wybrane zbiory z
     * tablicy zbiorów {@code sets} o indeksach niemniejszych od {@code index} do
     * rozwiązania {@code curr}.
     *
     * @param sets  lista zbiorów, które możemy używać do pokrycia
     * @param index najmniejszy z pośród indeksów zbioru, które rozważamy
     * @param q zbiór zapytania, którego pokrycie próbujemy znaleźć
     * @param best  najlepsze, z dotychczas znalezionych rozwiązań
     * @param curr  obecnie rozważane rozwiązanie, zawiera indeksy zbiorów, które już użyliśmy w pokryciu
     * @return Obiekt {@code best}, jeśli znalezione najlepsze rozwiązanie
     * jest gorsze od {@code best}, lub nie istnieje. W przeciwnym przypadku obiekt
     * reprezentujący najlepsze znalezione rozwiązanie.
     */
    private static Solution brute(List<Set> sets, int index, Query q, Solution best, Solution curr) {
        if (q.isEmpty()) {
            curr.setAsExisting();
            if (curr.isBetter(best))
                best = curr;
            return best;
        }
        if (curr.size() >= best.size() && best.exists())
            return best;
        if (index > sets.size() - 1)
            return best;
        Set itSet = sets.get(index);
        if (q.countSameElements(itSet) > 0) {
            Query removedQuery = q.removeSameElements(itSet);
            Solution currentCopyWithSet = new Solution(curr);
            currentCopyWithSet.add(index + 1);
            best = brute(sets, index + 1, removedQuery, best, currentCopyWithSet);
        }
        return brute(sets, index + 1, q, best, curr);
    }

    /**
     * Znajduje optymalne rozwiązanie problemu pokrycia zbioru.
     *
     * @param sets list zbiorów używanych do pokrycia, optymalnie
     *             gdyby była tablicą (@code java.util.ArrayList) lub inną listą z metodą @code get
     *             działającą w czasie O(1)
     * @param query obiekt reprezentujący zbiór, którego pokrycie próbujemy znaleźć
     * @return Obiekt reprezentujący rozwiązanie.
     */
    @Override
    public Solution solve(List<Set> sets, Query query) {
        Solution bestFound = new Solution();
        Solution currentSolution = new Solution();
        return brute(sets, 0, query, bestFound, currentSolution);
    }
}
