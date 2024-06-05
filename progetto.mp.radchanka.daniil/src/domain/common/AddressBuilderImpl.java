package domain.common;

import java.util.Optional;

public class AddressBuilderImpl implements AddressBuilder {
    private Optional<String> building;
    private Optional<String> city;
    private Optional<String> countryRegion;
    private Optional<String> postalCode;
    private Optional<String> stateProvince;
    private Optional<String> street;
    
    public AddressBuilderImpl() {
        this.building = Optional.empty();
        this.city = Optional.empty();
        this.countryRegion = Optional.empty();
        this.postalCode = Optional.empty();
        this.stateProvince = Optional.empty();
        this.street = Optional.empty();
    }
    
    @Override
    public AddressBuilder withBuilding(String building) {
        this.building = Optional.of(building);
        return this;
    }
    
    @Override
    public AddressBuilder withCity(String city) {
        this.city = Optional.of(city);
        return this;
    }
    
    @Override
    public AddressBuilder withCountryRegion(String countryRegion) {
        this.countryRegion = Optional.of(countryRegion);
        return this;
    }
    
    @Override
    public AddressBuilder withPostalCode(String postalCode) {
        this.postalCode = Optional.of(postalCode);
        return this;
    }
    
    @Override
    public AddressBuilder withStateProvince(String stateProvince) {
        this.stateProvince = Optional.of(stateProvince);
        return this;
    }
    
    @Override
    public AddressBuilder withStreet(String street) {
        this.street = Optional.of(street);
        return this;
    }
    
    @Override
    public Address build() {
        return new Address(building, city, countryRegion, postalCode, stateProvince, street);
    }
    
}
