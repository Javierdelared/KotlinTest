package puzzles.y2024

import java.io.File
import kotlin.math.abs

class PuzzleSolver1 {
    fun puzzle11(): Int {
        val (leftList, rightList) = getLists()
        val sortedLeftList = leftList.sorted()
        val sortedRightList = rightList.sorted()
        return sortedLeftList.indices.sumOf {
            abs(sortedLeftList[it] - sortedRightList[it])
        }
    }

    fun puzzle12(): Int {
        val (leftList, rightList) = getLists()
        val rightListMap = mutableMapOf<Int, Int>()
        rightList.forEach {
            rightListMap[it] = rightListMap.getOrDefault(it, 0) + 1
        }
        return leftList.sumOf {
            it * rightListMap.getOrDefault(it, 0)
        }
    }

    private fun getLists(): Pair<MutableList<Int>, MutableList<Int>> {
        val leftList = mutableListOf<Int>()
        val rightList = mutableListOf<Int>()
        File("src/main/resources/2024/advent_file_1.txt").useLines { lines ->
            lines.toList().forEach { line ->
                line.split("   ").let {
                    leftList.add(it[0].toInt())
                    rightList.add(it[1].toInt())
                }
            }
        }
        return Pair(leftList, rightList)
    }
}