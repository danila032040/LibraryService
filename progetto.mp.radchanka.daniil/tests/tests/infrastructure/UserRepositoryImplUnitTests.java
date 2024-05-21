package tests.infrastructure;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.ArrayList;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

import domain.user.User;
import infrastructure.user.repositories.UserRepositoryImpl;

public class UserRepositoryImplUnitTests {
	@Test
	public void createInstance_WhenStorageIsNull_ShouldThrowNullPointerException() {
		ThrowingCallable actual = () -> new UserRepositoryImpl(
				null,
				ArrayList::new,
				User::createClone);

		assertThatExceptionOfType(NullPointerException.class)
				.isThrownBy(actual);
	}

	@Test
	public void createInstance_WhenResultCollectionFactoryIsNull_ShouldThrowNullPointerException() {
		ThrowingCallable actual = () -> new UserRepositoryImpl(
				new ArrayList<User>(),
				null,
				User::createClone);

		assertThatExceptionOfType(NullPointerException.class)
				.isThrownBy(actual);
	}

	@Test
	public void createInstance_WhenEntityCloneFactoryFactoryIsNull_ShouldThrowNullPointerException() {
		ThrowingCallable actual = () -> new UserRepositoryImpl(
				new ArrayList<User>(),
				ArrayList::new,
				null);

		assertThatExceptionOfType(NullPointerException.class)
				.isThrownBy(actual);
	}

}
