package puzzles

import kotlin.test.Test
import kotlin.test.assertEquals

class PuzzleSolver1Test {

    private val puzzleSolver = PuzzleSolver1()

    @Test
    fun testPuzzle11() {
        assertEquals(54605, puzzleSolver.puzzle11())
    }

    @Test
    fun testPuzzle12() {
        assertEquals(55429, puzzleSolver.puzzle12())
    }
}