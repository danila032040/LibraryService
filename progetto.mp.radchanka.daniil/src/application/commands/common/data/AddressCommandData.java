package application.commands.common.data;

import java.util.Optional;

public class AddressCommandData {
	private final Optional<String> building;
	private final Optional<String> city;
	private final Optional<String> countryRegion;
	private final Optional<String> postalCode;
	private final Optional<String> stateProvince;
	private final Optional<String> street;

	public AddressCommandData(Optional<String> building, Optional<String> city,
			Optional<String> countryRegion, Optional<String> postalCode,
			Optional<String> stateProvince, Optional<String> street) {
		this.building = building;
		this.city = city;
		this.countryRegion = countryRegion;
		this.postalCode = postalCode;
		this.stateProvince = stateProvince;
		this.street = street;
	}

	public Optional<String> getBuilding() {
		return building;
	}
	public Optional<String> getCity() {
		return city;
	}
	public Optional<String> getCountryRegion() {
		return countryRegion;
	}
	public Optional<String> getPostalCode() {
		return postalCode;
	}
	public Optional<String> getStateProvince() {
		return stateProvince;
	}
	public Optional<String> getStreet() {
		return street;
	}
}
