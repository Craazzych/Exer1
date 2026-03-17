package com.example.console
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis
import kotlin.random.Random

fun main() = runBlocking {
    val time = measureTimeMillis {
        try {
            val users = async { loadUsers() }
            val sales = async { loadSales() }
            val weather = async { loadWeather() }

            println("Users: ${users.await()}")
            println("Sales: ${sales.await()}")
            println("Weather: ${weather.await()}")

        } catch (e: Exception) {
            println("Ошибка: ${e.message}")
        }
    }

    println("Execution time: ${time / 1000.0} sec")
}

suspend fun loadUsers(): List<String> {
    delay(1800)
    if (Random.nextBoolean()) throw Exception("Ошибка загрузки пользователей")
    return listOf("Alice", "Bob", "Ivan", "Olga")
}

suspend fun loadSales(): Map<String, Int> {
    delay(1200)
    if (Random.nextBoolean()) throw Exception("Ошибка загрузки продаж")
    return mapOf("Coffee" to 1680, "Tea" to 475)
}

suspend fun loadWeather(): List<String> {
    delay(2500)
    if (Random.nextBoolean()) throw Exception("Ошибка загрузки погоды")
    return listOf("Москва: -3°C", "NY: -5°C", "Tokyo: 11°C")
}