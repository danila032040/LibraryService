package tests.domain.author.unitTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Collection;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

import base.ddd.DomainEvent;
import domain.author.Author;
import domain.author.AuthorId;
import domain.common.Address;

public class AuthorUnitTests {

	@Test
	public void createNewAuthor_WhenIdIsNull_ShouldThrowNullPointerException() {
		ThrowingCallable actual = () -> Author
				.createNewAuthor(null, "", "", "");

		assertThatExceptionOfType(NullPointerException.class)
				.isThrownBy(actual);
	}

	@Test
	public void createNewAuthor_WhenNameIsNull_ShouldThrowNullPointerException() {
		ThrowingCallable actual = () -> Author
				.createNewAuthor(new AuthorId(1), null, "", "");

		assertThatExceptionOfType(NullPointerException.class)
				.isThrownBy(actual);
	}

	@Test
	public void createNewAuthor_WhenSurnameIsNull_ShouldThrowNullPointerException() {
		ThrowingCallable actual = () -> Author
				.createNewAuthor(new AuthorId(1), "", null, "");

		assertThatExceptionOfType(NullPointerException.class)
				.isThrownBy(actual);
	}

	@Test
	public void createNewAuthor_WhenCountryIsNull_ShouldThrowNullPointerException() {
		ThrowingCallable actual = () -> Author
				.createNewAuthor(new AuthorId(1), "", "", null);

		assertThatExceptionOfType(NullPointerException.class)
				.isThrownBy(actual);
	}

	@Test
	public void createNewAuthor_ShouldAddAuthorCreatedDomainEvent() {
		Author expected = Author.createNewAuthor(new AuthorId(1), "", "", "");

		Collection<DomainEvent> actual = expected.extractAllDomainEvents();

		assertThat(actual)
				.satisfiesExactly(
						x -> assertThat(x)
								.isInstanceOfSatisfying(
										domain.author.events.AuthorCreatedDomainEvent.class,
										domainEvent -> {
											assertThat(
													domainEvent
															.getAuthor())
													.isSameAs(expected);
										}));
	}

	@Test
	public void createClone_ShouldCreateEqualButNotTheSameInstance() {
		Author expected = Author.createNewAuthor(new AuthorId(1), "", "", "");

		Author actual = expected.createClone();

		assertThat(actual).isNotSameAs(expected).isEqualTo(expected);
	}
}
