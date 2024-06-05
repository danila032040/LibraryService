package application.commands.book.register;

import base.result.Error;
import base.result.ValidationResult;
import base.utils.Validator;

public class RegisterBookCommandValidator implements Validator<RegisterBookCommand> {
    
    @Override
    public ValidationResult validate(RegisterBookCommand request) {
        return ValidationResult
                .create()
                .withErrorIf(
                        () -> request.getName() == null || request.getName().isBlank(),
                        Error.from("Name must be not whitespace and not empty"))
                .withErrorIf(
                        () -> request.getGenre() == null || request.getGenre().isBlank(),
                        Error.from("Genre must be not whitespace and not empty"))
                .withErrorIf(
                        () -> request.getPublicationYear() < 0,
                        Error.from("Publication year must be not negative"));
    }
    
}
