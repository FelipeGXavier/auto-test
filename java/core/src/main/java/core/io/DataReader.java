package core.io;

import java.io.*;

public class DataReader implements Reader {

    @Override
    public String read(String path) throws IOException {
        var file = new File(path);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        return result.toString();
    }

    @Override
    public String read(File file) {
        return null;
    }
}
