package puzzles.y2024

import puzzles.utils.*

class PuzzleSolver7 {

    fun puzzle71() = EQUATIONS.filter { equation ->
            isValidEquation(equation.first, equation.second, OPERATORS_PUZZLE_1)
        }.sumOf { it.first }

    fun puzzle72() = EQUATIONS.filter { equation ->
            isValidEquation(equation.first, equation.second, OPERATORS_PUZZLE_2)
        }.sumOf { it.first }

    private fun isValidEquation(testResult: Long, numbers: List<Long>, operators: List<Operators>): Boolean {
        if (numbers.size == 1) return testResult == numbers[0]
        if (numbers[0] > testResult) return false
        return operators.any { op ->
            val newNumbers = mutableListOf(op.action(numbers[0], numbers[1]))
            if (numbers.size > 2) newNumbers.addAll(numbers.subList(2, numbers.size))
            isValidEquation(testResult, newNumbers, operators)
        }
    }

    companion object {
        val EQUATIONS = FileUtils.getMatrix("src/main/resources/2024/advent_file_7.txt").map { equation ->
            equation.split(": ").let { equationSides ->
                Pair(equationSides[0].toLong(), equationSides[1].split(" ").map(String::toLong))
            }
        }

        private val OPERATORS_PUZZLE_1 = listOf(Operators.ADD, Operators.MULTIPLY)
        private val OPERATORS_PUZZLE_2 = listOf(Operators.ADD, Operators.MULTIPLY, Operators.CONCAT)
    }
}