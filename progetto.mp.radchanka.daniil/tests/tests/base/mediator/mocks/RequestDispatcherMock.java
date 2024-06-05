package tests.base.mediator.mocks;

import base.mediator.request.Request;
import base.mediator.request.RequestDispatcher;
import base.mediator.request.RequestHandler;
import base.mediator.request.exceptions.RequestHandlerAlreadyRegisteredException;

public class RequestDispatcherMock implements RequestDispatcher {
    private int registerHandlerExecutionsCount;
    private int sendRequestExecutionsCount;
    
    @Override
    public <TRequest extends Request<TResult>, TResult> RequestDispatcher registerHandler(
            Class<TRequest> requestType,
            RequestHandler<TRequest, TResult> handler)
            throws RequestHandlerAlreadyRegisteredException {
        ++registerHandlerExecutionsCount;
        return this;
    }
    
    @Override
    public <TRequest extends Request<TResult>, TResult> TResult sendRequest(TRequest request) {
        ++sendRequestExecutionsCount;
        return null;
    }
    
    public int getRegisterHandlerExecutionsCount() {
        return registerHandlerExecutionsCount;
    }
    
    public int getSendRequestExecutionsCount() {
        return sendRequestExecutionsCount;
    }
}
