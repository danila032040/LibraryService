package application.commands.common;

import base.mediator.request.Request;
import base.mediator.request.pipeline.RequestHandlerPipelineBehaviour;

public class LoggingCommandHandlerChainElement<TRequest extends Request<TResult>, TResult>
        implements
        RequestHandlerPipelineBehaviour<TRequest, TResult> {
    
    @Override
    public TResult handle(TRequest request) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public void setNext(RequestHandlerPipelineBehaviour<TRequest, TResult> next) {
        // TODO Auto-generated method stub
        
    }
    
}
