package cover.sets;

/**
 * Reprezentuje zbiór zawierający ciąg arytmetyczny
 * nieograniczony z góry.
 *
 * @author Mateusz Nowakowski
 */
public class InfiniteArthmeticSet extends ArthmeticSet {
    public InfiniteArthmeticSet(int min, int step) {
        super(min, step, Integer.MAX_VALUE);
    }
}
