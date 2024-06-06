package base.mediator.request.pipeline;

import base.mediator.request.Request;
import base.mediator.request.RequestHandler;

public interface PipelineBehaviour<TRequest extends Request<TResult>, TResult> {
    public TResult handle(TRequest request, RequestHandler<TRequest, TResult> next);
}
