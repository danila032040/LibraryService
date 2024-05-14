package base.log;

public class LogScope implements AutoCloseable {

	private String name;
	private Logger logger;

	public LogScope(Logger logger, String name) {
		this.logger = logger;
		this.name = name;

	}

	public String getName() {
		return name;
	}

	@Override
	public void close() throws Exception {
		if (logger == null)
			return;
		logger = null;
		logger.closeScope();
	}

}
