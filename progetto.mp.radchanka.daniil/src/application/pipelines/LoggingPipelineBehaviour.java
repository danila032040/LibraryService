package application.pipelines;

import java.time.Duration;
import java.time.Instant;

import base.log.Logger;
import base.mediator.pipeline.PipelineBehaviour;
import base.mediator.request.Request;
import base.mediator.request.RequestHandler;

public class LoggingPipelineBehaviour<TRequest extends Request<TResult>, TResult>
        implements
        PipelineBehaviour<TRequest, TResult> {
    
    private final Logger logger;
    private final Class<TResult> resultClass;
    
    public LoggingPipelineBehaviour(Logger logger, Class<TResult> resultClass) {
        super();
        this.logger = logger;
        this.resultClass = resultClass;
    }
    
    @Override
    public TResult handle(TRequest request, RequestHandler<TRequest, TResult> next) {
        logger.logInformation("{0} -- Handling Request", resultClass.getName());
        Instant timerStart = Instant.now();
        
        TResult result = next.handle(request);
        
        Instant timerEnd = Instant.now();
        logger
                .logInformation(
                        "{0} -- Finished Request in {1} ms",
                        resultClass.getName(),
                        Duration.between(timerStart, timerEnd).toMillis());
        return result;
    }
}
