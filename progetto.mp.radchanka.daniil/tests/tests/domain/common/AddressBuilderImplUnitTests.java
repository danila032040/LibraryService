package tests.domain.common;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

import domain.common.Address;
import domain.common.AddressBuilder;

public class AddressBuilderImplUnitTests {
    
    @Test
    public void withBuilding_WhenBuildingIsNull_ShouldThrowNullPointerException() {
        AddressBuilder addressBuilder = AddressBuilder.createBuilder();
        
        ThrowingCallable actual = () -> addressBuilder.withBuilding(null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void withCity_WhenCityIsNull_ShouldThrowNullPointerException() {
        AddressBuilder addressBuilder = AddressBuilder.createBuilder();
        
        ThrowingCallable actual = () -> addressBuilder.withCity(null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void withCountryRegion_WhenCountryRegionIsNull_ShouldThrowNullPointerException() {
        AddressBuilder addressBuilder = AddressBuilder.createBuilder();
        
        ThrowingCallable actual = () -> addressBuilder.withCountryRegion(null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void withPostalCode_WhenPostalCodeIsNull_ShouldThrowNullPointerException() {
        AddressBuilder addressBuilder = AddressBuilder.createBuilder();
        
        ThrowingCallable actual = () -> addressBuilder.withPostalCode(null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void withStateProvince_WhenStateProvinceIsNull_ShouldThrowNullPointerException() {
        AddressBuilder addressBuilder = AddressBuilder.createBuilder();
        
        ThrowingCallable actual = () -> addressBuilder.withStateProvince(null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void build_WhenNoFieldsProvided_ShouldNotThrowAnyException() {
        AddressBuilder addressBuilder = AddressBuilder.createBuilder();
        
        ThrowingCallable actual = () -> addressBuilder.build();
        
        assertThatNoException().isThrownBy(actual);
    }
    
    @Test
    public void build_WithBuilding_ShouldReturnAddressWithOnlyBuilding() {
        String building = "test";
        
        Address actualAddress = AddressBuilder.createBuilder().withBuilding(building).build();
        
        assertThat(actualAddress.getBuilding()).hasValue(building);
        assertThat(actualAddress.getCity()).isEmpty();
        assertThat(actualAddress.getCountryRegion()).isEmpty();
        assertThat(actualAddress.getPostalCode()).isEmpty();
        assertThat(actualAddress.getStateProvince()).isEmpty();
        assertThat(actualAddress.getStreet()).isEmpty();
    }
    
    @Test
    public void build_WithCity_ShouldReturnAddressWithOnlyCity() {
        String city = "test";
        
        Address actualAddress = AddressBuilder.createBuilder().withCity(city).build();
        
        assertThat(actualAddress.getBuilding()).isEmpty();
        assertThat(actualAddress.getCity()).hasValue(city);
        assertThat(actualAddress.getCountryRegion()).isEmpty();
        assertThat(actualAddress.getPostalCode()).isEmpty();
        assertThat(actualAddress.getStateProvince()).isEmpty();
        assertThat(actualAddress.getStreet()).isEmpty();
    }
    
    @Test
    public void build_WithCountryRegion_ShouldReturnAddressWithOnlyCountryRegion() {
        String countryRegion = "test";
        
        Address actualAddress = AddressBuilder.createBuilder().withCountryRegion(countryRegion).build();
        
        assertThat(actualAddress.getBuilding()).isEmpty();
        assertThat(actualAddress.getCity()).isEmpty();
        assertThat(actualAddress.getCountryRegion()).hasValue(countryRegion);
        assertThat(actualAddress.getPostalCode()).isEmpty();
        assertThat(actualAddress.getStateProvince()).isEmpty();
        assertThat(actualAddress.getStreet()).isEmpty();
    }
    
    @Test
    public void build_WithPostalCode_ShouldReturnAddressWithOnlyPostalCode() {
        String postalCode = "test";
        
        Address actualAddress = AddressBuilder.createBuilder().withPostalCode(postalCode).build();
        
        assertThat(actualAddress.getBuilding()).isEmpty();
        assertThat(actualAddress.getCity()).isEmpty();
        assertThat(actualAddress.getCountryRegion()).isEmpty();
        assertThat(actualAddress.getPostalCode()).hasValue(postalCode);
        assertThat(actualAddress.getStateProvince()).isEmpty();
        assertThat(actualAddress.getStreet()).isEmpty();
    }
    
    @Test
    public void build_WithStateProvince_ShouldReturnAddressWithOnlyStateProvince() {
        String stateProvince = "test";
        
        Address actualAddress = AddressBuilder.createBuilder().withStateProvince(stateProvince).build();
        
        assertThat(actualAddress.getBuilding()).isEmpty();
        assertThat(actualAddress.getCity()).isEmpty();
        assertThat(actualAddress.getCountryRegion()).isEmpty();
        assertThat(actualAddress.getPostalCode()).isEmpty();
        assertThat(actualAddress.getStateProvince()).hasValue(stateProvince);
        assertThat(actualAddress.getStreet()).isEmpty();
    }
    
    @Test
    public void build_WithStreet_ShouldReturnAddressWithOnlyStreet() {
        String street = "test";
        
        Address actualAddress = AddressBuilder.createBuilder().withStreet(street).build();
        
        assertThat(actualAddress.getBuilding()).isEmpty();
        assertThat(actualAddress.getCity()).isEmpty();
        assertThat(actualAddress.getCountryRegion()).isEmpty();
        assertThat(actualAddress.getPostalCode()).isEmpty();
        assertThat(actualAddress.getStateProvince()).isEmpty();
        assertThat(actualAddress.getStreet()).hasValue(street);
    }
}
