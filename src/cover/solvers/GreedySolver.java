package cover.solvers;

import cover.Query;
import cover.sets.Set;
import cover.Solution;

import java.util.List;

/**
 * Implementacja heurystyki zachłannej rozwiązującej problem pokrycia zbioru.
 *
 * @author Mateusz Nowakowski
 */
public class GreedySolver implements CoverSolver {
    /**
     * Implementacja heurystyki zachłannej.
     * Znajduje rozwiązanie problemu pokrycia zbioru z zapytania {@code query} z użyciem
     * zbiorów znajdujących się w tablicy {@code sets} używając opisanej w poleceniu
     * zachłannej heurystyki.
     *
     * @param sets lista zbiorów używanych do pokrycia, optymalnie gdyby była to kolekcja
     *             z metodą @code get działającą w czasie O(1)
     * @param query obiekt reprezentujący zbiór, którego pokrycie próbujemy znaleźć
     * @return Obiekt reprezentujący rozwiązanie.
     */
    @Override
    public Solution solve(List<Set> sets, Query query) {
        Solution solution = new Solution();
        boolean hasFoundSet = true;
        while (hasFoundSet && !query.isEmpty()) {
            int indexOfMax = findIndexOfMaxSet(sets, query);
            if (indexOfMax == -1) {
                hasFoundSet = false;
            } else {
                Set maxSet = sets.get(indexOfMax);
                query = query.removeSameElements(maxSet);
                solution.add(indexOfMax + 1);
            }
        }
        if (query.isEmpty()) {
            solution.setAsExisting();
        }
        return solution;
    }

    /**
     * Znajduje zbiór mający najwięcej elementów wspólnych z danym zbiorem.
     * W tablicy {@code sets} znajduje zbiór mający najwięcej elementów wspólnych
     * ze zbiorem zapytania {@code query}.
     *
     * @param sets  tablica zbiorów
     * @param query zbiór zapytania
     * @return Indeks tego zbioru w tablicy, lub
     * {@code -1} gdy nie uda sie takiego znaleźć (żaden zbiór nie ma elementów wspólnych).
     * W przypadku kilku zbiorów mających największą liczbę elementów wspólnych, zwraca
     * najmniejszy z ich indeksów.
     */
    private static int findIndexOfMaxSet(List<Set> sets, Query query) {
        int maxCount = 0;
        int maxIndex = -1;
        for (int i = 0; i < sets.size(); i++) {
            int countIt = query.countSameElements(sets.get(i));
            if (countIt > maxCount) {
                maxCount = countIt;
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}
