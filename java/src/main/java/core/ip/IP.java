package core.ip;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IP {

    private String address;

    private IP(String address) {
        if (!validAddress(address)) {
            throw new RuntimeException("Invalid address");
        }
        this.address = address;
    }

    public static IP of(String address) {
        return new IP(address);
    }

    public String getAddress() {
        return address;
    }

    public static boolean validAddress(String address) {
        if (address != null) {
            final String IPV4_PATTERN_SHORTEST =
                    "^((25[0-5]|(2[0-4]|1[0-9]|[1-9]|)[0-9])(\\.(?!$)|$)){4}$";
            Pattern pattern = Pattern.compile(IPV4_PATTERN_SHORTEST);
            Matcher matcher = pattern.matcher(address);
            if (matcher.matches()) {
                return true;
            }
        }
        return false;
    }
}
