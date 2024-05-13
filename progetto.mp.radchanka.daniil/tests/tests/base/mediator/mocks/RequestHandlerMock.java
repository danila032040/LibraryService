package tests.base.mediator.mocks;

import base.mediator.RequestHandler;

public class RequestHandlerMock
		implements
			RequestHandler<RequestMock, Integer> {
	private int handleExecutionsCount;

	public int getHandleExecutionsCount() {
		return handleExecutionsCount;
	}

	@Override
	public Integer handle(RequestMock request) {
		return handleExecutionsCount++;
	}
}
