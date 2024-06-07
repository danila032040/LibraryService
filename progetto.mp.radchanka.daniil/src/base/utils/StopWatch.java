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
        startInstant.ifPresent(start -> {
            Instant stop = clock.instant();
            duration.plus(Duration.between(start, stop));
        });
        startInstant = Optional.empty();
    }
    
    public Duration getDuration() {
        return duration;
    }
    
}
