package application.validators.book;

import application.commands.book.update.UpdateBookCommand;
import base.result.ErrorResult;
import base.result.ValidationResult;
import base.utils.Validator;

public class UpdateBookCommandValidator implements Validator<UpdateBookCommand> {
    
    @Override
    public ValidationResult validate(UpdateBookCommand request) {
        return ValidationResult
                .create()
                .withErrorIf(() -> request.getName().isBlank(), ErrorResult.from("Name must be not blank"))
                .withErrorIf(() -> request.getGenre().isBlank(), ErrorResult.from("Genre must be not blank"))
                .withErrorIf(
                        () -> request.getPublicationYear() < 0,
                        ErrorResult.from("Publication year must be not negative"));
    }
    
}
