package tests.base.mediator.unit;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import base.mediator.MediatorImpl;
import base.mediator.request.exceptions.RequestHandlerAlreadyRegisteredException;
import tests.base.mediator.mocks.NotificationDispatcherMock;
import tests.base.mediator.mocks.NotificationHandlerMock;
import tests.base.mediator.mocks.NotificationMock;
import tests.base.mediator.mocks.RequestDispatcherMock;
import tests.base.mediator.mocks.RequestHandlerMock;
import tests.base.mediator.mocks.RequestMock;

public class MediatorImplUnitTests {
    
    private NotificationDispatcherMock notificationDispatcher;
    private RequestDispatcherMock requestDispatcher;
    private MediatorImpl mediator;
    
    @Before
    public void setup() {
        notificationDispatcher = new NotificationDispatcherMock();
        requestDispatcher = new RequestDispatcherMock();
        mediator = new MediatorImpl(requestDispatcher, notificationDispatcher);
    }
    
    @Test
    public void registerHandler_WithNotificationHandler_ShouldUseRegisterHandlerOfNotificationDispatcherExactlyOnce() {
        mediator.registerHandler(NotificationMock.class, new NotificationHandlerMock());
        
        int actualExecutions = notificationDispatcher.getRegisterHandlerExecutionsCount();
        
        assertThat(actualExecutions).isEqualTo(1);
    }
    
    @Test
    public void sendNotification_ShouldUseSendNotificationOfNotificationDispatcherExactlyOnce() {
        mediator.sendNotification(new NotificationMock());
        
        int actualExecutions = notificationDispatcher.getSendNotificationExecutionsCount();
        
        assertThat(actualExecutions).isEqualTo(1);
    }
    
    @Test
    public void registerHandler_WithRequestHandler_ShouldUseRegisterHandlerOfRequestDispatcherExactlyOnce()
            throws RequestHandlerAlreadyRegisteredException {
        mediator.registerHandler(RequestMock.class, new RequestHandlerMock());
        
        int actualExecutions = requestDispatcher.getRegisterHandlerExecutionsCount();
        
        assertThat(actualExecutions).isEqualTo(1);
    }
    
    @Test
    public void sendRequest_ShouldUseSendRequestOfRequestDispatcherExactlyOnce() {
        mediator.sendRequest(new RequestMock());
        
        int actualExecutions = requestDispatcher.getSendRequestExecutionsCount();
        
        assertThat(actualExecutions).isEqualTo(1);
    }
}
