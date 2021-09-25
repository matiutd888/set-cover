package cover.sets;

/**
 * Reprezentuje zbiór zawierający ciąg artmetyczny
 * o wyrazach nieprzekraczającyh pewnej liczby.
 *
 * @author Mateusz Nowakowski
 */
public class ArthmeticSet implements Set {
    /**
     * Pierwszy wyraz ciągu arytmetycznego.
     */
    private final int min;

    /**
     * Liczba, od której każda liczba w zbiorze jest niewiększa.
     * Ograniczenie górne zbioru.
     */
    private final int max;

    /**
     * Różnica między kolejnymi wyrazami w ciągu.
     */
    private final int step;

    public ArthmeticSet(int min, int step, int max) {
        this.min = min;
        this.max = max;
        this.step = step;
    }

    @Override
    public boolean belongs(int number) {
        return number >= min && number <= max && (number - min) % step == 0;
    }
}