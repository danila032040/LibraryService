package tests.application.validators.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;

import application.commands.common.address.AddressCommandData;
import application.commands.user.register.RegisterUserCommand;
import application.validators.user.RegisterUserCommandValidator;
import base.result.ErrorResult;
import base.result.ValidationResult;

public class RegisterUserCommandValidatorUnitTests {
    
    @Test
    public void validate_WhenAddressIsNotValid_ShouldBeNotValidWithErrors() {
        RegisterUserCommandValidator validator = new RegisterUserCommandValidator(
                address -> ValidationResult.create().withError(ErrorResult.from("Address is not valid")));
        RegisterUserCommand command = new RegisterUserCommand(
                "Test",
                "Test",
                Optional.empty(),
                new AddressCommandData(
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty()));
        
        ValidationResult validationResult = validator.validate(command);
        
        assertThat(validationResult.isValid()).isFalse();
        assertThat(validationResult.getErrors()).hasSize(1);
        assertThat(validationResult.getErrors().get(0).getMessage()).isEqualTo("Address is not valid");
    }
    
    @Test
    public void validate_WhenNameIsBlank_ShouldBeNotValidWithErrors() {
        RegisterUserCommandValidator validator = new RegisterUserCommandValidator(address -> ValidationResult.create());
        RegisterUserCommand command = new RegisterUserCommand(
                "",
                "Test",
                Optional.empty(),
                new AddressCommandData(
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty()));
        
        ValidationResult validationResult = validator.validate(command);
        
        assertThat(validationResult.isValid()).isFalse();
        assertThat(validationResult.getErrors()).hasSize(1);
        assertThat(validationResult.getErrors().get(0).getMessage()).isEqualTo("Name must be not blank");
    }
    
    @Test
    public void validate_WhenSurnameIsBlank_ShouldBeNotValidWithErrors() {
        RegisterUserCommandValidator validator = new RegisterUserCommandValidator(address -> ValidationResult.create());
        RegisterUserCommand command = new RegisterUserCommand(
                "Test",
                "",
                Optional.empty(),
                new AddressCommandData(
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty()));
        
        ValidationResult validationResult = validator.validate(command);
        
        assertThat(validationResult.isValid()).isFalse();
        assertThat(validationResult.getErrors()).hasSize(1);
        assertThat(validationResult.getErrors().get(0).getMessage()).isEqualTo("Surname must be not blank");
    }
    
    @Test
    public void validate_WhenPhoneNumberIsBlank_ShouldBeNotValidWithErrors() {
        RegisterUserCommandValidator validator = new RegisterUserCommandValidator(address -> ValidationResult.create());
        RegisterUserCommand command = new RegisterUserCommand(
                "Test",
                "Test",
                Optional.of("  "),
                new AddressCommandData(
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty()));
        
        ValidationResult validationResult = validator.validate(command);
        
        assertThat(validationResult.isValid()).isFalse();
        assertThat(validationResult.getErrors()).hasSize(1);
        assertThat(validationResult.getErrors().get(0).getMessage())
                .isEqualTo("Phone number must be not whitespace if provided");
    }
    
    @Test
    public void validate_WhenAllFieldsAreValid_ShouldBeValid() {
        RegisterUserCommandValidator validator = new RegisterUserCommandValidator(address -> ValidationResult.create());
        RegisterUserCommand command = new RegisterUserCommand(
                "Test",
                "Test",
                Optional.empty(),
                new AddressCommandData(
                        Optional.of("Test"),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty()));
        
        ValidationResult validationResult = validator.validate(command);
        
        assertThat(validationResult.isValid()).isTrue();
        assertThat(validationResult.getErrors()).isEmpty();
    }
}
