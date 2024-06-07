package application.pipelineBehaviours;

import java.time.Clock;

import base.log.Logger;
import base.mediator.pipeline.PipelineBehaviour;
import base.mediator.request.Request;
import base.mediator.request.RequestHandler;
import base.utils.StopWatch;

public class LoggingPipelineBehaviour<TRequest extends Request<TResult>, TResult>
        implements
        PipelineBehaviour<TRequest, TResult> {
    
    private final Logger logger;
    private final Clock clock;
    private final Class<TResult> resultClass;
    
    public LoggingPipelineBehaviour(Logger logger, Clock clock, Class<TResult> resultClass) {
        this.logger = logger;
        this.clock = clock;
        this.resultClass = resultClass;
    }
    
    @Override
    public TResult handle(TRequest request, RequestHandler<TRequest, TResult> next) {
        StopWatch stopWatch = StopWatch.from(clock);
        logger.logInformation("{0} -- Handling Request", resultClass.getName());
        stopWatch.start();
        
        TResult result = next.handle(request);
        
        stopWatch.stop();
        logger
                .logInformation(
                        "{0} -- Finished Request in {1} ms",
                        resultClass.getName(),
                        stopWatch.getDuration().toMillis());
        return result;
    }
}
