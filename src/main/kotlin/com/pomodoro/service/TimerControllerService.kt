package com.pomodoro.service
import org.springframework.stereotype.Service
@Service
class TimerControllerService {

    private var workTimeInSeconds: Long = 25 * 60
    private var breakTimeInSeconds: Long = 5 * 60

    fun setWorkTime(minutes: Long) {
        workTimeInSeconds = minutes * 60
    }

    fun setBreakTime(minutes: Long) {
        breakTimeInSeconds = minutes * 60
    }

    fun getWorkTime(): Long {
        return workTimeInSeconds
    }

    fun getBreakTime(): Long {
        return breakTimeInSeconds
    }
}
