package tests.base.log.unitTests;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

import base.log.LogConfiguration;

public class LogConfigurationUnitTests {
    
    @Test
    public void setLocalDateTimeProvider_WhenLocalDateProviderIsNull_ShouldThrowNullPointerException() {
        LogConfiguration logConfiguration = new LogConfiguration();
        
        ThrowingCallable actual = () -> logConfiguration.setLocalDateTimeProvider(null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void setLocale_WhenLocaleIsNull_ShouldThrowNullPointerException() {
        LogConfiguration logConfiguration = new LogConfiguration();
        
        ThrowingCallable actual = () -> logConfiguration.setLocale(null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void setMinimalLogLevel_WhenMinimalLogLevelIsNull_ShouldThrowNullPointerException() {
        LogConfiguration logConfiguration = new LogConfiguration();
        
        ThrowingCallable actual = () -> logConfiguration.setMinimalLogLevel(null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void setTimeStampFormat_WhenTimeStampFormatIsNull_ShouldThrowNullPointerException() {
        LogConfiguration logConfiguration = new LogConfiguration();
        
        ThrowingCallable actual = () -> logConfiguration.setTimeStampFormat(null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
}
