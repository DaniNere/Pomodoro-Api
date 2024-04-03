package com.pomodoro.controller

import org.springframework.web.bind.annotation.*
import com.pomodoro.service.TimerConfigService

@RestController
@RequestMapping("/timer")
class TimerController(private val timerConfigService: TimerConfigService) {

    @GetMapping("/start")
    fun startTimer(): String {
        val workTime = timerConfigService.getWorkTime()
        // Lógica para iniciar o temporizador Pomodoro com o tempo de trabalho configurado
        return "Timer started with work time of $workTime minutes"
    }

    @GetMapping("/pause")
    fun pauseTimer(): String {
        // Lógica para pausar o temporizador Pomodoro
        return "Timer paused"
    }

    @GetMapping("/stop")
    fun stopTimer(): String {
        // Lógica para parar o temporizador Pomodoro
        return "Timer stopped"
    }

    @PostMapping("/work-time/{minutes}")
    fun setWorkTime(@PathVariable minutes: Long): String {
        timerConfigService.setWorkTime(minutes)
        return "Work time set to $minutes minutes"
    }

    @PostMapping("/break-time/{minutes}")
    fun setBreakTime(@PathVariable minutes: Long): String {
        timerConfigService.setBreakTime(minutes)
        return "Break time set to $minutes minutes"
    }
}
