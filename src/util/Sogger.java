package util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class Sogger {

	private static Logger logger = null;

	static {
		LogManager.getLogManager().reset();
		logger = Logger.getLogger("servlet");

		ConsoleHandler consoleHandler = new ConsoleHandler();
		consoleHandler.setLevel(Level.ALL);
		consoleHandler.setFormatter(new Formatter() {
			public String format(LogRecord logRecord) {
				return "\n" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()) + " " + logRecord.getLevel().getLocalizedName() + ": " + logRecord.getMessage();
			}
		});
		logger.addHandler(consoleHandler);

		FileHandler fileHandler = null;
		try {
			String rootPath = Sogger.class.getClassLoader().getResource("/").getPath();
			File logFile = new File(rootPath, "servlet.log");
			fileHandler = new FileHandler(logFile.getCanonicalPath(), true);
			fileHandler.setLevel(Level.ALL);
			fileHandler.setFormatter(new Formatter() {
				public String format(LogRecord logRecord) {
					return "\n" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()) + " " + logRecord.getLevel().getLocalizedName() + ": " + logRecord.getMessage();
				}
			});
		} catch (Exception e) {
			Sogger.severe(e);
		}
		logger.addHandler(fileHandler);
	}

	public static void severe(String message) {
		logger.severe(message);
	}

	public static void warning(String message) {
		logger.warning(message);
	}

	public static void info(String message) {
		logger.info(message);
	}

	public static void config(String message) {
		logger.config(message);
	}

	public static void fine(String message) {
		logger.fine(message);
	}

	public static void finer(String message) {
		logger.finer(message);
	}

	public static void finest(String message) {
		logger.finest(message);

	}

	public static void severe(Exception e) {
		StringBuffer stringBuffer = new StringBuffer(e.toString());
		for (StackTraceElement stackTrace : e.getStackTrace()) {
			stringBuffer.append("\n\tat " + stackTrace.getClassName() + "." + stackTrace.getMethodName() + "(" + stackTrace.getFileName() + ":" + stackTrace.getLineNumber() + ")");
		}
		logger.severe(stringBuffer.toString());
	}
}
