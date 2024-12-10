package puzzles.y2024

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PuzzleSolver10Test {

    private val puzzleSolver = PuzzleSolver10()

    @Test
    fun testPuzzle101() {
        assertEquals(796, puzzleSolver.puzzle101())
    }

    @Test
    fun testPuzzle102() {
        assertEquals(1942, puzzleSolver.puzzle102())
    }
}