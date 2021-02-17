package core.contracts;

import java.io.IOException;
import java.util.Optional;

public interface Finder<I, O> {

    Optional<O> find(I input) throws IOException, InterruptedException;
}
