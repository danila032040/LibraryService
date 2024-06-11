package tests.base.log.unitTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.Locale;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

import base.log.LogEntry;
import base.log.LogLevelType;
import base.log.PrintStreamLogEntryPublisher;

public class PrintStreamLogEntryPublisherUnitTests {
    @Test
    public void createInstance_WhenLocaleSupplierIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> new PrintStreamLogEntryPublisher(
                null,
                () -> "",
                new PrintStream(OutputStream.nullOutputStream()));
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void createInstance_WhenPrintStreamIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> new PrintStreamLogEntryPublisher(() -> Locale.US, () -> "", null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void createInstance_WhenTimeStampFormatSupplierIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> new PrintStreamLogEntryPublisher(
                () -> Locale.US,
                null,
                new PrintStream(OutputStream.nullOutputStream()));
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void publishLogEntry_ShouldPrintLineOfLogEntryOnPrintStreamCorrectly() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        PrintStreamLogEntryPublisher logEntryPublisher = new PrintStreamLogEntryPublisher(
                () -> Locale.US,
                () -> "dd-MM-YYY HH:mm:ss",
                printStream);
        LogEntry logEntry = new LogEntry(
                LogLevelType.Information,
                LocalDateTime.of(2024, 01, 01, 1, 0, 0),
                "origMessage",
                new Object[] {},
                (a, b) -> "compiled{" + a + "}");
        String expected = "01-01-2024 01:00:00 [INF]: compiled{origMessage}";
        logEntryPublisher.publish(logEntry);
        
        String actual = outputStream.toString().replace(System.getProperty("line.separator"), "");
        
        assertThat(actual).isEqualTo(expected);
    }
}
