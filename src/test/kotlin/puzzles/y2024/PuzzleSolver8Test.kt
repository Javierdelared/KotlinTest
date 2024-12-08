package puzzles.y2024

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PuzzleSolver8Test {

    private val puzzleSolver = PuzzleSolver8()

    @Test
    fun testPuzzle81() {
        assertEquals(336, puzzleSolver.puzzle81())
    }

    @Test
    fun testPuzzle82() {
        assertEquals(1131, puzzleSolver.puzzle82())
    }
}