package tests.application.commands.book.update.unitTests;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Optional;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

import application.commands.book.update.UpdateBookCommand;

public class UpdateBookCommandUnitTests {
    
    @Test
    public void updateBookCommand_WhenNameIsNull_ShouldThrowNullPointerException() {
        
        ThrowingCallable actual = () -> new UpdateBookCommand(8, null, "", 2003, Optional.empty(), Optional.empty());
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
        
    }
    
    @Test
    public void updateBookCommand_WhenGenreIsNull_ShouldThrowNullPointerException() {
        
        ThrowingCallable actual = () -> new UpdateBookCommand(8, "", null, 2003, Optional.empty(), Optional.empty());
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
        
    }
    
    @Test
    public void updateBookCommand_WhenauthorIdIsNull_ShouldThrowNullPointerException() {
        
        ThrowingCallable actual = () -> new UpdateBookCommand(8, "", "", 2003, null, Optional.empty());
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
        
    }
    
    @Test
    public void updateBookCommand_WhenlibraryIdIsNull_ShouldThrowNullPointerException() {
        
        ThrowingCallable actual = () -> new UpdateBookCommand(8, "", "", 2003, Optional.empty(), null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
        
    }
    
}
