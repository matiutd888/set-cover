package cover.sets;

/**
 * Reprezentuje zbiór.
 * Zbiór udostępnia metodę sprawdzającą, czy dana liczba jest w zbiorze.
 *
 * @author Mateusz Nowakowski
 */
public interface Set {
    /**
     * Sprawdza, czy liczba jest w zbiorze.
     *
     * @param number liczba, której obecność w zbiorze jest sprawdzana
     * @return Wartość {@code true}, jeśli liczba należy do zbioru,
     * {@code false} w przeciwnym przypadku.
     */
    boolean belongs(int number);
}
