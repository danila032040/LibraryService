package tests.application.pipelineBehaviours.mocks;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

public class ClockMock extends Clock {
    private Instant instant;
    
    public ClockMock(Instant instant) {
        this.instant = instant;
    }
    
    @Override
    public ZoneId getZone() {
        return ZoneId.systemDefault();
    }
    
    @Override
    public Clock withZone(ZoneId zone) {
        return this;
    }
    
    @Override
    public Instant instant() {
        return instant;
    }
    
    public void addSeconds(long seconds) {
        instant = instant.plusSeconds(seconds);
    }
}
