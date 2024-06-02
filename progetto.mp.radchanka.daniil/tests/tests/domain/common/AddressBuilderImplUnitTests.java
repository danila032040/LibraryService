package tests.domain.common;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

import domain.common.AddressBuilder;
import domain.common.AddressBuilderImpl;

public class AddressBuilderImplUnitTests {

	@Test
	public void withBuilding_WhenBuildingIsNull_ShouldThrowNullPointerException() {
		AddressBuilderImpl addressBuilder = new AddressBuilderImpl();

		ThrowingCallable actual = () -> addressBuilder.withBuilding(null);

		assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
	}

	@Test
	public void withCity_WhenCityIsNull_ShouldThrowNullPointerException() {
		AddressBuilderImpl addressBuilder = new AddressBuilderImpl();

		ThrowingCallable actual = () -> addressBuilder.withCity(null);

		assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
	}

	@Test
	public void withCountryRegion_WhenCountryRegionIsNull_ShouldThrowNullPointerException() {
		AddressBuilderImpl addressBuilder = new AddressBuilderImpl();

		ThrowingCallable actual = () -> addressBuilder.withCountryRegion(null);

		assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
	}

	@Test
	public void withPostalCode_WhenPostalCodeIsNull_ShouldThrowNullPointerException() {
		AddressBuilderImpl addressBuilder = new AddressBuilderImpl();

		ThrowingCallable actual = () -> addressBuilder.withPostalCode(null);

		assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
	}

	@Test
	public void withStateProvince_WhenStateProvinceIsNull_ShouldThrowNullPointerException() {
		AddressBuilderImpl addressBuilder = new AddressBuilderImpl();

		ThrowingCallable actual = () -> addressBuilder.withStateProvince(null);

		assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
	}

	@Test
	public void build_ShouldNotThrowAnyException() {
		AddressBuilderImpl addressBuilder = new AddressBuilderImpl();

		ThrowingCallable actual = () -> addressBuilder.build();

		assertThatNoException().isThrownBy(actual);
	}

}
