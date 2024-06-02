package application.commands.library.changeAddress;

import java.util.Optional;

import base.mediator.request.Request;
import base.result.ErrorOr;
import base.result.Success;
import domain.library.LibraryId;

public class ChangeAddressCommand implements Request<ErrorOr<Success>> {
	private final int libraryId;
	private final Optional<String> building;
	private final Optional<String> city;
	private final Optional<String> countryRegion;
	private final Optional<String> postalCode;
	private final Optional<String> stateProvince;
	private final Optional<String> street;

	public ChangeAddressCommand(int libraryId, Optional<String> building,
			Optional<String> city, Optional<String> countryRegion,
			Optional<String> postalCode, Optional<String> stateProvince,
			Optional<String> street) {
		this.libraryId = libraryId;
		this.building = building;
		this.city = city;
		this.countryRegion = countryRegion;
		this.postalCode = postalCode;
		this.stateProvince = stateProvince;
		this.street = street;
	}

	public int getLibraryId() {
		return libraryId;
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
