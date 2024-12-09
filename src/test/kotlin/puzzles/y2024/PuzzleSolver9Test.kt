package puzzles.y2024

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PuzzleSolver9Test {

    private val puzzleSolver = PuzzleSolver9()

    @Test
    fun testPuzzle91() {
        assertEquals(6359213660505, puzzleSolver.puzzle91())
    }

    @Test
    fun testPuzzle92() {
        assertEquals(6381624803796, puzzleSolver.puzzle92())
    }
}