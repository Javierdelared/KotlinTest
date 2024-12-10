package puzzles.y2024

import puzzles.utils.Directions
import puzzles.utils.FileUtils
import puzzles.utils.Point2D

class PuzzleSolver10 {

    fun puzzle101() = TRAILHEADS.sumOf { trailHead ->
            val trails = mutableSetOf<Point2D>()
            findTrails(trails, trailHead)
            trails.size
        }

    fun puzzle102() = TRAILHEADS.sumOf { trailHead ->
        findTrailScore(trailHead)
    }

    private fun findTrails(trails: MutableSet<Point2D>, pos: Map.Entry<Point2D, Char>) {
        if (pos.value == '9') trails.add(pos.key)
        Directions.ORTHOGONAL_DIRECTIONS.forEach { d ->
            val nextPos = TOPOGRAPHIC_MAP[pos.key.move(d)]
            if (nextPos!= null && nextPos.value.code - pos.value.code == 1) findTrails(trails, nextPos)
        }
    }

    private fun findTrailScore(pos: Map.Entry<Point2D, Char>): Int {
        if (pos.value == '9') return 1
        return Directions.ORTHOGONAL_DIRECTIONS.sumOf { d ->
            val nextPos = TOPOGRAPHIC_MAP[pos.key.move(d)]
            if (nextPos!= null && nextPos.value.code - pos.value.code == 1) findTrailScore(nextPos) else 0
        }
    }


    companion object {
        private val TOPOGRAPHIC_MAP = FileUtils.mapMatrix("src/main/resources/2024/advent_file_10.txt")
            .entries.associateBy { it.key }
        private val TRAILHEADS = TOPOGRAPHIC_MAP.values.filter { entry -> entry.value == '0' }
    }
}