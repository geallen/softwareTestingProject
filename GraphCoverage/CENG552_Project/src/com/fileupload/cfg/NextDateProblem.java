package com.fileupload.cfg;

public class NextDateProblem {
	public String getNextDate(String enteredDate) {

		String date = enteredDate;
		String nextDate = "";
		int entered_day, entered_month, entered_year = 0;
		if (date.length() == 10 && date.charAt(4) == '/' && date.charAt(7) == '/') {
			entered_year = Integer.parseInt(date.substring(0, 4));
			entered_month = Integer.parseInt(date.substring(5, 7));
			entered_day = Integer.parseInt(date.substring(8, 10));
			if (entered_day > 31 || entered_month > 12 || entered_year <= 0 || entered_day <= 0 || entered_month <= 0) {
				nextDate = "Invalid date format !!!";
			} else {
				if (entered_month == 02) {
					if (entered_year % 4 == 0) {
						if (entered_day < 29) {
							entered_day++;
							nextDate = String.valueOf(entered_year) + '/' + String.valueOf(entered_month) + '/'
									+ String.valueOf(entered_day);
						} else if (entered_day == 29) {
							entered_day = 01;
							entered_month = 03;
							nextDate = String.valueOf(entered_year) + '/' + String.valueOf(entered_month) + '/'
									+ String.valueOf(entered_day);
						} else {
							nextDate = "Invalid date. Day must be smaller than 30 !";
						}

					} else {
						if (entered_day < 28) {
							entered_day++;
							nextDate = String.valueOf(entered_year) + '/' + String.valueOf(entered_month) + '/'
									+ String.valueOf(entered_day);
						} else if (entered_day == 28) {
							entered_day = 01;
							entered_month = 03;
							nextDate = String.valueOf(entered_year) + '/' + String.valueOf(entered_month) + '/'
									+ String.valueOf(entered_day);
						} else {
							nextDate = "Invalid date. Day must be smaller than 29 !";
						}
					}
				} else if (entered_month == 1 || entered_month == 3 || entered_month == 5 || entered_month == 7
						|| entered_month == 8 || entered_month == 10 || entered_month == 12) {
					if (entered_day < 31) {
						entered_day++;
						nextDate = String.valueOf(entered_year) + '/' + String.valueOf(entered_month) + '/'
								+ String.valueOf(entered_day);
					} else {
						if (entered_month == 12) {
							entered_day = 01;
							entered_month = 01;
							entered_year++;
							nextDate = String.valueOf(entered_year) + '/' + String.valueOf(entered_month) + '/'
									+ String.valueOf(entered_day);
						} else {
							entered_day = 01;
							entered_month++;
							nextDate = String.valueOf(entered_year) + '/' + String.valueOf(entered_month) + '/'
									+ String.valueOf(entered_day);
						}
					}
				} else if (entered_month == 4 || entered_month == 6 || entered_month == 9 || entered_month == 11) {
					if (entered_day < 30) {
						entered_day++;
						nextDate = String.valueOf(entered_year) + '/' + String.valueOf(entered_month) + '/'
								+ String.valueOf(entered_day);
					} else if (entered_day == 30) {
						entered_day = 01;
						entered_month++;
						nextDate = String.valueOf(entered_year) + '/' + String.valueOf(entered_month) + '/'
								+ String.valueOf(entered_day);
					} else {
						nextDate = "Invalid date. Day must be smaller than 31 !";
					}
				}
			}
		} else {
			nextDate = "Invalid date format !!!";
		}
		return nextDate;
	}

}
