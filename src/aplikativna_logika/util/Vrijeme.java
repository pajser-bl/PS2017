package aplikativna_logika.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class Vrijeme {
	public static LocalDateTime trenutnoVrijeme() {
		return LocalDateTime.now();
	}

	public static String formatDatum(LocalDateTime localDateTime) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.YYYY");
		return simpleDateFormat.format(localDateTime);
	}

	public static String formatVrijeme(LocalDateTime localDateTime) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
		return simpleDateFormat.format(localDateTime);
	}

	public static String formatVrijemeDatum(LocalDateTime localDateTime) {
		return formatDatum(localDateTime) + " " + formatVrijeme(localDateTime);
	}

}