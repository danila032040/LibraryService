package application.validators.book;

import application.commands.book.register.RegisterBookCommand;
import base.result.ErrorResult;
import base.result.ValidationResult;
import base.utils.Validator;

public class RegisterBookCommandValidator implements Validator<RegisterBookCommand> {
    
    @Override
    public ValidationResult validate(RegisterBookCommand request) {
        return ValidationResult
                .create()
                .withErrorIf(
                        () -> request.getName() == null || request.getName().isBlank(),
                        ErrorResult.from("Name must be not whitespace and not empty"))
                .withErrorIf(
                        () -> request.getGenre() == null || request.getGenre().isBlank(),
                        ErrorResult.from("Genre must be not whitespace and not empty"))
                .withErrorIf(
                        () -> request.getPublicationYear() < 0,
                        ErrorResult.from("Publication year must be not negative"));
    }
    
}
