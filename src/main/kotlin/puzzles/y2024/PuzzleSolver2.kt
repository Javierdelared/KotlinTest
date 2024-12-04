package puzzles.y2024

import org.slf4j.LoggerFactory
import java.io.File

class PuzzleSolver2 {

    private val logger =  LoggerFactory.getLogger(javaClass)

    fun puzzle21() = REPORTS.count { report -> isSafe(report) }
    fun puzzle22() = REPORTS.count { report -> isSafeWithSkip(report) }
    fun puzzle22Alt() = REPORTS.count { report -> isSafeWithSkipAlt(report) }

    private fun isSafe(report: List<Int>): Boolean {
        if (report.size < 2) return true
        val direction = report[1].compareTo(report[0])
        return (1..<report.size).all { isInRange(direction, report, it, it-1) }.also {
            logger.info("Report {} safety is {}", report, it)
        }
    }

    private fun isInRange(d: Int, report: List<Int>, i: Int, j: Int) = d * (report[i] - report[j]) in 1..3

    private fun isSafeWithSkip(report: List<Int>): Boolean {
        if (report.size < 3) return true
        return report.indices.any { skipIndex -> isSafeWithSkip(report, skipIndex) }.also {
            logger.info("Report {} safety is {}", report, it)
        }
    }

    private fun isSafeWithSkip(
        report: List<Int>,
        skipIndex: Int
    ) : Boolean {
        val direction = when (skipIndex) {
            0 -> report[2].compareTo(report[1])
            1 -> report[2].compareTo(report[0])
            else -> report[1].compareTo(report[0])
        }
        return (1..<report.size).all { isInRangeWithSkipped(direction, report, it, skipIndex) }
    }

    private fun isInRangeWithSkipped(d: Int, report: List<Int>, i: Int, skipIndex: Int) : Boolean {
        if (i == skipIndex) return true
        var j = i - 1
        if (j == skipIndex) {
            j--
        }
        if (j < 0) return true
        return isInRange(d, report, i, j)
    }

    private fun isSafeWithSkipAlt(report: List<Int>): Boolean {
        if (report.size < 3) return true
        val directions = listOf(-1, 1)
        return directions.any { isSafeWithSkipAlt(report, it) }.also {
            logger.info("Report {} safety is {}", report, it)
        }
    }
    private fun isSafeWithSkipAlt(report: List<Int>, direction : Int): Boolean {
        val failIndexes = mutableSetOf<Int>()
        (1..< report.size).forEach {
            if (!isInRange(direction, report, it, it-1)) {
                failIndexes.add(it-1)
                failIndexes.add(it)
            }
            if (failIndexes.size > 3) return false
        }
        return when(failIndexes.size) {
            0 -> true
            2 -> {
                if (listOf(0, report.size-1).any { failIndexes.contains(it) }) return true
                failIndexes.forEach {failIndex ->
                    if (isInRange(direction, report, failIndex+1, failIndex-1)) return true
                }
                return false
            }
            3 -> failIndexes.sorted()[1].let { isInRange(direction, report, it+1, it-1) }
            else -> false
        }
    }

    companion object {
        private val REPORTS : List<List<Int>> = File("src/main/resources/2024/advent_file_2.txt")
            .useLines { lns ->
                lns.toList().map { line -> line.split(" ").map { it.toInt() }.toList() }
            }
    }
}