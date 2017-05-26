package utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateParser {

	private DateParser() {
	}

	public static Timestamp getTimestamp(String date, String pattern) {

		DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern);
		DateTime dateTime = formatter.parseDateTime(String.format("%s", date));

		return new Timestamp(dateTime.getMillis());
	}

	public static String getTime(Timestamp timestamp, String datePattern) {

		Date date = new Date(timestamp.getTime());

		String formattedDate = new SimpleDateFormat(datePattern).format(date);

		return formattedDate;
	}

}