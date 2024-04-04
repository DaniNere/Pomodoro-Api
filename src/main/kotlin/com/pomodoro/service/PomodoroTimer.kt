package com.pomodoro.service

import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit
import org.springframework.stereotype.Component

@Component
class PomodoroTimer {
    private var remainingTimeSeconds: Long = 0
    private var timer: ScheduledExecutorService? = null

    fun startTimer(minutes: Long) {
        remainingTimeSeconds = minutes * 60
        timer = Executors.newSingleThreadScheduledExecutor()
        timer?.scheduleAtFixedRate(
            { updateTimer() },
            0, // Inicializa a contagem regressiva imediatamente
            1, // Intervalo de 1 segundo
            TimeUnit.SECONDS
        )
    }

    private var callback: (() -> Unit)? = null

    fun setCallback(callback: () -> Unit) {
        this.callback = callback
    }

    fun onWorkTimerFinished() {
        println("Work timer finished")
    }

    fun pauseTimer() {
        timer?.shutdown()
    }

    private fun updateTimer() {
        remainingTimeSeconds--
        val remainingMinutes = remainingTimeSeconds / 60
        println("Remaining Time: $remainingMinutes minutes")
        if (remainingTimeSeconds <= 0) {
            callback?.invoke() // Notifica quando o temporizador chega a zero
            timer?.shutdown() // Encerra o temporizador
        }
    }
}

fun main() {
    val pomodoroTimer = PomodoroTimer()
    pomodoroTimer.setCallback {
        println("Tempo de trabalho concluído!")
    }
    pomodoroTimer.startTimer(25) // Inicia o temporizador com 25 minutos
}
