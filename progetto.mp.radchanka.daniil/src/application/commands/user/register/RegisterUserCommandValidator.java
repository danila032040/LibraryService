package application.commands.user.register;

import application.commands.common.address.AddressCommandData;
import base.result.Error;
import base.result.ValidationResult;
import base.utils.Validator;

public class RegisterUserCommandValidator implements Validator<RegisterUserCommand> {
    
    private final Validator<AddressCommandData> addressValidator;
    
    public RegisterUserCommandValidator(Validator<AddressCommandData> addressValidator) {
        
        this.addressValidator = addressValidator;
    }
    
    @Override
    public ValidationResult validate(RegisterUserCommand request) {
        ValidationResult addressValidationResult = addressValidator.validate(request.getAddress());
        return ValidationResult
                .create()
                .unionWith(addressValidationResult)
                .withErrorIf(
                        () -> request.getName() == null || request.getName().isBlank(),
                        Error.from("Name must be not whitespace and not empty"))
                .withErrorIf(
                        () -> request.getSurname() == null || request.getSurname().isBlank(),
                        Error.from("Surname must be not whitespace and not empty"))
                .withErrorIf(
                        () -> request.getPhoneNumber().map(String::isBlank).orElse(false),
                        Error.from("Phone number must be not whitespace if provided"));
        
    }
}
