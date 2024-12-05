package puzzles.y2024

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PuzzleSolver5Test {

    private val puzzleSolver = PuzzleSolver5()

    @Test
    fun testPuzzle51() {
        assertEquals(4996, puzzleSolver.puzzle51())
    }

    @Test
    fun testPuzzle52() {
        assertEquals(6311, puzzleSolver.puzzle52())
    }
}