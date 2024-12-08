package puzzles.y2024

import puzzles.utils.*

class PuzzleSolver8 {

    fun puzzle81() = countAntiNodes { antenna1, antenna2, antiNodes ->
            val antiNode = antenna1.moveTimes(antenna2.moveTimes(antenna1, -1), 2)
            if (antiNode.isInRange(LIMIT_POINT)) antiNodes.add(antiNode)
        }

    fun puzzle82() = countAntiNodes { antenna1, antenna2, antiNodes ->
            val distance = antenna2.moveTimes(antenna1, -1)
            var antiNode = antenna2
            do {
                antiNodes.add(antiNode)
                antiNode = antiNode.move(distance)
            } while (antiNode.isInRange(LIMIT_POINT))
        }

    private fun countAntiNodes(action: (a1: Point2D, a2: Point2D, antiNodes: MutableSet<Point2D>) -> Unit): Int {
        val antiNodes = mutableSetOf<Point2D>()
        ANTENNAS_GROUPS.values.forEach { antennasGroup ->
            antennasGroup.forEach { a1 ->
                antennasGroup.filter { a2 -> a1 != a2 }.forEach { a2 ->
                    action(a1, a2, antiNodes)
                }
            }
        }
        return antiNodes.size
    }

    companion object {
        private const val EMPTY_SPACE = '.'
        private val ANTENNAS_GROUPS = FileUtils.mapMatrix("src/main/resources/2024/advent_file_8.txt")
            .entries.filter { pos -> pos.value != EMPTY_SPACE }.groupBy({ a -> a.value }, { a -> a.key })
        private val LIMIT_POINT = FileUtils.getLimitPoint("src/main/resources/2024/advent_file_8.txt")
    }
}