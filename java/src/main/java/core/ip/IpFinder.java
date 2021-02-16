package core.ip;

import core.contracts.Finder;
import core.network.NetworkClient;

public class IpFinder implements Finder<IP, IpResponse> {

    private final NetworkClient client;

    public IpFinder(NetworkClient client) {
        this.client = client;
    }

    @Override
    public IpResponse find(IP input) {
        return null;
    }
}
