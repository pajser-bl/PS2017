package util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class TimeUtility {
	public static LocalDateTime getLDTNow() {
		return LocalDateTime.now();
	}

	public static String formatDate(LocalDateTime localDateTime) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.YYYY");
		return simpleDateFormat.format(localDateTime);
	}

	public static String formatTime(LocalDateTime localDateTime) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
		return simpleDateFormat.format(localDateTime);
	}

	public static String formatTimeDate(LocalDateTime localDateTime) {
		return formatDate(localDateTime) + " " + formatTime(localDateTime);
	}
	
	public static String localDateTimeToDBString(LocalDateTime localDateTime) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		return simpleDateFormat.format(localDateTime);
	}
}