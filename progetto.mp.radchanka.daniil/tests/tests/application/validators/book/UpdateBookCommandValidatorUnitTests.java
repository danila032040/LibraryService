package tests.application.validators.book;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;

import application.commands.book.update.UpdateBookCommand;
import application.validators.book.UpdateBookCommandValidator;
import base.result.ValidationResult;

public class UpdateBookCommandValidatorUnitTests {
    
    @Test
    public void validate_WhenAllFieldsAreValid_ShouldBeValid() {
        UpdateBookCommand command = new UpdateBookCommand(0, "Test", "Test", 0, Optional.empty(), Optional.empty());
        UpdateBookCommandValidator validator = new UpdateBookCommandValidator();
        
        ValidationResult validationResult = validator.validate(command);
        
        assertThat(validationResult.isValid()).isTrue();
        assertThat(validationResult.getErrors()).isEmpty();
    }
    
    @Test
    public void validate_WhenGenreIsBlank_ShouldBeNotValidWithErrors() {
        UpdateBookCommand command = new UpdateBookCommand(0, "Test", " ", 0, Optional.empty(), Optional.empty());
        UpdateBookCommandValidator validator = new UpdateBookCommandValidator();
        
        ValidationResult validationResult = validator.validate(command);
        
        assertThat(validationResult.isValid()).isFalse();
        assertThat(validationResult.getErrors()).hasSize(1);
        assertThat(validationResult.getErrors().get(0).getMessage()).isEqualTo("Genre must be not blank");
    }
    
    @Test
    public void validate_WhenNameIsBlank_ShouldBeNotValidWithErrors() {
        UpdateBookCommand command = new UpdateBookCommand(0, " ", "Test", 0, Optional.empty(), Optional.empty());
        UpdateBookCommandValidator validator = new UpdateBookCommandValidator();
        
        ValidationResult validationResult = validator.validate(command);
        
        assertThat(validationResult.isValid()).isFalse();
        assertThat(validationResult.getErrors()).hasSize(1);
        assertThat(validationResult.getErrors().get(0).getMessage()).isEqualTo("Name must be not blank");
    }
    
    @Test
    public void validate_WhenPublicationYearIsNegative_ShouldBeNotValidWithErrors() {
        UpdateBookCommand command = new UpdateBookCommand(0, "Test", "Test", -1, Optional.empty(), Optional.empty());
        UpdateBookCommandValidator validator = new UpdateBookCommandValidator();
        
        ValidationResult validationResult = validator.validate(command);
        
        assertThat(validationResult.isValid()).isFalse();
        assertThat(validationResult.getErrors()).hasSize(1);
        assertThat(validationResult.getErrors().get(0).getMessage()).isEqualTo("Publication year must be not negative");
    }
}
