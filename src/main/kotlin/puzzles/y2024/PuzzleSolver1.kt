package puzzles.y2024

import java.io.File
import kotlin.math.abs

class PuzzleSolver1 {

    private var leftList = mutableListOf<Int>()
    private var rightList = mutableListOf<Int>()

    init {
        File("src/main/resources/2024/advent_file_1.txt").useLines { lns ->
            lns.toList().forEach { line ->
                line.split("   ").let {
                    leftList.add(it[0].toInt())
                    rightList.add(it[1].toInt())
                }
            }
        }
    }
    fun puzzle11(): Int {
        val sortedLeftList = leftList.sorted()
        val sortedRightList = rightList.sorted()
        return sortedLeftList.indices.sumOf {
            abs(sortedLeftList[it] - sortedRightList[it])
        }
    }

    fun puzzle12(): Int {
        val rightListMap = mutableMapOf<Int, Int>()
        rightList.forEach {
            rightListMap[it] = rightListMap.getOrDefault(it, 0) + 1
        }
        return leftList.sumOf {
            it * rightListMap.getOrDefault(it, 0)
        }
    }
}