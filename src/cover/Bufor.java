package cover;

import cover.sets.*;
import cover.solvers.CoverSolver;
import cover.solvers.ExactSolver;
import cover.solvers.GreedySolver;
import cover.solvers.NaiveSolver;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Wczytuje i interpretuje dane oraz wyświetla odpowiednie dla danych wartości.
 *
 * @author Mateusz Nowakowski
 */
public class Bufor {
    /**
     * Lista zbiorów, które pojawiły się na wejściu.
     * Zbiory te będą wykorzystywane do znajdowania pokrycia zapytań.
     */
    private final ArrayList<Set> listOfSets;

    private static final int NAIVE_ID = 3;
    private static final int GREEDY_ID = 2;
    private static final int EXACT_ID = 1;

    private static final int SINGLETON_ARGUMENTS_COUNT = 1;
    private static final int INFINITE_ARTHMETIC_ARGUMENTS_COUNT = 2;
    private static final int FINITE_ARTHMETIC_ARGUMENTS_COUNT = 3;

    public Bufor() {
        listOfSets = new ArrayList<>();
    }

    /**
     * Główna metoda wczytująca.
     * Wczytuje i interpretuje dane ze standardowego wejścia.
     */
    public void read() {
        Scanner s = new Scanner(System.in);
        while (s.hasNextInt()) {
            int number = s.nextInt();
            if (number > 0) {
                readSet(number, s);
            } else if (number < 0) {
                readQuery(number, s);
            } else {
                listOfSets.add(new EmptySet());
            }
        }
        s.close();
    }

    private CoverSolver getSolverByType(int type) {
        switch (type) {
            case NAIVE_ID:
                return new NaiveSolver();
            case GREEDY_ID:
                return new GreedySolver();
            case EXACT_ID:
                return new ExactSolver();
        }
        throw new IllegalArgumentException("Incorrect solver type " + type);
    }

    /**
     * Wczytuje zapytanie i wyświetla rozwiązanie.
     * Czyta składniki pewnego zbioru, aż nie wczyta zera.
     * Zakłada, że pierwsza liczba opisująca zapytanie jest już
     * wczytana i jest nią {@code number}.
     * Zakłada, że skaner {@code s} jest ustawiony na wczytanie pierwszej liczby która wystąpiła
     * na wejściu po {@code number}.
     * Wyświetla rozwiązanie zapytania.
     *
     * @param number liczba ujemna, pierwszy element zapytania
     * @param s      skaner skanujący wejście
     */
    private void readQuery(int number, Scanner s) {
        int n = s.nextInt();
        Query query = new Query(-number);
        Solution solution = getSolverByType(n).solve(listOfSets, query);
        System.out.println(solution);
    }

    /**
     * Wczytuje zbiór.
     * Czyta składniki pewnego zbioru, aż nie wczyta zera.
     * Zakłada, że pierwsza liczba opisująca pierwszy składnik zbioru jest już
     * wczytana i jest nią {@code number}.
     * Zakłada, że skaner {@code s} jest ustawiony na wczytanie pierwszej liczby która wystąpiła
     * na wejściu po {@code number}.
     * Tworzy zbiór zgodny z wczytanym opisem i dodaje go do globalnej listy zbiorów.
     *
     * @param number liczba dodatnia
     * @param s      skaner skanujący wejście
     */
    private void readSet(int number, Scanner s) {
        int[] setKeyNumbers = new int[3];
        setKeyNumbers[0] = number;
        int i = 1;
        SetUnion set = new SetUnion();
        boolean isZeroReached = false;
        while (!isZeroReached && s.hasNextInt()) {
            int n = s.nextInt();
            if (n > 0) {
                Set aS = setFromArray(setKeyNumbers, i);
                set.addSet(aS);
                setKeyNumbers[0] = n;
                i = 1;
            } else if (n < 0) {
                setKeyNumbers[i] = n;
                i++;
            } else {
                Set aS = setFromArray(setKeyNumbers, i);
                set.addSet(aS);
                isZeroReached = true;
            }
        }
        listOfSets.add(set);
    }

    /**
     * Tworzy i zwraca zbiór opisany jako składnik.
     * Tworzy prosty zbiór opisany w gramatyce jako składnik
     * (singleton, nieskończony ciąg arytmetyczny, skończony ciąg arytmetyczny)
     * na wejściu opisany pierwszymi {@code argCount} liczbami w tablicy {@code arr}.
     *
     * @param arr      tablica liczb opisujących składnik
     * @param argCount ilość liczb opisujących składnik
     * @return Obiekt reprezentujący składnik
     * ({@link Singleton}, {@link InfiniteArthmeticSet} lub {@link ArthmeticSet})
     */
    private Set setFromArray(int[] arr, int argCount) {
        switch (argCount) {
            case SINGLETON_ARGUMENTS_COUNT:
                return new Singleton(arr[0]);
            case INFINITE_ARTHMETIC_ARGUMENTS_COUNT:
                return new InfiniteArthmeticSet(arr[0], -arr[1]);
            case FINITE_ARTHMETIC_ARGUMENTS_COUNT:
                return new ArthmeticSet(arr[0], -arr[1], -arr[2]);
            default:
                return null;
        }
    }
}
