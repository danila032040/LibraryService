package domain.common;

public interface AddressBuilder {
	public static AddressBuilder createBuilder() {
		return new AddressBuilderImpl();
	}
	public AddressBuilder withBuilding(String building);
	public AddressBuilder withCity(String city);
	public AddressBuilder withCountryRegion(String countryRegion);
	public AddressBuilder withPostalCode(String postalCode);
	public AddressBuilder withStateProvince(String stateProvince);
	public AddressBuilder withStreet(String street);
	public Address build();
}
