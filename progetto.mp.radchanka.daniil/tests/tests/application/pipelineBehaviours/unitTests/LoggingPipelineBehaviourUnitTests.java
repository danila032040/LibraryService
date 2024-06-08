package tests.application.pipelineBehaviours.unitTests;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Clock;
import java.time.Instant;

import org.junit.Test;

import application.pipelineBehaviours.LoggingPipelineBehaviour;
import base.log.LogLevelType;
import base.mediator.request.RequestHandler;
import base.result.ErrorOr;
import base.utils.StopWatch;
import tests.application.pipelineBehaviours.mocks.ClockMock;
import tests.application.pipelineBehaviours.mocks.LoggerMock;
import tests.application.pipelineBehaviours.mocks.RequestHandlerMock;
import tests.application.pipelineBehaviours.mocks.RequestMock;

public class LoggingPipelineBehaviourUnitTests {
    
    @Test
    public void handle_ShouldLogHandlingAndFinishing() {
        LoggerMock logger = new LoggerMock();
        ClockMock clock = new ClockMock(Instant.now());
        RequestHandler<RequestMock, ErrorOr<String>> nextHandler = (requst) -> {
            clock.addSeconds(5);
            return ErrorOr.fromErrorMessage("Test");
        };
        LoggingPipelineBehaviour<RequestMock, ErrorOr<String>> loggingPipelineBehaviour = new LoggingPipelineBehaviour<>(
                logger,
                StopWatch.from(clock),
                "TestRequest");
        RequestMock request = new RequestMock();
        
        loggingPipelineBehaviour.handle(request, nextHandler);
        
        assertThat(logger.getSpesifiedLogLevels()).containsOnly(LogLevelType.Information);
        assertThat(logger.getSpecifiedArgumentsList()).satisfiesExactly(specifiedArguments0 -> {
            assertThat(specifiedArguments0).containsExactly("TestRequest");
        }, specifiedArguments1 -> {
            assertThat(specifiedArguments1).containsExactly("TestRequest", 5000L);
        });
        assertThat(logger.getSpecifiedMessages())
                .containsExactly("{0} -- Handling Request", "{0} -- Finished Request in {1} ms");
    }
    
    @Test
    public void handle_ShouldReturnResultFromNextHandler() {
        RequestHandlerMock nextHandler = new RequestHandlerMock("Test");
        LoggingPipelineBehaviour<RequestMock, ErrorOr<String>> loggingPipelineBehaviour = new LoggingPipelineBehaviour<>(
                new LoggerMock(),
                StopWatch.from(Clock.systemUTC()),
                "TestRequest");
        RequestMock request = new RequestMock();
        
        ErrorOr<String> result = loggingPipelineBehaviour.handle(request, nextHandler);
        
        assertThat(result.getResult()).hasValue("Test");
        assertThat(nextHandler.isCalled()).isTrue();
    }
}
