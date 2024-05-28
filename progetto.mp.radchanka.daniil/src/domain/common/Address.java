package domain.common;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import base.ddd.ValueObject;
import base.cloneable.Cloneable;

public class Address extends ValueObject implements Cloneable<Address> {
	private Optional<String> building;
	private Optional<String> city;
	private Optional<String> countryRegion;
	private Optional<String> postalCode;
	private Optional<String> stateProvince;
	private Optional<String> street;

	public Address() {
		this(Optional.empty(), Optional.empty(), Optional.empty(),
				Optional.empty(), Optional.empty(), Optional.empty());
	}

	public Address(Optional<String> building, Optional<String> city,
			Optional<String> countryRegion, Optional<String> postalCode,
			Optional<String> stateProvince, Optional<String> street) {
		this.building = Objects.requireNonNull(building);
		this.city = Objects.requireNonNull(city);
		this.countryRegion = Objects.requireNonNull(countryRegion);
		this.postalCode = Objects.requireNonNull(postalCode);
		this.stateProvince = Objects.requireNonNull(stateProvince);
		this.street = Objects.requireNonNull(street);
	}
	public Optional<String> getBuilding() {
		return building;
	}
	public Optional<String> getCity() {
		return city;
	}
	public Optional<String> getCountyRegion() {
		return countryRegion;
	}
	@Override
	public Iterator<Object> getEqualityComponentsIterator() {
		return List
				.<Object>of(
						street,
						building,
						city,
						countryRegion,
						stateProvince,
						postalCode)
				.iterator();
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
	@Override
	public Address createClone() {
		return new Address(
				building,
				city,
				countryRegion,
				postalCode,
				stateProvince,
				street);
	}
}
