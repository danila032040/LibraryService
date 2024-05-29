package tests.infrastructure;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.ArrayList;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

import domain.library.Library;
import infrastructure.library.repositories.LibraryRepositoryImpl;

public class LibraryRepositoryImplUnitTests {
	@Test
	public void createInstance_WhenEntityCloneFactoryFactoryIsNull_ShouldThrowNullPointerException() {
		ThrowingCallable actual = () -> new LibraryRepositoryImpl(
				new ArrayList<Library>(),
				ArrayList::new,
				null);

		assertThatExceptionOfType(NullPointerException.class)
				.isThrownBy(actual);
	}

	@Test
	public void createInstance_WhenResultCollectionFactoryIsNull_ShouldThrowNullPointerException() {
		ThrowingCallable actual = () -> new LibraryRepositoryImpl(
				new ArrayList<Library>(),
				null,
				Library::createClone);

		assertThatExceptionOfType(NullPointerException.class)
				.isThrownBy(actual);
	}

	@Test
	public void createInstance_WhenStorageIsNull_ShouldThrowNullPointerException() {
		ThrowingCallable actual = () -> new LibraryRepositoryImpl(
				null,
				ArrayList::new,
				Library::createClone);

		assertThatExceptionOfType(NullPointerException.class)
				.isThrownBy(actual);
	}

}
