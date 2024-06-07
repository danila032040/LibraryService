package tests.application.validators.common;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;

import application.commands.common.address.AddressCommandData;
import application.validators.common.AddressCommandDataValidator;
import base.result.ValidationResult;

public class AddressCommandDataValidatorUnitTests {
    
    @Test
    public void validate_WhenNoFieldsArePresent_ShouldBeNotValidWithErrors() {
        AddressCommandData address = new AddressCommandData(
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty());
        AddressCommandDataValidator validator = new AddressCommandDataValidator();
        
        ValidationResult validationResult = validator.validate(address);
        
        assertThat(validationResult.isValid()).isFalse();
        assertThat(validationResult.getErrors()).hasSize(1);
        assertThat(validationResult.getErrors().get(0).getErrorMessage())
                .isEqualTo("At least one field of address must be present");
    }
    
    @Test
    public void validate_WhenBuildingIsPresent_ShouldBeValid() {
        AddressCommandData address = new AddressCommandData(
                Optional.of("Test"),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty());
        AddressCommandDataValidator validator = new AddressCommandDataValidator();
        
        ValidationResult validationResult = validator.validate(address);
        
        assertThat(validationResult.isValid()).isTrue();
        assertThat(validationResult.getErrors()).isEmpty();
    }
    
    @Test
    public void validate_WhenCityIsPresent_ShouldBeValid() {
        AddressCommandData address = new AddressCommandData(
                Optional.empty(),
                Optional.of("Test"),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty());
        AddressCommandDataValidator validator = new AddressCommandDataValidator();
        
        ValidationResult validationResult = validator.validate(address);
        
        assertThat(validationResult.isValid()).isTrue();
        assertThat(validationResult.getErrors()).isEmpty();
    }
    
    @Test
    public void validate_WhenCountryRegionIsPresent_ShouldBeValid() {
        AddressCommandData address = new AddressCommandData(
                Optional.empty(),
                Optional.empty(),
                Optional.of("Test"),
                Optional.empty(),
                Optional.empty(),
                Optional.empty());
        AddressCommandDataValidator validator = new AddressCommandDataValidator();
        
        ValidationResult validationResult = validator.validate(address);
        
        assertThat(validationResult.isValid()).isTrue();
        assertThat(validationResult.getErrors()).isEmpty();
    }
    
    @Test
    public void validate_WhenPostalCodeIsPresent_ShouldBeValid() {
        AddressCommandData address = new AddressCommandData(
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.of("Test"),
                Optional.empty(),
                Optional.empty());
        AddressCommandDataValidator validator = new AddressCommandDataValidator();
        
        ValidationResult validationResult = validator.validate(address);
        
        assertThat(validationResult.isValid()).isTrue();
        assertThat(validationResult.getErrors()).isEmpty();
    }
    
    @Test
    public void validate_WhenStateProvinceIsPresent_ShouldBeValid() {
        AddressCommandData address = new AddressCommandData(
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.of("Test"),
                Optional.empty());
        AddressCommandDataValidator validator = new AddressCommandDataValidator();
        
        ValidationResult validationResult = validator.validate(address);
        
        assertThat(validationResult.isValid()).isTrue();
        assertThat(validationResult.getErrors()).isEmpty();
    }
    
    @Test
    public void validate_WhenStreetIsPresent_ShouldBeValid() {
        AddressCommandData address = new AddressCommandData(
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.of("Test"));
        AddressCommandDataValidator validator = new AddressCommandDataValidator();
        
        ValidationResult validationResult = validator.validate(address);
        
        assertThat(validationResult.isValid()).isTrue();
        assertThat(validationResult.getErrors()).isEmpty();
    }
}
