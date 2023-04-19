package com.xworkz.googlesheetconnection.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class GoogleSheetUtil {

	public static String getColumnLetter(int column) {
		StringBuilder sb = new StringBuilder();
		while (column > 0) {
			int remainder = (column - 1) % 26;
			sb.insert(0, (char) ('A' + remainder));
			column = (column - 1) / 26;
		}
		return sb.toString();
	}

	

}
