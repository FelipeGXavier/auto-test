package core.service;

import core.io.Reader;

import java.io.File;
import java.io.IOException;

public class ReadCsvService {

    private final Reader reader;

    public ReadCsvService(Reader reader) {
        this.reader = reader;
    }

    public String readBin(String filename) throws IOException {
        String path = new File("").getAbsolutePath() + "/bin/" + filename;
        return this.reader.read(path);
    }
}
