package tests.application.pipelineBehaviours.mocks;

import base.mediator.request.RequestHandler;
import base.result.ErrorOr;

public class RequestHandlerMock implements RequestHandler<RequestMock, ErrorOr<String>> {
    private final String result;
    private boolean called = false;
    
    public RequestHandlerMock(String result) {
        this.result = result;
    }
    
    @Override
    public ErrorOr<String> handle(RequestMock request) {
        called = true;
        return ErrorOr.fromResult(result);
    }
    
    public boolean isCalled() {
        return called;
    }
}