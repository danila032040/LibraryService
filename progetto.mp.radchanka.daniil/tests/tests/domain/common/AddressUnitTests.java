package tests.domain.common;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Optional;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

import domain.common.Address;

public class AddressUnitTests {
    @Test
    public void createClone_ShouldReturnEqualButNotTheSameInstance() {
        Optional<String> building = Optional.empty();
        Optional<String> city = Optional.empty();
        Optional<String> countryRegion = Optional.empty();
        Optional<String> postalCode = Optional.empty();
        Optional<String> stateProvince = Optional.empty();
        Optional<String> street = Optional.empty();
        Address address = new Address(building, city, countryRegion, postalCode, stateProvince, street);
        
        Address actual = address.createClone();
        
        assertThat(actual).isNotSameAs(address).isEqualTo(address);
    }
    
    @Test
    public void createInstance_WhenBuildingIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> new Address(
                null,
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty());
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void createInstance_WhenOptionalCityIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> new Address(
                Optional.empty(),
                null,
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty());
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void createInstance_WhenOptionalPostalCodeIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> new Address(
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                null,
                Optional.empty(),
                Optional.empty());
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void createInstance_WhenOptionalStateProvinceIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> new Address(
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                null,
                Optional.empty());
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void createInstance_WhenOptionalStreetIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> new Address(
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void createInstance_WhenOptionnalCountryRegionIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> new Address(
                Optional.empty(),
                Optional.empty(),
                null,
                Optional.empty(),
                Optional.empty(),
                Optional.empty());
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    

    @Test
    public void equals_ShouldReturnTrueForEqualAddresses() {
        Address address1 = new Address(
                Optional.of("Building 1"),
                Optional.of("City A"),
                Optional.of("Country X"),
                Optional.of("12345"),
                Optional.of("State Y"),
                Optional.of("Street 123"));

        Address address2 = new Address(
                Optional.of("Building 1"),
                Optional.of("City A"),
                Optional.of("Country X"),
                Optional.of("12345"),
                Optional.of("State Y"),
                Optional.of("Street 123"));

        assertThat(address1).isEqualTo(address2);
    }

    @Test
    public void equals_ShouldReturnFalseForDifferentAddresses() {
        Address address1 = new Address(
                Optional.of("Building 1"),
                Optional.of("City A"),
                Optional.of("Country X"),
                Optional.of("12345"),
                Optional.of("State Y"),
                Optional.of("Street 123"));

        Address address2 = new Address(
                Optional.of("Building 2"),
                Optional.of("City B"),
                Optional.of("Country Y"),
                Optional.of("67890"),
                Optional.of("State Z"),
                Optional.of("Street 456"));

        assertThat(address1).isNotEqualTo(address2);
    }

    @Test
    public void hashCode_ShouldBeEqualForEqualAddresses() {
        Address address1 = new Address(
                Optional.of("Building 1"),
                Optional.of("City A"),
                Optional.of("Country X"),
                Optional.of("12345"),
                Optional.of("State Y"),
                Optional.of("Street 123"));

        Address address2 = new Address(
                Optional.of("Building 1"),
                Optional.of("City A"),
                Optional.of("Country X"),
                Optional.of("12345"),
                Optional.of("State Y"),
                Optional.of("Street 123"));

        assertThat(address1.hashCode()).isEqualTo(address2.hashCode());
    }

    @Test
    public void hashCode_ShouldBeDifferentForDifferentAddresses() {
        Address address1 = new Address(
                Optional.of("Building 1"),
                Optional.of("City A"),
                Optional.of("Country X"),
                Optional.of("12345"),
                Optional.of("State Y"),
                Optional.of("Street 123"));

        Address address2 = new Address(
                Optional.of("Building 2"),
                Optional.of("City B"),
                Optional.of("Country Y"),
                Optional.of("67890"),
                Optional.of("State Z"),
                Optional.of("Street 456"));

        assertThat(address1.hashCode()).isNotEqualTo(address2.hashCode());
    }
}
