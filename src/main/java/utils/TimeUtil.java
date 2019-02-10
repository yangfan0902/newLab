package utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class TimeUtil {
	static DateTime dateTime;

	public static String getStartTime() {
		dateTime = new DateTime();
		int dayOfWeek = dateTime.getDayOfWeek();
		if (dayOfWeek >= 5) {

			return (dateTime.dayOfWeek().withMinimumValue().hourOfDay().withMinimumValue().withMinuteOfHour(0)
					.withSecondOfMinute(0).plusDays(4)).toString("yyyy-MM-dd HH:mm:ss");
		}
		return (dateTime.dayOfWeek().withMinimumValue().hourOfDay().withMinimumValue().withMinuteOfHour(0)
				.withSecondOfMinute(0).minusDays(3)).toString("yyyy-MM-dd HH:mm:ss");
	}

	public static String getEndTime() {
		dateTime = new DateTime();
		int dayOfWeek = dateTime.getDayOfWeek();
		if (dayOfWeek >= 5) {

			return (dateTime.dayOfWeek().withMinimumValue().hourOfDay().withMaximumValue().withMinuteOfHour(59)
					.withSecondOfMinute(59).plusDays(10)).toString("yyyy-MM-dd HH:mm:ss");
		}
		return (dateTime.dayOfWeek().withMinimumValue().hourOfDay().withMaximumValue().withMinuteOfHour(59)
				.withSecondOfMinute(59).plusDays(3)).toString("yyyy-MM-dd HH:mm:ss");
	}
}
