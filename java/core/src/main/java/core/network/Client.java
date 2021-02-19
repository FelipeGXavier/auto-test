package core.network;

import java.io.IOException;

public interface Client {

    String get(String url) throws IOException, InterruptedException;
    int status();
}
