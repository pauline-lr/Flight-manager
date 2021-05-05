
private int day, month, year; 

boolean isValidYear = year >= Calendar.TODAY; 

boolean isLeapYear = (year%400 == 0) || ((year%100 != 0) && (year%4 == 0))

boolean isValid31daysMonth = (month == 1 || month == 3 || month == 5
						|| month == 7 ||month == 8 || month == 10
						|| month == 12) && (day >= 1 && day <= 31);

boolean isValid30daysMonth = (month == 4 || month == 6 || month == 9
						|| month == 11) && (day >= 1 && day <= 30);

boolean isValidFebruaryLeapYear = month == 2 && isLeapYear && day >= 1 & j <= 28;

boolean isValidNotFebruaryLeapYear = month == 2 && !isLeapYear && day >= 1 & j <= 28;

if(isValidYear && (is31daysMonth || is30daysMonth || )){
	System.out.println("Valid date");
}else{
	System.out.println("Error : Invalid date");
}