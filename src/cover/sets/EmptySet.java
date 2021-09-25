package cover.sets;

/**
 * Reprezentuje zbiór pusty.
 *
 * @author Mateusz Nowakowski
 */
public class EmptySet implements Set {
    @Override
    public boolean belongs(int number) {
        return false;
    }
}
