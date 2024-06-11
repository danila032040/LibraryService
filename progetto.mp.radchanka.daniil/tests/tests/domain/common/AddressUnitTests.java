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
}
