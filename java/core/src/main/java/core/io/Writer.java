package core.io;

import java.io.IOException;
import java.io.OutputStream;

public interface Writer {

    OutputStream getOutputStream();
    Writer append(String data) throws IOException;
    Writer append(byte[] data) throws IOException;
    Writer appendEscape(String data);
    void finish() throws IOException;
    void flush() throws IOException;

}
