package puzzles.y2024

import org.junit.jupiter.api.Test

class PuzzleSolver1Test{
    private val puzzleSolver = PuzzleSolver1()

    @Test
    fun testPuzzle11() {
        kotlin.test.assertEquals(2815556, puzzleSolver.puzzle11())
    }

    @Test
    fun testPuzzle12() {
        kotlin.test.assertEquals(23927637, puzzleSolver.puzzle12())
    }
}