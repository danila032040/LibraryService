package tests.application.validators.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;

import application.commands.common.address.AddressCommandData;
import application.commands.user.update.UpdateUserCommand;
import application.validators.user.UpdateUserCommandValidator;
import base.result.ErrorResult;
import base.result.ValidationResult;

public class UpdateUserCommandValidatorUnitTests {
    
    @Test
    public void validate_WhenNewPhoneNumberAndAddressAreEmpty_ShouldBeNotValidWithErrors() {
        UpdateUserCommandValidator validator = new UpdateUserCommandValidator(address -> ValidationResult.create());
        UpdateUserCommand command = new UpdateUserCommand(0, Optional.empty(), Optional.empty());
        
        ValidationResult validationResult = validator.validate(command);
        
        assertThat(validationResult.isValid()).isFalse();
        assertThat(validationResult.getErrors()).hasSize(1);
        assertThat(validationResult.getErrors().get(0).getMessage())
                .isEqualTo("At least new phone number or new address must be present");
    }
    
    @Test
    public void validate_WhenNewPhoneNumberAndAddressArePresentAndAddressIsInvalid_ShouldBeNotValidWithErrors() {
        UpdateUserCommandValidator validator = new UpdateUserCommandValidator(
                address -> ValidationResult.create().withError(ErrorResult.from("Address is invalid")));
        
        UpdateUserCommand command = new UpdateUserCommand(
                0,
                Optional
                        .of(
                                new AddressCommandData(
                                        Optional.empty(),
                                        Optional.empty(),
                                        Optional.empty(),
                                        Optional.empty(),
                                        Optional.empty(),
                                        Optional.empty())),
                Optional.of("Test"));
        
        ValidationResult validationResult = validator.validate(command);
        
        assertThat(validationResult.isValid()).isFalse();
        assertThat(validationResult.getErrors()).hasSize(1);
        assertThat(validationResult.getErrors().get(0).getMessage()).isEqualTo("Address is invalid");
    }
    
    @Test
    public void validate_WhenNewPhoneNumberIsBlank_ShouldBeNotValidWithErrors() {
        UpdateUserCommandValidator validator = new UpdateUserCommandValidator(address -> ValidationResult.create());
        UpdateUserCommand command = new UpdateUserCommand(0, Optional.empty(), Optional.of(""));
        
        ValidationResult validationResult = validator.validate(command);
        
        assertThat(validationResult.isValid()).isFalse();
        assertThat(validationResult.getErrors()).hasSize(1);
        assertThat(validationResult.getErrors().get(0).getMessage())
                .isEqualTo("Phone number must be not whitespace if provided");
    }
    
    @Test
    public void validate_WhenNewPhoneNumberIsPresentAndAddressIsValid_ShouldBeValid() {
        UpdateUserCommandValidator validator = new UpdateUserCommandValidator(address -> ValidationResult.create());
        UpdateUserCommand command = new UpdateUserCommand(
                0,
                Optional
                        .of(
                                new AddressCommandData(
                                        Optional.empty(),
                                        Optional.empty(),
                                        Optional.empty(),
                                        Optional.empty(),
                                        Optional.empty(),
                                        Optional.empty())),
                Optional.of("Test"));
        
        ValidationResult validationResult = validator.validate(command);
        
        assertThat(validationResult.isValid()).isTrue();
        assertThat(validationResult.getErrors()).isEmpty();
    }
}
