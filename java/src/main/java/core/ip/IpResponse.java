package core.ip;

import java.util.Objects;

public class IpResponse {

    private String city;
    private String continent;
    private String country;
    private String latitude;
    private String longitude;

    protected IpResponse(
            String city, String continent, String country, String latitude, String longitude) {
        this.city = city;
        this.continent = continent;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IpResponse that = (IpResponse) o;
        return city.equals(that.city)
                && continent.equals(that.continent)
                && country.equals(that.country)
                && latitude.equals(that.latitude)
                && longitude.equals(that.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, continent, country, latitude, longitude);
    }

    public static class Builder {

        private String city;
        private String continent;
        private String country;
        private String latitude;
        private String longitude;

        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        public Builder setContinent(String continent) {
            this.continent = continent;
            return this;
        }

        public Builder setCountry(String country) {
            this.country = country;
            return this;
        }

        public Builder setLatitude(String latitude) {
            this.latitude = latitude;
            return this;
        }

        public Builder setLongitude(String longitude) {
            this.longitude = longitude;
            return this;
        }

        public IpResponse build() {
            return new IpResponse(city, continent, country, latitude, longitude);
        }
    }
}
