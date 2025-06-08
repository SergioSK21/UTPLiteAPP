package com.example.utpliteapp


sealed class ScheduleItem {
    data class DayHeader(val day: String) : ScheduleItem()
    data class ClassItem(val schedule: ClassSchedule) : ScheduleItem()
}
