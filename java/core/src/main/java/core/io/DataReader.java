package core.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DataReader implements Reader {

    @Override
    public String read(String path) throws IOException {
        var file = new File(path);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line);
            result.append("\n");
        }
        return result.toString();
    }

    @Override
    public String read(File file) {
        return null;
    }
}
