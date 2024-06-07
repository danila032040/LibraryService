package tests.application.pipelineBehaviours.mocks;

import base.result.ValidationResult;
import base.utils.Validator;

public class ValidatorMock implements Validator<RequestMock> {
    private ValidationResult validationResult;
    
    public void setValidationResult(ValidationResult validationResult) {
        this.validationResult = validationResult;
    }
    
    @Override
    public ValidationResult validate(RequestMock request) {
        return validationResult;
    }
}
