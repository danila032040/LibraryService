package tests.application.commands.user.register.unitTests;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Optional;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

import application.commands.common.address.AddressCommandData;
import application.commands.user.register.RegisterUserCommand;

public class RegisterUserCommandUnitTests {
    @Test
    public void constructor_WhenAddresCIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> new RegisterUserCommand(null, "", Optional.empty(), null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void constructor_WhenNameIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> new RegisterUserCommand(
                null,
                "",
                Optional.empty(),
                new AddressCommandData(
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty()));
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void constructor_WhenPhoneNumberIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> new RegisterUserCommand(
                "",
                "",
                null,
                new AddressCommandData(
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty()));
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void constructor_WhenSurnameIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> new RegisterUserCommand(
                "",
                null,
                Optional.empty(),
                new AddressCommandData(
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty()));
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
}
