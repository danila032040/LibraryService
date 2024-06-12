package base.utils;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

public class StopWatch {
    
    public static StopWatch from(Clock clock) {
        return new StopWatch(clock);
    }
    private final Clock clock;
    private Duration duration;
    
    private Optional<Instant> startInstant;
    
    private StopWatch(Clock clock) {
        this.clock = Objects.requireNonNull(clock);
        this.startInstant = Optional.empty();
        this.duration = Duration.ZERO;
    }
    
    public Duration getDuration() {
        if (isStarted())
            recalculateDuration();
        return duration;
    }
    
    public void reset() {
        startInstant = Optional.empty();
        duration = Duration.ZERO;
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
    
    private boolean isStarted() {
        return startInstant.isPresent();
    }
    
    private void recalculateDuration() {
        Instant start = startInstant.orElseThrow();
        Instant stop = clock.instant();
        duration = duration.plus(Duration.between(start, stop));
        startInstant = Optional.of(stop);
    }
    
}
