package cover.solvers;

import cover.Query;
import cover.sets.Set;
import cover.Solution;

import java.util.List;

/**
 * Udostępnia metody rozwiązujące problem pokrycia zbioru.
 * Metody robią to znajdując optymalne rozwiązanie lub używając heurystyk.
 *
 * @author Mateusz Nowakowski
 */
public interface CoverSolver {
    /**
     * Znajduje rozwiązanie problemu pokrycia zbioru zapytania {@code query} z użyciem
     * zbiorów znajdujących się w tablicy {@code sets} pewnej strategii.
     *
     * @param sets  tablica zbiorów używanych do pokrycia
     * @param query obiekt reprezentujący zbiór, którego pokrycie próbujemy znaleźć (zbiór zapytania)
     * @return Obiekt reprezentujący rozwiązanie.
     */
    Solution solve(List<Set> sets, Query query);
}
