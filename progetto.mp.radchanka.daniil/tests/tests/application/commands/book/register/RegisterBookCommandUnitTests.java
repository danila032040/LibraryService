package tests.application.commands.book.register;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Optional;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

import application.commands.book.register.RegisterBookCommand;

public class RegisterBookCommandUnitTests {

	@Test
	public void registerBookCommand_WhenNameIsNull_ShouldThrowNullPointerException() {

		ThrowingCallable actual = () -> new RegisterBookCommand(null, "", 8, Optional.empty(), Optional.empty());

		assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);

	}
	
	@Test
	public void registerBookCommand_WhenGenreIsNull_ShouldThrowNullPointerException() {

		ThrowingCallable actual = () -> new RegisterBookCommand("", null, 8, Optional.empty(), Optional.empty());

		assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);

	}
	
	@Test
	public void registerBookCommand_WhenAuthorIdIsNull_ShouldThrowNullPointerException() {

		ThrowingCallable actual = () -> new RegisterBookCommand("", "", 8, null, Optional.empty());

		assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);

	}
	
	@Test
	public void registerBookCommand_WhenLibraryIdIsNull_ShouldThrowNullPointerException() {

		ThrowingCallable actual = () -> new RegisterBookCommand("", "", 8, Optional.empty(), null);

		assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);

	}

}
