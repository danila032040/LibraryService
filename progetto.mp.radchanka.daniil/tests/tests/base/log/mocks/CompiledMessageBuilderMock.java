package tests.base.log.mocks;

import java.util.function.BiFunction;

import base.log.CompiledMessageBuilder;

public class CompiledMessageBuilderMock implements CompiledMessageBuilder {
    private final BiFunction<String, Object[], String> resultStringBuilder;
    
    private Object[] lastSpecifiedArguments;
    private String lastSpecifiedOriginalMessage;
    
    public CompiledMessageBuilderMock(BiFunction<String, Object[], String> resultStringBuilder) {
        super();
        this.resultStringBuilder = resultStringBuilder;
    }
    
    @Override
    public String compileMessage(String originalMessage, Object... arguments) {
        this.lastSpecifiedOriginalMessage = originalMessage;
        this.lastSpecifiedArguments = arguments;
        return resultStringBuilder.apply(originalMessage, arguments);
    }
    
    public Object[] getLastSpecifiedArguments() {
        return lastSpecifiedArguments;
    }
    
    public String getLastSpecifiedOriginalMessage() {
        return lastSpecifiedOriginalMessage;
    }
}
