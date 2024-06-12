package domain.common;

import java.util.Optional;

public class AddressBuilder {
    public static AddressBuilder createBuilder() {
        return new AddressBuilder();
    }
    private Optional<String> building;
    private Optional<String> city;
    private Optional<String> countryRegion;
    private Optional<String> postalCode;
    private Optional<String> stateProvince;
    
    private Optional<String> street;
    
    private AddressBuilder() {
        this.building = Optional.empty();
        this.city = Optional.empty();
        this.countryRegion = Optional.empty();
        this.postalCode = Optional.empty();
        this.stateProvince = Optional.empty();
        this.street = Optional.empty();
    }
    
    public Address build() {
        return new Address(building, city, countryRegion, postalCode, stateProvince, street);
    }
    
    public AddressBuilder withBuilding(String building) {
        this.building = Optional.of(building);
        return this;
    }
    
    public AddressBuilder withCity(String city) {
        this.city = Optional.of(city);
        return this;
    }
    
    public AddressBuilder withCountryRegion(String countryRegion) {
        this.countryRegion = Optional.of(countryRegion);
        return this;
    }
    
    public AddressBuilder withPostalCode(String postalCode) {
        this.postalCode = Optional.of(postalCode);
        return this;
    }
    
    public AddressBuilder withStateProvince(String stateProvince) {
        this.stateProvince = Optional.of(stateProvince);
        return this;
    }
    
    public AddressBuilder withStreet(String street) {
        this.street = Optional.of(street);
        return this;
    }
    
}
