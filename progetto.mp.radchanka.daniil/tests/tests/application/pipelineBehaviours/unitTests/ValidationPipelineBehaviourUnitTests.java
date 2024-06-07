package tests.application.pipelineBehaviours.unitTests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import application.pipelineBehaviours.ValidationPipelineBehaviour;
import base.result.ErrorOr;
import base.result.ErrorResult;
import base.result.ValidationResult;
import tests.application.pipelineBehaviours.mocks.RequestHandlerMock;
import tests.application.pipelineBehaviours.mocks.RequestMock;
import tests.application.pipelineBehaviours.mocks.ValidatorMock;

public class ValidationPipelineBehaviourUnitTests {
    
    @Test
    public void handle_WhenValidationPasses_ShouldCallNextHandler() {
        ValidatorMock validator = new ValidatorMock();
        RequestHandlerMock nextHandler = new RequestHandlerMock();
        ValidationPipelineBehaviour<RequestMock, String> validationPipelineBehaviour = new ValidationPipelineBehaviour<>(
                validator);
        validator.setValidationResult(ValidationResult.create());
        
        validationPipelineBehaviour.handle(new RequestMock(), nextHandler);
        
        assertThat(nextHandler.isCalled()).isTrue();
    }
    
    @Test
    public void handle_WhenValidationFails_ShouldReturnErrorResult() {
        ValidatorMock validator = new ValidatorMock();
        RequestHandlerMock nextHandler = new RequestHandlerMock();
        ValidationPipelineBehaviour<RequestMock, String> validationPipelineBehaviour = new ValidationPipelineBehaviour<>(
                validator);
        validator
                .setValidationResult(
                        ValidationResult
                                .create()
                                .withError(ErrorResult.from("Test1"))
                                .withError(ErrorResult.from("Test2")));
        
        ErrorOr<String> result = validationPipelineBehaviour.handle(new RequestMock(), nextHandler);
        
        // Assert
        assertThat(result.isError()).isTrue();
        assertThat(result.getError().map(ErrorResult::getErrorMessage)).hasValue("Test1; Test2");
    }
}
