package application.pipelineBehaviours;

import base.log.Logger;
import base.mediator.pipeline.PipelineBehaviour;
import base.mediator.request.Request;
import base.mediator.request.RequestHandler;
import base.utils.StopWatch;

public class LoggingPipelineBehaviour<TRequest extends Request<TResult>, TResult>
        implements
        PipelineBehaviour<TRequest, TResult> {
    
    private final Logger logger;
    private final StopWatch stopWatch;
    private final String requestName;
    
    public LoggingPipelineBehaviour(Logger logger, StopWatch stopWatch, String requestName) {
        this.logger = logger;
        this.stopWatch = stopWatch;
        this.requestName = requestName;
    }
    
    @Override
    public TResult handle(TRequest request, RequestHandler<TRequest, TResult> next) {
        stopWatch.reset();
        logger.logInformation("{0} -- Handling Request", requestName);
        stopWatch.start();
        
        TResult result = next.handle(request);
        
        stopWatch.stop();
        logger.logInformation("{0} -- Finished Request in {1} ms", requestName, stopWatch.getDuration().toMillis());
        return result;
    }
}
