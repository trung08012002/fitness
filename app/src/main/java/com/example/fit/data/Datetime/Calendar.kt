package com.example.fit.data.Datetime

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
class Calendar(){
    private var currentdate:LocalDate
    var choosedate:LocalDate
    init {
      currentdate=LocalDate.now()
      choosedate=currentdate
    }
    fun monthYearFromDate(date: LocalDate):String
    {
        val formatter:DateTimeFormatter = DateTimeFormatter.ofPattern("MMMM YYYY")
        return date.format(formatter)
    }
    fun daysInMonthArray(date:LocalDate) : ArrayList<String>{
        val daysInMonthArray: ArrayList<String> = ArrayList()
        val yearMonth: YearMonth = YearMonth.from(date)
        val daysInMonth = yearMonth.lengthOfMonth()
        val firstOfMonth: LocalDate = choosedate.withDayOfMonth(1)//lay ra ngay dau cua thang
        val dayOfWeek =
            firstOfMonth.dayOfWeek.value//lay ra ngay do trong 1 tuan la thu may voi gia tri int
        for (i in 1..42)
        {
            if(i<=dayOfWeek||i>daysInMonth+dayOfWeek)// daysInMonth+dayofweek+1-(dayofweek+1)
            {
                daysInMonthArray.add("")
            }
            else{
                daysInMonthArray.add((i+dayOfWeek).toString())
            }
        }
        return daysInMonthArray
    }
    fun previousMonth()
    {
        choosedate.minusMonths(1)
    }
    fun nextMonth()
    {
        choosedate.plusMonths(1)
    }
    fun nextYear()
    {
        choosedate.plusYears(1)
    }
    fun previousYear()
    {
        choosedate.minusYears(1)
    }
}