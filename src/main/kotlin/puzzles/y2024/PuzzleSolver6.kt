package puzzles.y2024

import puzzles.utils.Directions
import puzzles.utils.Point2D
import puzzles.utils.FileUtils
import puzzles.utils.Vector2D

class PuzzleSolver6 {

    private var map = mutableMapOf<Point2D, Char>()
    private val guardInitialVector: Vector2D
    init {
        var guardPosition: Point2D? = null
        FileUtils.actOnMatrix("src/main/resources/2024/advent_file_6.txt") { pos, c ->
            if (c == GUARD) guardPosition = pos
            map[pos] = c
        }
        guardInitialVector = Vector2D(guardPosition!!, Directions.N)
    }

    fun puzzle61() = getVisitedVectors().map(Vector2D::pos).toSet().size

    fun puzzle62(): Int {
        val baseVisitedVectors = getVisitedVectors()
        val baseVisitedPositions = baseVisitedVectors.map(Vector2D::pos)
        return map.keys.count { obstructionPos ->
            val obstructionIndex = baseVisitedPositions.indexOf(obstructionPos)
            if (obstructionIndex > 0) {
                val visitedVectors = baseVisitedVectors.subList(0, obstructionIndex).toMutableSet()
                isLoop(obstructionPos, visitedVectors)
            } else false
        }
    }

    private fun getVisitedVectors(): MutableList<Vector2D> {
        var guardVector = guardInitialVector
        val visitedVectors = mutableListOf<Vector2D>()
        while (map[guardVector.pos] != null) {
            visitedVectors.add(guardVector)
            guardVector = moveGuard(guardVector)
        }
        return visitedVectors
    }

    private fun moveGuard(guardVector: Vector2D, obstructionPos: Point2D? = null) =
        guardVector.move().takeIf { map[it.pos] != OBSTRUCTION && it.pos != obstructionPos }
            ?: guardVector.turnRight90()

    private fun isLoop(
        obstructionPos: Point2D,
        visitedVectors: MutableSet<Vector2D>
    ): Boolean {
        var guardVector = visitedVectors.last()
        while (map[guardVector.pos] != null) {
            guardVector = moveGuard(guardVector, obstructionPos)
            if (!visitedVectors.add(guardVector)) return true
        }
        return false
    }

    companion object {
        private const val OBSTRUCTION = '#'
        private const val GUARD = '^'
    }
}