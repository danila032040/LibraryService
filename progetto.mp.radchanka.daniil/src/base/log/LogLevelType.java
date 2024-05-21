package base.log;

public enum LogLevelType {
	Information,
	Debug,
	Warning,
	Error,
	Fatal;

	public String toShortString() {
		switch (this) {
			case Information :
				return "INF";
			case Debug :
				return "DBG";
			case Warning :
				return "WARN";
			case Error :
				return "ERR";
			case Fatal :
				return "FTL";
			default :
				throw new IllegalStateException();
		}
	}
}
