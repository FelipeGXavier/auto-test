package core.contracts;

public interface Finder<I, O> {

    O find(I input);
}
