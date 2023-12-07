package puzzles

import java.io.File

class PuzzleSolver1 {
    fun puzzle11(): Int {
        val regexDigits = "[1-9]".toRegex()
        File("src/main/resources/advent_file_1.txt").useLines {
            return it.toList().sumOf { l ->
                (regexDigits.find(l)!!.value + regexDigits.find(l.reversed())!!.value).toInt()
            }
        }
    }

    fun puzzle12(): Int {
        val mapOfDigits = mapOf("one" to "1", "two" to "2", "three" to "3", "four" to "4", "five" to "5",
            "six" to "6", "seven" to "7", "eight" to "8", "nine" to "9")
        val regexDigits = "[1-9]|one|two|three|four|five|six|seven|eight|nine".toRegex()
        val greedyRegexDigits = ".*([0-9]|one|two|three|four|five|six|seven|eight|nine)".toRegex()
        File("src/main/resources/advent_file_1.txt").useLines {
            return it.toList().sumOf { l ->
                        val first = regexDigits.find(l)!!.value
                        val last = greedyRegexDigits.find(l)!!.groups[1]!!.value
                        (mapOfDigits.getOrElse(first) { first } + mapOfDigits.getOrElse(last) { last }).toInt()
            }
        }
    }
}