// Необходимо составить программу, которая помогает пользователю составить план поезда.
// После запуска программа спрашивает пользователя - хочет ли он закончить работу, или составить поезд

import kotlin.random.Random

// Основная функция программы
fun main() {
    println("Добро пожаловать в программу по созданию плана поезда!")

    // Флаг для завершения программы
    var exitRequested = false

    // Цикл для взаимодействия с пользователем
    while (!exitRequested) {
        println("Выберите действие:")
        println("1. Создать направление")
        println("2. Продать билеты")
        println("3. Сформировать поезд")
        println("4. Отправить поезд")
        println("5. Завершить программу")

        // Чтение ввода пользователя и обработка выбора
        when (readLine()?.toUpperCase()) {
            "1" -> createDirection()
            "2" -> sellTickets()
            "3" -> formTrain()
            "4" -> sendTrain()
            "5" -> exitRequested = true
            else -> println("Некорректный ввод. Пожалуйста, выберите действие из списка.")
        }
    }
}

// Переменные для хранения состояния программы
var direction: String? = null // при создании переменной она null, после выполнения createDirection становится string
var passengers: Int = 0
var trainCapacity: Int = 0
var trainWagons: MutableList<Int> = mutableListOf()
var CapacityWagons: MutableList<Int> = mutableListOf()

// Создание направления (города отправления и прибытия)
fun createDirection() {
    val cities = listOf("Бийск", "Барнаул", "Москва", "Санкт-Петербург", "Ростов-на-Дону", "Владимир", "Новосибирск", "Сочи", "Красноярск", "Краснодар", "Чебоксары", "Йошкар-Ола", "Владивосток", "Рязань", "Пермь")
    val fromCity = cities.random()
    var toCity = cities.random()
    while (fromCity == toCity) {
        toCity = cities.random()
    }
    direction = "$fromCity - $toCity"
    println("Направление создано: $direction")
}

// Продажа билетов (генерация случайного количества пассажиров)
fun sellTickets() {
    passengers = Random.nextInt(5, 202)
    println("Продано билетов: $passengers")
}

// Формирование поезда (генерация вместимости вагонов и распределение пассажиров)
fun formTrain() {
    trainWagons.clear()
    var remainingPassengers = passengers

    // Работает до тех пор пока все пассажиры не будут зассажены в вагоны. С кажым проходом цикла делается один вагон
    while (remainingPassengers > 0) {
        val wagonCapacity = Random.nextInt(1, 26) // рандомно создается вместимость пассажиров в вагоне
        // Распределение пассажиров вагоне, обычно работает первая скобка,
        // вторая нужна для тех случаев когда оставшихся пассажиров меньше, чем вместимость вагона строчкой выше
        val passengersInWagon = if (remainingPassengers >= wagonCapacity) wagonCapacity else remainingPassengers
        CapacityWagons.add(wagonCapacity)// записывает вместимость вагона в mutable list
        trainWagons.add(passengersInWagon)// в mutable list записывается int значение, означающее кол-во пассажиров в вагоне
        remainingPassengers -= passengersInWagon // вычетается из общего кол-во пассажиров, распределеной в этом вагоне
    }

    trainCapacity = trainWagons.size

    // Вывод информации о поезде с учетом слова "вагон"
    if ((trainCapacity == 1) || (trainCapacity == 21))
    {
        println("Поезд сформирован: $trainCapacity вагон")
    }
    else if ((trainCapacity == 2) || (trainCapacity == 3) || (trainCapacity == 4)|| (trainCapacity == 22)|| (trainCapacity== 23)|| (trainCapacity == 24)) {
        println("Поезд сформирован: $trainCapacity вагона")
    }
    else
        println("Поезд сформирован: $trainCapacity вагонов")
}

// Отправка поезда (вывод информации о направлении, количестве вагонов и пассажирах в каждом вагоне)
fun sendTrain() {
    // Проверка, что направление и поезд были созданы
    if (direction != null && trainCapacity > 0) {
        println("Поезд $direction, состоящий из $trainCapacity вагонов, отправлен.")
        for ((index, capacity) in trainWagons.withIndex()) {
            println("Вагон ${index + 1}: вместимость ${CapacityWagons[index]}, пассажиров $capacity")
        }
    } else {
        println("Направление или поезд не были созданы. Пожалуйста, выполните предыдущие шаги.")
    }
}
