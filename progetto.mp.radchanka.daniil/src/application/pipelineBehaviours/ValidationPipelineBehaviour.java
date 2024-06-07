package application.pipelineBehaviours;

import java.util.stream.Collectors;

import base.mediator.pipeline.PipelineBehaviour;
import base.mediator.request.Request;
import base.mediator.request.RequestHandler;
import base.result.ErrorOr;
import base.result.ErrorResult;
import base.result.ValidationResult;
import base.utils.Validator;

public class ValidationPipelineBehaviour<TRequest extends Request<ErrorOr<TResult>>, TResult>
        implements
        PipelineBehaviour<TRequest, ErrorOr<TResult>> {
    
    private final Validator<TRequest> requestValidator;
    
    public ValidationPipelineBehaviour(Validator<TRequest> requestValidator) {
        this.requestValidator = requestValidator;
    }
    
    @Override
    public ErrorOr<TResult> handle(TRequest request, RequestHandler<TRequest, ErrorOr<TResult>> next) {
        ValidationResult validationResult = requestValidator.validate(request);
        
        if (!validationResult.isValid())
            return ErrorOr
                    .fromErrorMessage(
                            validationResult
                                    .getErrors()
                                    .stream()
                                    .map(ErrorResult::getErrorMessage)
                                    .collect(Collectors.joining("; ")));
        
        return next.handle(request);
    }
}
