package cover;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Reprezentuje rozwiązanie problemu pokrycia zbioru.
 * Przechowuje indeksy zbiorów, których użyjemy do pokrycia.
 *
 * @author Mateusz Nowakowski
 */
public class Solution {
    /**
     * Wartość napisowa wyświetlana w przypadku, gdy nie istnieje rozwiązanie.
     */
    private static final String SOLUTION_NOT_EXIST_STRING = "0";

    /**
     * Lista przechowująca indeksy zbiorów w rozwiązaniu.
     */
    private final ArrayList<Integer> indexes;

    /**
     * Przyjmuje wartość {@code true}, jeśli rozwiązanie istnieje,
     * {@code false} w przeciwym przypadku.
     */
    private boolean exists;

    /**
     * Tworzy nowe (jeszcze nie zawieraje żadnych elementów)
     * rozwiązanie.
     * Domyślnie rozwiązanie jest złe (nie istnieje).
     */
    public Solution() {
        exists = false;
        indexes = new ArrayList<>();
    }

    /**
     * Copy Constructor.
     * Tworzy obiekt będący kopią rozwiązania podanego jako argument.
     *
     * @param solution rozwiązanie, którego kopia ma zostać stworzona.
     */
    public Solution(Solution solution) {
        exists = solution.exists;
        indexes = new ArrayList<>(solution.indexes);
    }

    /**
     * Dodaje zbiór o indeksie {@code index} do rozwiązania.
     *
     * @param index indeks zbioru
     */
    public void add(int index) {
        indexes.add(index);
    }

    /**
     * Zwraca liczbę zbiorów w rozwiązaniu.
     *
     * @return Liczba zbiorów w rozwiązaniu.
     */
    public int size() {
        return indexes.size();
    }

    /**
     * Sprawdza, czy rozwiązanie jest istniejące.
     *
     * @return Wartość {@code true}, jeśli rozwiązanie jest ustawione jako
     * istniejące, wartość {@code false} w przeciwnym przypadku.
     */
    public boolean exists() {
        return exists;
    }

    /**
     * Ustawia rozwiązanie na istniejące.
     * Zmienia wartość {@link exists} na {@code true}.
     */
    public void setAsExisting() {
        exists = true;
    }

    /**
     * Zwraca reprezentację napisową rozwiązania.
     * Reprezentacja ta jest zgodna z założeniami zadania,
     * gdy rozwiązanie nie istnieje zwraca {@link SOLUTION_NOT_EXIST_STRING},
     * w przeciwnym przypadku wypisuje indeksy posortowane rosnąco.
     *
     * @return Wartość napisowa rozwiązania.
     */
    @Override
    public String toString() {
        if (!exists) {
            return SOLUTION_NOT_EXIST_STRING;
        }
        Collections.sort(indexes);
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < indexes.size() - 1; i++) {
            stringBuilder.append(indexes.get(i)).append(" ");
        }
        stringBuilder.append(indexes.get(indexes.size() - 1));
        return stringBuilder.toString();
    }

    /**
     * Porównuje rozwiązania.
     * Zwraca {@code true}, jeśli rozwiązanie wywołujące metodę jest 'lepsze'
     * od {@code solution2}, to znaczy: rozwiązanie {@code solution2}
     * nie istnieje, lub jego rozmiar (ilość zbiorów w rozwiązaniu)
     * jest większa. Zwraca wartość {@code false} w przeciwnym przypadku.
     *
     * @param solution2 rozwiązanie do porównania
     * @return Wartość {@code true} jeśli rozwiązanie jest lepsze od
     * {@code solution2}, wartość {@code false} w przeciwnym przypadku.
     */
    public boolean isBetter(Solution solution2) {
        if (!this.exists()) {
            return false;
        }
        if (!solution2.exists()) {
            return true;
        }
        return this.size() < solution2.size();
    }
}
