package tests.base.utils.unitTests;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.time.Instant;

import org.junit.Test;

import base.utils.StopWatch;
import tests.base.utils.mocks.ClockMock;

public class StopWatchUnitTests {
    @Test
    public void getDuration_WhenStopWatchNotStarted_ShouldReturnZero() {
        Instant instant = Instant.now();
        ClockMock clockMock = new ClockMock(instant);
        StopWatch stopWatch = StopWatch.from(clockMock);
        
        Duration duration = stopWatch.getDuration();
        
        assertThat(duration).isEqualTo(Duration.ZERO);
    }
    
    @Test
    public void getDuration_WhenStopWatchStartedAndStopped_ShouldReturnElapsedTime() {
        Instant instant = Instant.now();
        ClockMock clockMock = new ClockMock(instant);
        StopWatch stopWatch = StopWatch.from(clockMock);
        
        stopWatch.start();
        clockMock.addSeconds(5);
        stopWatch.stop();
        Duration duration = stopWatch.getDuration();
        
        assertThat(duration.toSeconds()).isEqualTo(5);
    }
    
    @Test
    public void getDuration_WhenStopWatchStartedAndStoppedTwice_ShouldReturnElapsedTimeBetweenStartAndFirstStop() {
        Instant instant = Instant.now();
        ClockMock clockMock = new ClockMock(instant);
        StopWatch stopWatch = StopWatch.from(clockMock);
        
        stopWatch.start();
        clockMock.addSeconds(5);
        stopWatch.stop();
        clockMock.addSeconds(1);
        stopWatch.stop();
        clockMock.addSeconds(1);
        Duration duration = stopWatch.getDuration();
        
        assertThat(duration.toSeconds()).isEqualTo(5);
    }
    
    @Test
    public void getDuration_WhenStopWatchStartedAndNotStopped_ShouldReturnElapsedTimeUntilNow() {
        Instant instant = Instant.now();
        ClockMock clockMock = new ClockMock(instant);
        StopWatch stopWatch = StopWatch.from(clockMock);
        
        stopWatch.start();
        clockMock.addSeconds(5);
        
        Duration duration = stopWatch.getDuration();
        
        assertThat(duration.toSeconds()).isEqualTo(5);
    }
    
    @Test
    public void getDuration_WhenStopWatchStartedResetedStarted_ShouldReturnElapsedTimeBetweenNowAndLastStart() {
        Instant instant = Instant.now();
        ClockMock clockMock = new ClockMock(instant);
        StopWatch stopWatch = StopWatch.from(clockMock);
        
        stopWatch.start();
        clockMock.addSeconds(5);
        stopWatch.reset();
        stopWatch.start();
        clockMock.addSeconds(1);
        
        Duration duration = stopWatch.getDuration();
        
        assertThat(duration.toSeconds()).isEqualTo(1);
    }
    
    @Test
    public void getDuration_WhenStopWatchStartedResetedStarted_ShouldReturnElapsedTimeBetweenStopAndLastStart() {
        Instant instant = Instant.now();
        ClockMock clockMock = new ClockMock(instant);
        StopWatch stopWatch = StopWatch.from(clockMock);
        
        stopWatch.start();
        clockMock.addSeconds(5);
        stopWatch.reset();
        stopWatch.start();
        clockMock.addSeconds(2);
        stopWatch.stop();
        clockMock.addSeconds(5);
        
        Duration duration = stopWatch.getDuration();
        
        assertThat(duration.toSeconds()).isEqualTo(2);
    }
}
