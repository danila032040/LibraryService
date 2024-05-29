package tests.domain.library.unitTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Collection;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

import base.ddd.DomainEvent;
import domain.common.Address;
import domain.library.Library;
import domain.library.LibraryId;
import domain.library.events.LibraryAddressChangedDomainEvent;
import domain.library.events.LibraryCreatedDomainEvent;

public class LibraryUnitTests {
	@Test
	public void changeAddress_ShouldAddLibraryAddressChangedDomainEvent() {
		Address expectedPreviousAddress = new Address();
		Address expectedCurrentAddress = new Address();
		Library expectedLibrary = Library
				.createNewLibrary(new LibraryId(1), expectedPreviousAddress);
		expectedLibrary.extractAllDomainEvents();
		expectedLibrary.changeAddress(expectedCurrentAddress);

		Collection<DomainEvent> actual = expectedLibrary
				.extractAllDomainEvents();

		assertThat(actual)
				.satisfiesExactly(
						x -> assertThat(x)
								.isInstanceOfSatisfying(
										LibraryAddressChangedDomainEvent.class,
										domainEvent -> {
											assertThat(domainEvent.getLibrary())
													.isSameAs(expectedLibrary);
											assertThat(
													domainEvent
															.getPreviousAddress())
													.isSameAs(
															expectedPreviousAddress);
											assertThat(
													domainEvent
															.getCurrentAddress())
													.isSameAs(
															expectedCurrentAddress);
										}));
	}

	@Test
	public void changeAddress_WhenAddressIsNull_ShouldThrowNullPointerException() {
		Library library = Library
				.createNewLibrary(new LibraryId(1), new Address());

		ThrowingCallable actual = () -> library.changeAddress(null);

		assertThatExceptionOfType(NullPointerException.class)
				.isThrownBy(actual);
	}

	@Test
	public void createClone_ShouldCreateEqualButNotTheSameInstance() {
		Library expected = Library
				.createNewLibrary(new LibraryId(1), new Address());

		Library actual = expected.createClone();

		assertThat(actual).isNotSameAs(expected).isEqualTo(expected);
	}

	@Test
	public void createNewLibrary_ShouldAddLibraryCreatedDomainEvent() {
		Library expected = Library
				.createNewLibrary(new LibraryId(1), new Address());

		Collection<DomainEvent> actual = expected.extractAllDomainEvents();

		assertThat(actual)
				.satisfiesExactly(
						x -> assertThat(x)
								.isInstanceOfSatisfying(
										LibraryCreatedDomainEvent.class,
										domainEvent -> {
											assertThat(domainEvent.getLibrary())
													.isSameAs(expected);
										}));
	}

	@Test
	public void createNewLibrary_WhenAddressIsNull_ShouldThrowNullPointerException() {
		ThrowingCallable actual = () -> Library
				.createNewLibrary(new LibraryId(1), null);

		assertThatExceptionOfType(NullPointerException.class)
				.isThrownBy(actual);
	}

	@Test
	public void createNewLibrary_WhenIdIsNull_ShouldThrowNullPointerException() {
		ThrowingCallable actual = () -> Library
				.createNewLibrary(null, new Address());

		assertThatExceptionOfType(NullPointerException.class)
				.isThrownBy(actual);
	}
}
