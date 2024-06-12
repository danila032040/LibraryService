package tests.base.mediator.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Before;
import org.junit.Test;

import base.mediator.request.RequestDispatcherImpl;
import base.mediator.request.exceptions.RequestHandlerAlreadyRegisteredException;
import base.mediator.request.exceptions.RequestHandlerNotFoundException;
import tests.base.mediator.mocks.RequestHandlerMock;
import tests.base.mediator.mocks.RequestHandlerMock2;
import tests.base.mediator.mocks.RequestMock0;
import tests.base.mediator.mocks.RequestMock2;

public class RequestDispatcherImplUnitTests {
    
    private RequestDispatcherImpl requestDispatcher;
    
    @Test
    public void registerHandler_Twice_ShouldThrowRequestHandlerAlreadyRegisteredException() {
        ThrowingCallable actual = () -> {
            requestDispatcher
                    .registerHandler(RequestMock0.class, new RequestHandlerMock())
                    .registerHandler(RequestMock0.class, new RequestHandlerMock());
        };
        
        assertThatExceptionOfType(RequestHandlerAlreadyRegisteredException.class)
                .isThrownBy(actual)
                .satisfies(exception -> assertThat(exception.getRequestType()).isEqualTo(RequestMock0.class));
    }
    
    @Test
    public void registerHandler_WhenRequestHandlerInstanceIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> requestDispatcher.registerHandler(RequestMock0.class, null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void registerHandler_WhenRequestTypeIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> requestDispatcher.registerHandler(null, new RequestHandlerMock());
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void sendRequest_ShouldDispatchRequestToTheCorrespondingRegisteredHandler()
            throws RequestHandlerAlreadyRegisteredException {
        RequestHandlerMock handler1 = new RequestHandlerMock();
        RequestHandlerMock2 handler2 = new RequestHandlerMock2();
        requestDispatcher.registerHandler(RequestMock0.class, handler1).registerHandler(RequestMock2.class, handler2);
        requestDispatcher.sendRequest(new RequestMock0());
        
        assertThat(handler1.getHandleExecutionsCount()).isEqualTo(1);
        assertThat(handler2.getHandleExecutionsCount()).isEqualTo(0);
    }
    
    @Test
    public void sendRequest_WhenNoCorrespondingHandlerWereRegistered_ShouldThrowRequestHandlerNotFoundException()
            throws RequestHandlerAlreadyRegisteredException {
        RequestHandlerMock handler = new RequestHandlerMock();
        requestDispatcher.registerHandler(RequestMock0.class, handler);
        
        ThrowingCallable actual = () -> requestDispatcher.sendRequest(new RequestMock2());
        
        assertThatExceptionOfType(RequestHandlerNotFoundException.class)
                .isThrownBy(actual)
                .satisfies(exception -> assertThat(exception.getRequestType()).isEqualTo(RequestMock2.class));
    }
    
    @Before
    public void setup() {
        requestDispatcher = new RequestDispatcherImpl();
    }
}