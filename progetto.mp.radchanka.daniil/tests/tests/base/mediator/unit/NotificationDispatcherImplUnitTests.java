package tests.base.mediator.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Before;
import org.junit.Test;

import base.mediator.notification.NotificationDispatcherImpl;
import base.mediator.notification.exceptions.NotificationHandlerNotFoundException;
import tests.base.mediator.mocks.NotificationHandlerMock;
import tests.base.mediator.mocks.NotificationHandlerMock2;
import tests.base.mediator.mocks.NotificationMock;
import tests.base.mediator.mocks.NotificationMock2;

public class NotificationDispatcherImplUnitTests {

	private NotificationDispatcherImpl notificationDispatcher;

	@Before
	public void setup() {
		notificationDispatcher = new NotificationDispatcherImpl();
	}

	@Test
	public void registerHandler_WhenNotificationTypeIsNull_ShouldThrowNullPointerException() {
		ThrowingCallable actual = () -> notificationDispatcher
				.registerHandler(null, new NotificationHandlerMock());

		assertThatExceptionOfType(NullPointerException.class)
				.isThrownBy(actual);
	}

	@Test
	public void registerHandler_WhenNotificationHandlerInstanceIsNull_ShouldThrowNullPointerException() {
		ThrowingCallable actual = () -> notificationDispatcher
				.registerHandler(NotificationMock.class, null);

		assertThatExceptionOfType(NullPointerException.class)
				.isThrownBy(actual);
	}

	@Test
	public void registerHandler_Twice_ShouldNotThrowAnyException() {
		ThrowingCallable actual = () -> {
			notificationDispatcher
					.registerHandler(
							NotificationMock.class,
							new NotificationHandlerMock())
					.registerHandler(
							NotificationMock.class,
							new NotificationHandlerMock());
		};

		assertThatNoException().isThrownBy(actual);
	}

	@Test
	public void sendNotification_ShouldDispatchNotificationToAllRegisteredHandlersExactlyOncePerHandler() {
		NotificationHandlerMock handler1 = new NotificationHandlerMock();
		NotificationHandlerMock handler2 = new NotificationHandlerMock();
		notificationDispatcher
				.registerHandler(NotificationMock.class, handler1)
				.registerHandler(NotificationMock.class, handler2);
		notificationDispatcher.sendNotification(new NotificationMock());

		assertThat(handler1.getHandleExecutionsCount()).isEqualTo(1);
		assertThat(handler2.getHandleExecutionsCount()).isEqualTo(1);
	}

	@Test
	public void sendNotification_ShouldDispatchNotificationToTheCorrespondingRegisteredHandler() {
		NotificationHandlerMock handler1 = new NotificationHandlerMock();
		NotificationHandlerMock2 handler2 = new NotificationHandlerMock2();
		notificationDispatcher
				.registerHandler(NotificationMock.class, handler1)
				.registerHandler(NotificationMock2.class, handler2);
		notificationDispatcher.sendNotification(new NotificationMock());

		assertThat(handler1.getHandleExecutionsCount()).isEqualTo(1);
		assertThat(handler2.getHandleExecutionsCount()).isEqualTo(0);
	}

	@Test
	public void sendNotification_WhenNoCorrespondingHandlerWereRegistered_ShouldThrowNotificationHandlerNotFoundException() {
		NotificationHandlerMock handler = new NotificationHandlerMock();
		notificationDispatcher.registerHandler(NotificationMock.class, handler);

		ThrowingCallable actual = () -> notificationDispatcher
				.sendNotification(new NotificationMock2());

		assertThatExceptionOfType(NotificationHandlerNotFoundException.class)
				.isThrownBy(actual)
				.satisfies(
						exception -> assertThat(exception.getNotificationType())
								.isEqualTo(NotificationMock2.class));
	}
}
