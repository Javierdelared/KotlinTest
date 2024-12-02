package puzzles.y2024

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PuzzleSolver2Test {

    private val puzzleSolver = PuzzleSolver2()

    @Test
    fun testPuzzle21() {
        assertEquals(252, puzzleSolver.puzzle21())
    }

    @Test
    fun testPuzzle22() {
        assertEquals(324, puzzleSolver.puzzle22())
    }

    @Test
    fun testPuzzle22Alt() {
        assertEquals(324, puzzleSolver.puzzle22Alt())
    }
}