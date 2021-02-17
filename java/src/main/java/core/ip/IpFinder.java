package core.ip;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.contracts.Client;
import core.contracts.Finder;
import core.network.NetworkClient;

import java.io.IOException;
import java.util.Optional;

public class IpFinder implements Finder<IP, IpResponse> {

    private final Client client;
    private final String URL = "http://demo.ip-api.com/json/%s?fields=66842623&lang=en";

    public IpFinder(Client client) {
        this.client = client;
    }

    @Override
    public Optional<IpResponse> find(IP input) throws IOException, InterruptedException {
        var address = input.getAddress();
        var response = this.client.get(String.format(URL, address));
        if (this.validateResponse(response)) {
            var mapper = new ObjectMapper();
            var json = mapper.readTree(response);
            var result =
                    new IpResponse.Builder()
                            .setCity(json.get("city").asText())
                            .setContinent(json.get("continent").asText())
                            .setCountry(json.get("country").asText())
                            .setLatitude(json.get("lat").asText())
                            .setLongitude(json.get("lon").asText())
                            .build();
            return Optional.of(result);
        }
        return Optional.empty();
    }

    private boolean validateResponse(String response) {
        if (client.status() == 200) {
            var mapper = new ObjectMapper();
            try {
                var json = mapper.readTree(response);
                if (json.has("city")
                        && json.has("continent")
                        && json.has("country")
                        && json.has("lat")
                        && json.has("lon")) {
                    return true;
                }
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }
}
