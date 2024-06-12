package tests.base.mediator.mocks;

import base.mediator.notification.NotificationHandler;

public class NotificationHandlerMock implements NotificationHandler<NotificationMock> {
    private int handleExecutionsCount;
    
    public int getHandleExecutionsCount() {
        return handleExecutionsCount;
    }
    
    @Override
    public void handle(NotificationMock notification) {
        handleExecutionsCount++;
    }
}
