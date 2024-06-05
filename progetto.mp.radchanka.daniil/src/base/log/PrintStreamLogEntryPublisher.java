package base.log;

import java.io.PrintStream;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;
import java.util.function.Supplier;

public class PrintStreamLogEntryPublisher implements LogEntryPublisher {
    private final Supplier<Locale> localeSupplier;
    private final PrintStream printStream;
    private final Supplier<String> timeStampFormatSupplier;
    
    public PrintStreamLogEntryPublisher(
            Supplier<Locale> localeSupplier,
            Supplier<String> timeStampFormatSupplier,
            PrintStream printStream) {
        this.localeSupplier = Objects.requireNonNull(localeSupplier);
        this.timeStampFormatSupplier = Objects.requireNonNull(timeStampFormatSupplier);
        this.printStream = Objects.requireNonNull(printStream);
    }
    
    @Override
    public void publishLogEntry(LogEntry logEntry) {
        Locale locale = localeSupplier.get();
        String timeStamp = DateTimeFormatter
                .ofPattern(timeStampFormatSupplier.get(), locale)
                .format(logEntry.getDateTime());
        String logLevelType = logEntry.getLogLevel().toShortString();
        String compiledMessage = logEntry.getCompiledMessage();
        
        printStream.println(String.format(locale, "%s [%s]: %s", timeStamp, logLevelType, compiledMessage));
    }
    
}
