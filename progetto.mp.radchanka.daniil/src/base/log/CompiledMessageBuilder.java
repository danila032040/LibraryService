package base.log;

public interface CompiledMessageBuilder {
	public String compileMessage(String originalMessage, Object... arguments);
}
