package tests.domain.author.unitTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Collection;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

import base.ddd.DomainEvent;
import domain.author.Author;
import domain.author.AuthorId;
import domain.author.events.AuthorCreatedDomainEvent;

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
		Author author = Author.createNewAuthor(new AuthorId(1), "", "", "");

		Collection<DomainEvent> actual = author.extractAllDomainEvents();

		assertThat(actual)
				.satisfiesExactly(
						x -> assertThat(x)
								.isInstanceOfSatisfying(
										AuthorCreatedDomainEvent.class,
										domainEvent -> {
											assertThat(domainEvent.getAuthor())
													.isSameAs(author);
										}));
	}

	@Test
	public void createClone_ShouldReturnEqualButNotTheSameInstance() {
		Author author = Author.createNewAuthor(new AuthorId(1), "", "", "");

		Author actual = author.createClone();

		assertThat(actual).isNotSameAs(author).isEqualTo(author);
	}

	@Test
	public void setName_WhenNameIsNull_ShouldThrowNullPointerException() {
		Author author = Author.createNewAuthor(new AuthorId(1), "", "", "");

		ThrowingCallable actual = () -> author.setName(null);

		assertThatExceptionOfType(NullPointerException.class)
				.isThrownBy(actual);
	}
	
	@Test
	public void setSurname_WhenSurnameIsNull_ShouldThrowNullPointerException() {
		Author author = Author.createNewAuthor(new AuthorId(1), "", "", "");

		ThrowingCallable actual = () -> author.setSurname(null);

		assertThatExceptionOfType(NullPointerException.class)
				.isThrownBy(actual);
	}
}
