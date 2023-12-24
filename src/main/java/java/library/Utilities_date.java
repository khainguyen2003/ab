package library;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

//Lấy ngày
public class Utilities_date {
	public static String getDate(String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		
		Date date = new Date();
		
		return dateFormat.format(date);
 	}
	public static String getCurrentDate() {
		return Utilities_date.getDate("dd/MM/yyyy");
 	}
	
	public static String getDate(String format, double numericDate) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		
		Date date = new Date((long) (numericDate * 1000));
		
		return dateFormat.format(date);
 	}
	
	public static String getDate(double numericDate) {
		return Utilities_date.getDate("dd/MM/yyyy", numericDate);
 	}
	
	public static String getDate() {
		return Utilities_date.getDate("dd/MM/yyyy");
 	}
	
	public static String getDateProfiles() {
		return Utilities_date.getDate("ddMMyyHHmmss");
 	}
	
}
