package cover;

import cover.sets.Set;

import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Reprezentuje zbiór będący zapytaniem, zbiór którego pokrycie należy znaleźć.
 * Nie dziedziczy (powinna?) po klasie Set,
 * ponieważ nie ma potrzeby, by udostępniała metodę belongs.
 * W dalszych komentarzach obiekty klasy są nazywane 'zbiorami zapytania'.
 *
 * @author Mateusz Nowakowski
 */
public class Query {
    /**
     * Lista przechowująca elementy zbioru zapytania.
     */
    private final LinkedList<Integer> queryElements;

    /**
     * Tworzy zbiór zapytania zawierające liczby od 1 do {@code a}.
     *
     * @param a dodatnia liczba całkowita
     */
    public Query(int a) {
        queryElements = IntStream.rangeClosed(1, a)
                .boxed()
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Copy Constructor.
     * Tworzy kopię obiektu {@code query}.
     *
     * @param query obiekt do skopiowania
     */
    private Query(Query query) {
        queryElements = new LinkedList<>(query.queryElements);
    }

    /**
     * Usuwa wspólne elementy ze zbiorem.
     * Zwraca zbiór zapytania powstały przez usunięcie wspólnych elementów.
     * Funkcja nie dokonuje zmian na obiekcie wywołującym metodę.
     *
     * @param set zbiór, do którego przynależność będzie decydowała o usunięciu elementu
     * @return Zbiór zapytania powstały poprzez usunięcie
     * elementów wspólnych ze zbiorem {@code set}.
     */
    public Query removeSameElements(Set set) {
        Query queryCopy = new Query(this);

        queryCopy.queryElements.removeIf(set::belongs);
        return queryCopy;
    }

    /**
     * Zwraca liczbę elementów wspólnych z danym zbiorem.
     *
     * @param set zbiór, z którym liczona będzie liczba elementów wspólnych
     * @return Liczba elementów wspólnych.
     */
    public int countSameElements(Set set) {
        return (int) queryElements.stream().filter(set::belongs).count();
    }

    /**
     * Sprawdza, czy zbiór zapytania jest pusty.
     *
     * @return Wartość {@code true}, jeśli jest pusty,
     * {@code false} w przeciwnym przypadku.
     */
    public boolean isEmpty() {
        return queryElements.isEmpty();
    }
}
