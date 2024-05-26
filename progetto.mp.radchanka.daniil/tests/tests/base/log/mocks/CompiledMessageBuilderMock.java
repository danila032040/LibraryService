package tests.base.log.mocks;

import java.util.function.BiFunction;

import base.log.CompiledMessageBuilder;

public class CompiledMessageBuilderMock implements CompiledMessageBuilder {
	private BiFunction<String, Object[], String> resultStringBuilder;
	private String lastSpecifiedOriginalMessage;
	private Object[] lastSpecifiedArguments;

	public CompiledMessageBuilderMock(
			BiFunction<String, Object[], String> resultStringBuilder) {
		super();
		this.resultStringBuilder = resultStringBuilder;
	}

	@Override
	public String compileMessage(String originalMessage, Object... arguments) {
		this.lastSpecifiedOriginalMessage = originalMessage;
		this.lastSpecifiedArguments = arguments;
		return resultStringBuilder.apply(originalMessage, arguments);
	}

	public String getLastSpecifiedOriginalMessage() {
		return lastSpecifiedOriginalMessage;
	}

	public Object[] getLastSpecifiedArguments() {
		return lastSpecifiedArguments;
	}

}
