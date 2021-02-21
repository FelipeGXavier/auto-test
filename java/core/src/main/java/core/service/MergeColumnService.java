package core.service;

import java.util.HashMap;
import java.util.Map;

public class MergeColumnService {

    public Map<String, String> mergeColumn(String data) {
        Map<String, String> values = new HashMap<>();
        var lines = data.split("\n");
        if (this.lengthGte(lines, 0)) {
            for (int i = 1; i < lines.length; i++) {
                var columns = lines[i].split(";");
                if (this.lengthEqualsTo(columns, 2)) {
                    var key = columns[0];
                    var value = columns[1];
                    if (!key.isEmpty()) {
                        values.putIfAbsent(key, value);
                    }
                }
            }
        }
        return values;
    }

    private boolean lengthEqualsTo(String[] element, int size) {
        if (element != null) {
            return element.length == size;
        }
        return false;
    }

    private boolean lengthGte(String[] element, int size) {
        if (element != null) {
            return element.length > size;
        }
        return false;
    }
}
