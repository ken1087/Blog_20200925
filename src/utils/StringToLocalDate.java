package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StringToLocalDate {
	
	/**
	 * methodName:format
	 * @param target
	 * */
	public static LocalDate format(String target) {
		//String타입인 날짜를 LocalDate타입으로 변환하는 작업 
		LocalDate result = 
				LocalDate.parse(
						target, 
						DateTimeFormatter.
						ofPattern("yyyy-MM-dd"));
		return result;
	}
}
