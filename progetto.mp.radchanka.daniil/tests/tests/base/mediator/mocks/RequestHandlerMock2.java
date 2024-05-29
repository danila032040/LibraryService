package tests.base.mediator.mocks;

import base.mediator.request.RequestHandler;

public class RequestHandlerMock2
		implements
			RequestHandler<RequestMock2, Integer> {
	private int handleExecutionsCount;

	public int getHandleExecutionsCount() {
		return handleExecutionsCount;
	}

	@Override
	public Integer handle(RequestMock2 request) {
		return handleExecutionsCount++;
	}
}
