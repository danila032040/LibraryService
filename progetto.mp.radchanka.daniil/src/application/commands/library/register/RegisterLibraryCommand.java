package application.commands.library.register;

import java.util.Optional;

import base.mediator.request.Request;
import base.result.ErrorOr;
import domain.library.LibraryId;

public class RegisterLibraryCommand implements Request<ErrorOr<LibraryId>> {
	private final Optional<String> building;
	private final Optional<String> city;
	private final Optional<String> countryRegion;
	private final Optional<String> postalCode;
	private final Optional<String> stateProvince;
	private final Optional<String> street;

	public RegisterLibraryCommand(Optional<String> building,
			Optional<String> city, Optional<String> countryRegion,
			Optional<String> postalCode, Optional<String> stateProvince,
			Optional<String> street) {
		super();
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
