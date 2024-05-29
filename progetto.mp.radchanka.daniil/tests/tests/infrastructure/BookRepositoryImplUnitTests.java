package tests.infrastructure;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.ArrayList;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

import domain.book.Book;
import infrastructure.book.repositories.BookRepositoryImpl;

public class BookRepositoryImplUnitTests {
	@Test
	public void createInstance_WhenEntityCloneFactoryFactoryIsNull_ShouldThrowNullPointerException() {
		ThrowingCallable actual = () -> new BookRepositoryImpl(
				new ArrayList<Book>(),
				ArrayList::new,
				null);

		assertThatExceptionOfType(NullPointerException.class)
				.isThrownBy(actual);
	}

	@Test
	public void createInstance_WhenResultCollectionFactoryIsNull_ShouldThrowNullPointerException() {
		ThrowingCallable actual = () -> new BookRepositoryImpl(
				new ArrayList<Book>(),
				null,
				Book::createClone);

		assertThatExceptionOfType(NullPointerException.class)
				.isThrownBy(actual);
	}

	@Test
	public void createInstance_WhenStorageIsNull_ShouldThrowNullPointerException() {
		ThrowingCallable actual = () -> new BookRepositoryImpl(
				null,
				ArrayList::new,
				Book::createClone);

		assertThatExceptionOfType(NullPointerException.class)
				.isThrownBy(actual);
	}

}
