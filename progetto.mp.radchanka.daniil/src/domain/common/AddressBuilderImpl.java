package domain.common;

import java.util.Optional;

public class AddressBuilderImpl implements AddressBuilder {
	private Optional<String> building;
	private Optional<String> city;
	private Optional<String> countryRegion;
	private Optional<String> postalCode;
	private Optional<String> stateProvince;
	private Optional<String> street;

	@Override
	public AddressBuilder withBuilding(String building) {
		this.building = Optional.of(building);
		return this;
	}

	@Override
	public AddressBuilder withCity(String city) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AddressBuilder withCountryRegion(String countryRegion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AddressBuilder withPostalCode(String postalCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AddressBuilder withStateProvince(String stateProvince) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AddressBuilder withStreet(String street) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Address build() {
		return new Address(
				building,
				city,
				countryRegion,
				postalCode,
				stateProvince,
				street);
	}

}
