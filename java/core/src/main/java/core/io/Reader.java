package core.io;

import java.io.File;
import java.io.IOException;

public interface Reader {

    String read(String path) throws IOException;

    String read(File file);
}
