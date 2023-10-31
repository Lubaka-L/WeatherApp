package com.example.weatherapptogit.data.enums

enum class DateFormat(val pattern: String) {
    NameOfDaysOfTheWeek("EEEE"),
    DayMonthYearTimeWithSeconds("dd-MM-yyyy HH:mm:ss"),
    DayMonthTimeWithSeconds("dd MMM HH:mm:ss"),
    HoursMinutes("HH:mm"),
    DayMonth("dd MMM")
}