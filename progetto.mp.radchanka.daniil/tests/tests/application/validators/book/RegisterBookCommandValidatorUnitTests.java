package tests.application.validators.book;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;

import application.commands.book.register.RegisterBookCommand;
import application.validators.book.RegisterBookCommandValidator;
import base.result.ValidationResult;

public class RegisterBookCommandValidatorUnitTests {
    
    @Test
    public void validate_WhenNameIsBlank_ShouldBeNotValidWithErrors() {
        RegisterBookCommand command = new RegisterBookCommand(" ", "Test", 0, Optional.empty(), Optional.empty());
        RegisterBookCommandValidator validator = new RegisterBookCommandValidator();
        
        ValidationResult validationResult = validator.validate(command);
        
        assertThat(validationResult.isValid()).isFalse();
        assertThat(validationResult.getErrors()).hasSize(1);
        assertThat(validationResult.getErrors().get(0).getErrorMessage()).isEqualTo("Name must be not blank");
    }
    
    @Test
    public void validate_WhenGenreIsBlank_ShouldBeNotValidWithErrors() {
        RegisterBookCommand command = new RegisterBookCommand("Test", " ", 0, Optional.empty(), Optional.empty());
        RegisterBookCommandValidator validator = new RegisterBookCommandValidator();
        
        ValidationResult validationResult = validator.validate(command);
        
        assertThat(validationResult.isValid()).isFalse();
        assertThat(validationResult.getErrors()).hasSize(1);
        assertThat(validationResult.getErrors().get(0).getErrorMessage()).isEqualTo("Genre must be not blank");
    }
    
    @Test
    public void validate_WhenPublicationYearIsNegative_ShouldBeNotValidWithErrors() {
        RegisterBookCommand command = new RegisterBookCommand("Test", "Test", -1, Optional.empty(), Optional.empty());
        RegisterBookCommandValidator validator = new RegisterBookCommandValidator();
        
        ValidationResult validationResult = validator.validate(command);
        
        assertThat(validationResult.isValid()).isFalse();
        assertThat(validationResult.getErrors()).hasSize(1);
        assertThat(validationResult.getErrors().get(0).getErrorMessage())
                .isEqualTo("Publication year must be not negative");
    }
    
    @Test
    public void validate_WhenAllFieldsAreValid_ShouldBeValid() {
        RegisterBookCommand command = new RegisterBookCommand("Test", "Test", 0, Optional.empty(), Optional.empty());
        RegisterBookCommandValidator validator = new RegisterBookCommandValidator();
        
        ValidationResult validationResult = validator.validate(command);
        
        assertThat(validationResult.isValid()).isTrue();
        assertThat(validationResult.getErrors()).isEmpty();
    }
}
