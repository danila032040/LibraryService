package base.mediator.request.pipeline;

import base.mediator.request.Request;
import base.mediator.request.RequestHandler;

public class RequestHandlerPipeline<TRequest extends Request<TResult>, TResult>
        implements
        RequestHandler<TRequest, TResult>,
        RequestHandlerPipelineBuilder<TRequest, TResult> {
    
    private final RequestHandlerPipelineBehaviour<TRequest, TResult> firstChainElement;
    private RequestHandlerPipelineBehaviour<TRequest, TResult> lastChainElement;
    
    private RequestHandlerPipeline(RequestHandlerPipelineBehaviour<TRequest, TResult> firstChainElement) {
        this.firstChainElement = firstChainElement;
        this.lastChainElement = firstChainElement;
    }
    
    public static <TRequest extends Request<TResult>, TResult> RequestHandlerPipelineBuilder<TRequest, TResult> startWith(
            RequestHandlerPipelineBehaviour<TRequest, TResult> initialChainElement) {
        return new RequestHandlerPipeline<>(initialChainElement);
    }
    
    @Override
    public TResult handle(TRequest request) {
        return this.firstChainElement.handle(request);
    }
    
    @Override
    public RequestHandlerPipelineBuilder<TRequest, TResult> continueWith(
            RequestHandlerPipelineBehaviour<TRequest, TResult> chainElement) {
        this.lastChainElement.setNext(chainElement);
        this.lastChainElement = chainElement;
        return this;
    }
    
    @Override
    public RequestHandler<TRequest, TResult> buildRequestHandler() {
        return this;
    }
    
}
