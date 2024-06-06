package base.mediator.request.pipeline;

import base.mediator.request.Request;
import base.mediator.request.RequestHandler;

public interface PipelineBuilder<TRequest extends Request<TResult>, TResult> {
    
    public PipelineBuilder<TRequest, TResult> continueWith(
            PipelineBehaviour<TRequest, TResult> chainElement);
    
    public RequestHandler<TRequest, TResult> buildRequestHandler();
}
