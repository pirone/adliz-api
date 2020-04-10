package br.com.pirone.adliz.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class DateUtils {
	
	private static String dateFormat = "dd/MM/yyyy";
	private static String jsDateFormat = "EE MMM d y H:m:s 'GMT'Z (zz)";
	
	static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
	
	public static String getFormatedDateAsString(Date date) {
		simpleDateFormat = new SimpleDateFormat(dateFormat);
		return simpleDateFormat.format(date);
	}
	
	public static Date getFormatedStringAsDate(String unix) throws ParseException {
		Instant instant = Instant.ofEpochMilli(Long.valueOf(unix));
		Date date = Date.from(instant);
		return date;
	}

}
