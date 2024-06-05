package application.commands.library.register;

import application.commands.common.address.AddressCommandData;
import base.result.ValidationResult;
import base.utils.Validator;

public class RegisterLibraryCommandValidator implements Validator<RegisterLibraryCommand> {
    
    private final Validator<AddressCommandData> addressValidator;
    
    public RegisterLibraryCommandValidator(Validator<AddressCommandData> addressValidator) {
        
        this.addressValidator = addressValidator;
    }
    
    @Override
    public ValidationResult validate(RegisterLibraryCommand request) {
        ValidationResult addressValidationResult = addressValidator.validate(request.getAddress());
        return ValidationResult.create().unionWith(addressValidationResult);
        
    }
}
