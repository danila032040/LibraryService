package tests.application.pipelineBehaviours.mocks;

import base.mediator.request.RequestHandler;
import base.result.ErrorOr;

public class RequestHandlerMock implements RequestHandler<RequestMock, ErrorOr<String>> {
    private boolean called = false;
    
    public boolean isCalled() {
        return called;
    }
    
    @Override
    public ErrorOr<String> handle(RequestMock request) {
        called = true;
        return null;
    }
}