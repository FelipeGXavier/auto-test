package core.ip;

public class IP {

    private String address;

    public IP(String address) {
        if (!validAddress(address)) {
            throw new RuntimeException("Invalid address");
        }
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public static boolean validAddress(String address) {
        return true;
    }
}
