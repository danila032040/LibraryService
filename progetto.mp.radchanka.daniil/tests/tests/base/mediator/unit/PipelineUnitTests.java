package tests.base.mediator.unit;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Lists;
import org.junit.Test;

import base.mediator.pipeline.Pipeline;
import base.mediator.request.RequestHandler;
import tests.base.mediator.mocks.PipelineBehaviourMock;
import tests.base.mediator.mocks.RequestHandlerMock;
import tests.base.mediator.mocks.RequestMock0;

public class PipelineUnitTests {
    @Test
    public void buildRequestHandler_WithBehaviours_ShouldReturnHandlerWhichRepresentsPipelineCorrectly() {
        RequestHandlerMock handler = new RequestHandlerMock();
        RequestMock0 request1 = new RequestMock0();
        RequestMock0 request2 = new RequestMock0();
        RequestMock0 request3 = new RequestMock0();
        PipelineBehaviourMock behaviour1 = new PipelineBehaviourMock(req -> request2);
        PipelineBehaviourMock behaviour2 = new PipelineBehaviourMock(req -> request3);
        
        RequestHandler<RequestMock0, Integer> actualHandler = Pipeline
                .forRequestHandler(handler)
                .addPipelineBehaviour(behaviour1)
                .addPipelineBehaviour(behaviour2)
                .buildAsRequestHandler();
        int actualResult = actualHandler.handle(request1);
        
        List<Optional<RequestMock0>> specifiedRequestsInPipelineOrder = Lists
                .list(
                        behaviour1.getLastSpecifiedRequest(),
                        behaviour2.getLastSpecifiedRequest(),
                        handler.getLastSpecifiedRequest());
        
        assertThat(actualResult).isEqualTo(0);
        
        assertThat(specifiedRequestsInPipelineOrder)
                .satisfiesExactly(
                        specifiedRequest -> assertThat(specifiedRequest).containsSame(request1),
                        specifiedRequest -> assertThat(specifiedRequest).containsSame(request2),
                        specifiedRequest -> assertThat(specifiedRequest).containsSame(request3));
    }
    
    @Test
    public void buildRequestHandler_WithNoBehaviours_ShouldReturnBaseHandler() {
        RequestHandlerMock handler = new RequestHandlerMock();
        Pipeline<RequestMock0, Integer> pipeline = Pipeline.forRequestHandler(handler);
        
        RequestHandler<RequestMock0, Integer> resultHandler = pipeline.buildAsRequestHandler();
        
        assertThat(resultHandler).isSameAs(handler);
    }
}
