package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {

	private File file;
	private PrintWriter logTracking;
	DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
	LocalDateTime localTime = LocalDateTime.now();

	public Log(String fileName) {
		this.file = new File(fileName);

		try {
			FileOutputStream output = new FileOutputStream(file, true);
			this.logTracking = new PrintWriter(output);
		} catch (FileNotFoundException e) {
			System.out.println("Not Valid");
		}
	}

	public void writeFile(String input) {
		logTracking.write(dateTime.format(localTime.now()) + " " + input + "\n");
		logTracking.flush();
	}
}
