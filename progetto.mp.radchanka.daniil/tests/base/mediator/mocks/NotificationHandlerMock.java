package base.mediator.mocks;

import base.mediator.NotificationHandler;

public class NotificationHandlerMock
		implements
			NotificationHandler<NotificationMock> {
	private int handleExecutionsCount;

	@Override
	public void handle(NotificationMock notification) {
		handleExecutionsCount++;
	}

	public int getHandleExecutionsCount() {
		return handleExecutionsCount;
	}
}
