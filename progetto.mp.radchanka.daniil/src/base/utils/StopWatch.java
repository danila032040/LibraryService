package base.utils;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

public class StopWatch {
    
    private final Clock clock;
    private Duration duration;
    private Optional<Instant> startInstant;
    
    private StopWatch(Clock clock) {
        this.clock = clock;
        this.startInstant = Optional.empty();
        this.duration = Duration.ZERO;
    }
    
    public static StopWatch from(Clock clock) {
        return new StopWatch(clock);
    }
    
    public void start() {
        startInstant = Optional.of(clock.instant());
    }
    
    public void stop() {
        if (!isStarted())
            return;
        recalculateDuration();
        startInstant = Optional.empty();
    }
    
    public void reset() {
        startInstant = Optional.empty();
        duration = Duration.ZERO;
    }
    
    private void recalculateDuration() {
        Instant start = startInstant.orElseThrow();
        Instant stop = clock.instant();
        duration = duration.plus(Duration.between(start, stop));
        startInstant = Optional.of(stop);
    }
    
    public Duration getDuration() {
        if (isStarted())
            recalculateDuration();
        return duration;
    }
    
    private boolean isStarted() {
        return startInstant.isPresent();
    }
    
}
