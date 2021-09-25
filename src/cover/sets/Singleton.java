package cover.sets;

/**
 * Reprezentuje zbiór zawierający jeden element.
 *
 * @author Mateusz Nowakowski
 */
public class Singleton implements Set {
    /**
     * Jedyny element w zbiorze.
     */
    private final int element;

    public Singleton(int element) {
        this.element = element;
    }

    @Override
    public boolean belongs(int number) {
        return this.element == number;
    }
}
