package base.mediator.request.pipeline;

import base.mediator.request.Request;
import base.mediator.request.RequestHandler;

public interface RequestHandlerPipelineBehaviour<TRequest extends Request<TResult>, TResult>
        extends
        RequestHandler<TRequest, TResult> {
    public void setNext(RequestHandlerPipelineBehaviour<TRequest, TResult> next);
}
