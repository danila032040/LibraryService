package tests.base.mediator.mocks;

import base.mediator.Request;
import base.mediator.RequestDispatcher;
import base.mediator.RequestHandler;
import base.mediator.RequestHandlerAlreadyRegisteredException;

public class RequestDispatcherMock implements RequestDispatcher{
	private int registerHandlerExecutionsCount;
	private int sendRequestExecutionsCount;
	
	@Override
	public <TRequest extends Request<TResult>, TResult> RequestDispatcher registerHandler(Class<TRequest> requestType,
			RequestHandler<TRequest, TResult> handler) throws RequestHandlerAlreadyRegisteredException {
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
