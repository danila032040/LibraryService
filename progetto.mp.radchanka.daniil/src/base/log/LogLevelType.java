package base.log;

public enum LogLevelType {
	Information {
		@Override
		public String toShortString() {
			return "INF";
		}
	},
	Debug {
		@Override
		public String toShortString() {
			return "DBG";
		}
	},
	Warning {
		@Override
		public String toShortString() {
			return "WRN";
		}
	},
	Error {
		@Override
		public String toShortString() {
			return "ERR";
		}
	},
	Fatal {
		@Override
		public String toShortString() {
			return "FTL";
		}
	};

	public abstract String toShortString();

}
