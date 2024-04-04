package com.pomodoro.controller

import com.pomodoro.service.PomodoroTimer
import org.springframework.web.bind.annotation.*
import com.pomodoro.service.TimerConfigService
import java.util.*
import kotlin.concurrent.schedule

@RestController
@RequestMapping("/timer")

class TimerController(private val timerConfigService: TimerConfigService,
                      private val pomodoroTimer: PomodoroTimer) {
    private var workTimer: Timer? = null
    private var breakTimer: Timer? = null
    @GetMapping("/start")
    fun startTimer(): String {
        val workTime = timerConfigService.getWorkTime() * 60 * 1000 // Convertendo minutos para milissegundos
        workTimer = Timer("WorkTimer", false)
        workTimer?.schedule(workTime) {
            // Lógica para quando o temporizador de trabalho terminar
            println("Temporizador de trabalho encerrado")
            pomodoroTimer.onWorkTimerFinished() // Chame o método na classe PomodoroTimer
        }
        return "Timer started with work time of ${timerConfigService.getWorkTime()} minutes"
    }

    @GetMapping("/pause")
    fun pauseTimer(): String {
        // Lógica para pausar o temporizador Pomodoro
        pomodoroTimer.pauseTimer()//pausa o cronometro
        return "Timer paused"
    }

    @GetMapping("/stop")
    fun stopTimer(): String {
        // Lógica para parar o temporizador Pomodoro
        pomodoroTimer.pauseTimer()//pausa o cronometro
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
