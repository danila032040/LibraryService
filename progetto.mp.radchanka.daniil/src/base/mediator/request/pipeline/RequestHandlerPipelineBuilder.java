package base.mediator.request.pipeline;

import base.mediator.request.Request;
import base.mediator.request.RequestHandler;

public interface RequestHandlerPipelineBuilder<TRequest extends Request<TResult>, TResult> {
    
    public RequestHandlerPipelineBuilder<TRequest, TResult> continueWith(
            RequestHandlerPipelineBehaviour<TRequest, TResult> chainElement);
    
    public RequestHandler<TRequest, TResult> buildRequestHandler();
}
