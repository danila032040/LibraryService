package tests.base.mediator.mocks;

import java.util.Optional;

import base.mediator.request.RequestHandler;

public class RequestHandlerMock implements RequestHandler<RequestMock0, Integer> {
    private int handleExecutionsCount;
    private Optional<RequestMock0> lastSpecifiedRequest;
    
    public int getHandleExecutionsCount() {
        return handleExecutionsCount;
    }
    
    public Optional<RequestMock0> getLastSpecifiedRequest() {
        return lastSpecifiedRequest;
    }
    
    @Override
    public Integer handle(RequestMock0 request) {
        lastSpecifiedRequest = Optional.of(request);
        return handleExecutionsCount++;
    }
}
