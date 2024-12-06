package puzzles.y2024

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PuzzleSolver6Test {

    private val puzzleSolver = PuzzleSolver6()

    @Test
    fun testPuzzle61() {
        assertEquals(5404, puzzleSolver.puzzle61())
    }

    @Test
    fun testPuzzle62() {
        assertEquals(1984, puzzleSolver.puzzle62())
    }
}