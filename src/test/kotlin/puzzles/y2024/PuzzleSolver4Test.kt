package puzzles.y2024

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PuzzleSolver4Test {

    private val puzzleSolver = PuzzleSolver4()

    @Test
    fun testPuzzle41() {
        assertEquals(2551, puzzleSolver.puzzle41())
    }

    @Test
    fun testPuzzle42() {
        assertEquals(1985, puzzleSolver.puzzle42())
    }
}