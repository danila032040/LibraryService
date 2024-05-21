package tests.infrastructure;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.ArrayList;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

import domain.author.Author;
import infrastructure.author.repositories.AuthorRepositoryImpl;

public class AuthorRepositoryImplUnitTests {
	@Test
	public void createInstance_WhenStorageIsNull_ShouldThrowNullPointerException() {
		ThrowingCallable actual = () -> new AuthorRepositoryImpl(
				null,
				ArrayList::new,
				Author::createClone);

		assertThatExceptionOfType(NullPointerException.class)
				.isThrownBy(actual);
	}

	@Test
	public void createInstance_WhenResultCollectionFactoryIsNull_ShouldThrowNullPointerException() {
		ThrowingCallable actual = () -> new AuthorRepositoryImpl(
				new ArrayList<Author>(),
				null,
				Author::createClone);

		assertThatExceptionOfType(NullPointerException.class)
				.isThrownBy(actual);
	}

	@Test
	public void createInstance_WhenEntityCloneFactoryFactoryIsNull_ShouldThrowNullPointerException() {
		ThrowingCallable actual = () -> new AuthorRepositoryImpl(
				new ArrayList<Author>(),
				ArrayList::new,
				null);

		assertThatExceptionOfType(NullPointerException.class)
				.isThrownBy(actual);
	}
}
