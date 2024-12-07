package puzzles.y2024

import puzzles.utils.Directions
import puzzles.utils.Point2D
import puzzles.utils.FileUtils

class PuzzleSolver4 {

    fun puzzle41() = MATRIX.entries.filter { e ->
            e.value == SEARCH_WORD[0]
        }.sumOf { e ->
            Directions.entries.count { d ->
                var nextPoint = e.key
                (1..<SEARCH_WORD.length).all {
                    nextPoint = nextPoint.move(d)
                    MATRIX[nextPoint] == SEARCH_WORD[it]
                }
            }
        }

    fun puzzle42() = MATRIX.entries.filter { e ->
        e.value == SEARCH_WORD_2[1]
    }.count { e ->
        Directions.DIAGONAL_DIRECTIONS.count { d ->
            MATRIX[e.key.move(d)] == SEARCH_WORD_2[0] && MATRIX[e.key.moveTimes(d, -1)] == SEARCH_WORD_2[2]
        } == 2
    }

    companion object {
        private val MATRIX : Map<Point2D, Char> = FileUtils.mapMatrix("src/main/resources/2024/advent_file_4.txt")

        private const val SEARCH_WORD = "XMAS"
        private const val SEARCH_WORD_2 = "MAS"
    }
}