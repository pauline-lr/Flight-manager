
/*
dans le form : liste déroulante pour le jour le mois et l'année
	
*/
private int day, month, year; 

boolean isValidYear = year >= /*année en cours*/; 

boolean isLeapYear = (year%400 == 0) || ((year%100 != 0) && (year%4 == 0))

boolean isValid31daysMonth = (month == 1 || month == 3 || month == 5
						|| month == 7 ||month == 8 || month == 10
						|| month == 12) && (day >= 1 && day <= 31);

boolean isValid30daysMonth = (month == 4 || month == 6 || month == 9
						|| month == 11) && (day >= 1 && day <= 30);

boolean isValidFebruaryLeapYear = month == 2 && isLeapYear && day >= 1 & day <= 28;

boolean isValidNotFebruaryLeapYear = month == 2 && !isLeapYear && day >= 1 & day <= 29;

if(isValidYear && (isValid31daysMonth || isValid30daysMonth || 
					isValidFebruaryLeapYear || isValidNotFebruaryLeapYear)){
	System.out.println("Valid date");
}else{0
	System.out.println("Error : Invalid date");
}