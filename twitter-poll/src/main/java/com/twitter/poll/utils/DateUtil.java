package com.twitter.poll.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateUtil {

	public static java.sql.Date getDate(String pDate) throws Exception {
		if (pDate == null || (pDate != null && pDate.trim().isEmpty())) {
			throw new Exception("Validate Date Formate like 'dd-MM-yyyy'");
		}
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		try {
			Date vDate = format.parse(pDate);
			LocalDate localDate = Instant.ofEpochMilli(vDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
			return java.sql.Date.valueOf(localDate);
		} catch (ParseException e) {
			throw new Exception("Validate Date Formate like 'dd-MM-yyyy'");
		}
	}

	public static String getTimeStampFormat(Timestamp pTimestamp) {

		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String date = "";
		if (pTimestamp != null) {
			date = formatter.format(pTimestamp);
		}
		return date;
	}

	public static String getDateFormatFromString(java.sql.Date pDate) {

		DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
		if (pDate != null) {
			return formater.format(pDate);
		}
		return null;
	}
}
