package com.pomodoro.service

import org.springframework.stereotype.Service

@Service
class TimerConfigService {
    private var workTime: Long = 25 // Tempo padrão de trabalho em minutos
    private var breakTime: Long = 5 // Tempo padrão de pausa em minutos

    fun getWorkTime(): Long {
        return workTime
    }

    fun setWorkTime(minutes: Long) {
        workTime = minutes
    }

    fun getBreakTime(): Long {
        return breakTime
    }

    fun setBreakTime(minutes: Long) {
        breakTime = minutes
    }
}
