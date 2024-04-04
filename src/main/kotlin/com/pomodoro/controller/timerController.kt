package com.pomodoro.controller

import com.pomodoro.service.PomodoroTimer
import com.pomodoro.service.TimerConfigService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/timer")
class TimerController(
    private val timerConfigService: TimerConfigService,
    private val pomodoroTimer: PomodoroTimer
) {

    @GetMapping("/start")
    fun startTimer(): String {
        val workTime = timerConfigService.getWorkTime()
        pomodoroTimer.startTimer(workTime)
        return "Timer started with work time of $workTime minutes"
    }

    @GetMapping("/pause")
    fun pauseTimer(): String {
        pomodoroTimer.pauseTimer()
        return "Timer paused"
    }

    @GetMapping("/stop")
    fun stopTimer(): String {
        pomodoroTimer.pauseTimer()
        return "Timer stopped"
    }

    @PostMapping("/work-time/{minutes}")
    fun setWorkTime(@PathVariable minutes: Long): String {
        timerConfigService.setWorkTime(minutes)
        return "Work time set to $minutes minutes"
    }

    @PostMapping("/break-time/{minutes}")
    fun setBreakTime(@PathVariable minutes: Long): String {
        // Lógica para definir o tempo do breakTimer, se necessário
        return "Break time set to $minutes minutes"
    }
}
