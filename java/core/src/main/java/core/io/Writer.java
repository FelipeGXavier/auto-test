package core.io;

import java.io.OutputStream;

public interface Writer {

    OutputStream getOutputStream();
    Writer append(String data);
    Writer append(byte[] data);
    Writer appendEscape(String data);

}
