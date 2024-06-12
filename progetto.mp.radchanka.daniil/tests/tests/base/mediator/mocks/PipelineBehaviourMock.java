package tests.base.mediator.mocks;

import java.util.Optional;
import java.util.function.Function;

import base.mediator.pipeline.PipelineBehaviour;
import base.mediator.request.RequestHandler;

public class PipelineBehaviourMock implements PipelineBehaviour<RequestMock0, Integer> {
    private final Function<RequestMock0, RequestMock0> requstMockTransformationFunction;
    private Optional<RequestMock0> lastSpecifiedRequest;
    private Optional<RequestHandler<RequestMock0, Integer>> lastSpecifiedNext;
    
    public PipelineBehaviourMock(Function<RequestMock0, RequestMock0> requstMockTransformationFunction) {
        this.requstMockTransformationFunction = requstMockTransformationFunction;
    }
    
    public Optional<RequestHandler<RequestMock0, Integer>> getLastSpecifiedNext() {
        return lastSpecifiedNext;
    }
    
    public Optional<RequestMock0> getLastSpecifiedRequest() {
        return lastSpecifiedRequest;
    }
    
    @Override
    public Integer handle(RequestMock0 request, RequestHandler<RequestMock0, Integer> next) {
        lastSpecifiedRequest = Optional.of(request);
        lastSpecifiedNext = Optional.of(next);
        return next.handle(requstMockTransformationFunction.apply(request));
    }
}
