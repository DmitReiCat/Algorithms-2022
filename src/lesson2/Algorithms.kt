@file:Suppress("UNUSED_PARAMETER")

package lesson2

import java.util.BitSet

/**
 * Получение наибольшей прибыли (она же -- поиск максимального подмассива)
 * Простая
 *
 * Во входном файле с именем inputName перечислены цены на акции компании в различные (возрастающие) моменты времени
 * (каждая цена идёт с новой строки). Цена -- это целое положительное число. Пример:
 *
 * 201
 * 196
 * 190
 * 198
 * 187
 * 194
 * 193
 * 185
 *
 * Выбрать два момента времени, первый из них для покупки акций, а второй для продажи, с тем, чтобы разница
 * между ценой продажи и ценой покупки была максимально большой. Второй момент должен быть раньше первого.
 * Вернуть пару из двух моментов.
 * Каждый момент обозначается целым числом -- номер строки во входном файле, нумерация с единицы.
 * Например, для приведённого выше файла результат должен быть Pair(3, 4)
 *
 * В случае обнаружения неверного формата файла бросить любое исключение.
 */
fun optimizeBuyAndSell(inputName: String): Pair<Int, Int> {
    TODO()
}

/**
 * Задача Иосифа Флафия.
 * Простая
 *
 * Образовав круг, стоят menNumber человек, пронумерованных от 1 до menNumber.
 *
 * 1 2 3
 * 8   4
 * 7 6 5
 *
 * Мы считаем от 1 до choiceInterval (например, до 5), начиная с 1-го человека по кругу.
 * Человек, на котором остановился счёт, выбывает.
 *
 * 1 2 3
 * 8   4
 * 7 6 х
 *
 * Далее счёт продолжается со следующего человека, также от 1 до choiceInterval.
 * Выбывшие при счёте пропускаются, и человек, на котором остановился счёт, выбывает.
 *
 * 1 х 3
 * 8   4
 * 7 6 Х
 *
 * Процедура повторяется, пока не останется один человек. Требуется вернуть его номер (в данном случае 3).
 *
 * 1 Х 3
 * х   4
 * 7 6 Х
 *
 * 1 Х 3
 * Х   4
 * х 6 Х
 *
 * х Х 3
 * Х   4
 * Х 6 Х
 *
 * Х Х 3
 * Х   х
 * Х 6 Х
 *
 * Х Х 3
 * Х   Х
 * Х х Х
 *
 * Общий комментарий: решение из Википедии для этой задачи принимается,
 * но приветствуется попытка решить её самостоятельно.
 */
fun josephTask(menNumber: Int, choiceInterval: Int): Int {
    TODO()
}

/**
 * Наибольшая общая подстрока.
 * Средняя
 *
 * Дано две строки, например ОБСЕРВАТОРИЯ и КОНСЕРВАТОРЫ.
 * Найти их самую длинную общую подстроку -- в примере это СЕРВАТОР.
 * Если общих подстрок нет, вернуть пустую строку.
 * При сравнении подстрок, регистр символов *имеет* значение.
 * Если имеется несколько самых длинных общих подстрок одной длины,
 * вернуть ту из них, которая встречается раньше в строке first.
 *
 * N - длинна 1го слова
 * M - длинна 2го слова
 *
 * R = O(N * M)
 * T = O(N * M)
 */
fun longestCommonSubstring(first: String, second: String): String {
    val matchTable = Array(first.length) { IntArray(second.length) { 0 } }

    var maxMatch = -1
    var firstMatchedAtEnd = -1

    for (i in first.indices) {
        for (j in second.indices) {
            if (first[i] == second[j]) {
                if (i == 0 || j == 0) matchTable[i][j] = 1
                else matchTable[i][j] = matchTable[i - 1][j - 1] + 1
                if (matchTable[i][j] > maxMatch) {
                    maxMatch = matchTable[i][j]; firstMatchedAtEnd = i
                }
            }
        }
    }
    return if (maxMatch == -1) "" else first.substring(firstMatchedAtEnd - maxMatch + 1, firstMatchedAtEnd + 1)
}

/**
 * Число простых чисел в интервале
 * Простая
 *
 * Рассчитать количество простых чисел в интервале от 1 до limit (включительно).
 * Если limit <= 1, вернуть результат 0.
 *
 * Справка: простым считается число, которое делится нацело только на 1 и на себя.
 * Единица простым числом не считается.
 *
 * R = O(N/2)
 * T = O(N *(ln(ln(N)))
 *
 */
fun calcPrimesNumber(limit: Int): Int {
    // Решето Эратосфена по не четным
    if (limit < 2) return 0
    val size = if (limit % 2 == 0) limit / 2 - 1 else limit / 2
    val primesList = BitSet(size)
    for (i in 0 until size) primesList[i] = true

    for (i in 3..limit step 2) {
        if (primesList[i / 2 - 1] && i < Int.MAX_VALUE / 2) {
            for (j in i + i..limit step i) if (j % 2 != 0) primesList[j / 2 - 1] = false
        }
    }

    var primeNumber = 1
    for (i in 0 until size) if (primesList[i]) primeNumber++
    return primeNumber

}
