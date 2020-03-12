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
	public LocalDateTime plus(float minutes) {
		return currentDateTime().plusSeconds((long)(minutes*60));
	}
	
	/**
	 * This method allows check if the given date-time is before to the program date-time.
	 * @param year The year to check.
	 * @param month The month to check.
	 * @param day The day to check.
	 * @param hour The hour to check.
	 * @param min The minute to check.
	 * @return if is before.
	 */
	public boolean checkDate(int year, int month, int day, int hour, int min){
        LocalDateTime to = LocalDateTime.of(year,month,day,hour,min);
        return to.isBefore(dateTime);
    }
	
	@Override
	public String toString() {
		return "["+currentDateTime().format(dtf)+"]";
	}

}
