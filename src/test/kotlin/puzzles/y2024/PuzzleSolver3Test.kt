package puzzles.y2024

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PuzzleSolver3Test {

    private val puzzleSolver = PuzzleSolver3()

    @Test
    fun testPuzzle31() {
        assertEquals(170778545, puzzleSolver.puzzle31())
    }

    @Test
    fun testPuzzle32() {
        assertEquals(82868252, puzzleSolver.puzzle32())
    }
}