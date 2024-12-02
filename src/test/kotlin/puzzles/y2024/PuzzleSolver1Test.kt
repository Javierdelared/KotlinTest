package puzzles.y2024

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PuzzleSolver1Test {

    private val puzzleSolver = PuzzleSolver1()

    @Test
    fun testPuzzle11() {
       assertEquals(2815556, puzzleSolver.puzzle11())
    }

    @Test
    fun testPuzzle12() {
        assertEquals(23927637, puzzleSolver.puzzle12())
    }
}