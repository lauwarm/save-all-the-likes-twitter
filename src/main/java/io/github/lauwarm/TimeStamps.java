package io.github.lauwarm;

import java.util.Properties;

/**
 * @author fabian
 *
 */
public class TimeStamps {
	
	private static Long timestampOld = null;
	private static Long timestampNew = null;

	public TimeStamps() {
		Properties properties = ConfigFile.getProperties();
		setTimestampOld(Long.parseLong(properties.getProperty("timestamp")));
	}
	
	public static Long getTimestampOld() {
		return timestampOld;
	}
	
	public static void setTimestampOld(Long timestampOld) {
		TimeStamps.timestampOld = timestampOld;
	}
	
	public static Long getTimestampNew() {
		return timestampNew;
	}
	
	public static void setTimestampNew(Long timestampNew) {
		TimeStamps.timestampNew = timestampNew;
	}
	
}
