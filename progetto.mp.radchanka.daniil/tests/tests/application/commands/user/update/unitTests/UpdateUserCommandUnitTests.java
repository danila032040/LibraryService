package tests.application.commands.user.update.unitTests;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Optional;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

import application.commands.user.update.UpdateUserCommand;

public class UpdateUserCommandUnitTests {
    @Test
    public void constructor_WhenNewAddressIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> new UpdateUserCommand(0, null, Optional.empty());
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void constructor_WhenNewPhoneNumberIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> new UpdateUserCommand(0, Optional.empty(), null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
}
