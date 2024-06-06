package base.mediator.request.pipeline;

import java.util.Deque;
import java.util.LinkedList;

import base.mediator.request.Request;
import base.mediator.request.RequestHandler;
import base.utils.IteratorUtils;

public class Pipeline<TRequest extends Request<TResult>, TResult> implements PipelineBuilder<TRequest, TResult> {
    
    private final RequestHandler<TRequest, TResult> baseHandler;
    private final Deque<PipelineBehaviour<TRequest, TResult>> behaviours;
    
    private Pipeline(RequestHandler<TRequest, TResult> baseHandler) {
        this.behaviours = new LinkedList<>();
        this.baseHandler = baseHandler;
    }
    
    public static <TRequest extends Request<TResult>, TResult> PipelineBuilder<TRequest, TResult> withBaseHandler(
            RequestHandler<TRequest, TResult> baseHandler) {
        return new Pipeline<>(baseHandler);
    }
    
    @Override
    public PipelineBuilder<TRequest, TResult> continueWith(PipelineBehaviour<TRequest, TResult> pipelineBehaviour) {
        behaviours.add(pipelineBehaviour);
        return this;
    }
    
    @Override
    public RequestHandler<TRequest, TResult> buildRequestHandler() {
        return IteratorUtils
                .reduceRemaining(
                        behaviours.descendingIterator(),
                        baseHandler,
                        (next, pipeline) -> (request) -> pipeline.handle(request, next));
    }
}
