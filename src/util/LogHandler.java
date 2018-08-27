package util;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogHandler {
	private static FileHandler fH;

	public static void init() {
		try {
			fH = new FileHandler("serverErrors.log", false);
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
		Logger l = Logger.getLogger("");
		fH.setFormatter(new SimpleFormatter());
		l.addHandler(fH);
		l.setLevel(Level.CONFIG);
	}
}
