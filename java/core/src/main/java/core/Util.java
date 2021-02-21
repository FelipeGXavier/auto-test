package core;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class Util {

    public static String generateRandomFileName(String ext) {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase().concat("." + ext);
    }

    public static File createTempFile(String ext) throws IOException {
        var tmp = System.getProperty("java.io.tmpdir");
        var filename = generateRandomFileName(ext);
        var path = tmp + "/" + filename;
        var file = new File(path);
        if (file.createNewFile()) {
            return file;
        }
        return null;
    }

    public static boolean removeTempFile(String path) {
        return new File(path).delete();
    }
}
