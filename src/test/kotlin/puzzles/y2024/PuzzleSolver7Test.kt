package puzzles.y2024

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PuzzleSolver7Test {

    private val puzzleSolver = PuzzleSolver7()

    @Test
    fun testPuzzle71() {
        assertEquals(2314935962622, puzzleSolver.puzzle71())
    }

    @Test
    fun testPuzzle72() {
        assertEquals(401477450831495, puzzleSolver.puzzle72())
    }
}