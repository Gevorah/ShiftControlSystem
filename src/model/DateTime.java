package model;

import java.time.*;
import java.time.format.*;

/**
*	This class allows create shifts.
*	@author Jhon Ijaji.
*	@version 2.0
*	@since 1.0
*/
public class DateTime {
	
	private LocalDateTime dateTime;
	private LocalDateTime aux;
	private DateTimeFormatter dtf;
	
	private int y;
	private int m;
	private int d;
	private int h;
	private int mn;
	private int s;
	
	/**
	 * This method allows create a date-time for the program.
	 */
	public DateTime() {
		dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		dateTime = LocalDateTime.now();
		aux = LocalDateTime.now();
	}
	
	/*
	 * This method allows reset the program date-time to the date-time from the system.
	 */
	public void resetDateTime() {
		dateTime = LocalDateTime.now();
	}
	
	/**
	 * This method allows set the program date-time.
	 * @param year The year to set.
	 * @param month The month to set.
	 * @param day The day to set.
	 * @param hour The hour to set.
	 * @param min The minute to set.
	 */
	public void setDateTime(int year, int month, int day, int hour, int min) {
		dateTime = LocalDateTime.of(year,month,day,hour,min);
		LocalDateTime curr = LocalDateTime.now();
		y = dateTime.getYear()-curr.getYear();
		m = dateTime.getMonthValue()-curr.getMonthValue();
		d = dateTime.getDayOfMonth()-curr.getDayOfMonth();
		h = dateTime.getHour()-curr.getHour();
		mn = dateTime.getMinute()-curr.getMinute();
		s = dateTime.getSecond()-curr.getSecond();
	}
	
	/**
	 * This method allows synchronize the program date-time.
	 * @return The current program date-time.
	 */
	public LocalDateTime currentDateTime() {
		return LocalDateTime.now().plus(Period.of(y,m,d)).plus(Duration.ofHours(h).plus(Duration.ofMinutes(mn)).plus(Duration.ofSeconds(s)));
	}
	
	/**
	 * This method allows add seconds to the current program date-time. 
	 * @param minutes The minutes to add.
	 * @return A date time with minutes added.
	 */
	public LocalDateTime plus(LocalDateTime date,float minutes) {
		return date.plusSeconds((long)(minutes*60));
	}
	
	/**
	 * This method allows check if this date-time is before the specified date-time.
	 * @param year The year to check.
	 * @param month The month to check.
	 * @param day The day to check.
	 * @param hour The hour to check.
	 * @param min The minute to check.
	 * @return true if this date-time is before the specified date-time
	 */
	public boolean checkDate(int year, int month, int day, int hour, int min){
        LocalDateTime to = LocalDateTime.of(year,month,day,hour,min);
        return to.isBefore(currentDateTime());
    }
	
	/**
	 * This method allows check if this date-time is before the specified date-time. 
	 * @param date The date-time to compare.
	 * @return true if this date-time is before the specified date-time.
	 */
	public boolean checkDate(LocalDateTime date) {
		return aux.isBefore(date);
	}
	
	/**
	 * This method allows parse a text to format of date.
	 * @param date The date to parse.
	 * @return The date formatted.
	 */
	public LocalDateTime parse(String date) {
		return LocalDateTime.parse(date,dtf);
	}
	
	/**
	 * This method allows show the date-time in specific format.
	 * @param dt The date-time to change format.
	 * @return The date-time formatted.
	 */
	public String format(LocalDateTime dt) {
		return dt.format(dtf);
	}
	
}
