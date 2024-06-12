package tests.base.mediator.mocks;

import base.mediator.notification.NotificationHandler;

public class NotificationHandlerMock2 implements NotificationHandler<NotificationMock2> {
    private int handleExecutionsCount;
    
    public int getHandleExecutionsCount() {
        return handleExecutionsCount;
    }
    
    @Override
    public void handle(NotificationMock2 notification) {
        handleExecutionsCount++;
    }
}
