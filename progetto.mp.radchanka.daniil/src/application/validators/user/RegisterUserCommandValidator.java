package application.validators.user;

import application.commands.common.address.AddressCommandData;
import application.commands.user.register.RegisterUserCommand;
import base.result.ErrorResult;
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
                .withErrorIf(() -> request.getName().isBlank(), ErrorResult.from("Name must be not blank"))
                .withErrorIf(() -> request.getSurname().isBlank(), ErrorResult.from("Surname must be not blank"))
                .withErrorIf(
                        () -> request.getPhoneNumber().map(String::isBlank).orElse(false),
                        ErrorResult.from("Phone number must be not whitespace if provided"));
        
    }
}
