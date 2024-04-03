package com.pomodoro.service
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

class PomodoroTimer(private val callback: () -> Unit) {
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
    fun onWorkTimerFinished() {
        // Lógica para ser executada quando o temporizador de trabalho terminar
        println("Work timer finished")
    }

    fun pauseTimer() {
        timer?.shutdown()
    }

    private fun updateTimer() {
        remainingTimeSeconds--
        if (remainingTimeSeconds <= 0) {
            callback.invoke() // Notifica quando o temporizador chega a zero
            timer?.shutdown() // Encerra o temporizador
        }
    }
}

fun main() {
    val pomodoroTimer = PomodoroTimer {
        println("Tempo de trabalho concluído!")
    }
    pomodoroTimer.startTimer(25) // Inicia o temporizador com 25 minutos
}
