package puzzles

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


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

    @Test
    fun rangeSum() {
        val arr = intArrayOf(1,2,3,4)
        assertEquals(13, puzzleSolver.rangeSum(arr, 4, 1, 5))
    }

    @Test
    fun sortedSquares() {
        val arr = intArrayOf(-4,-1,0,3,10)
        val expectedResult = intArrayOf(0,1,9,16,100)
        val result = puzzleSolver.sortedSquares(arr)
        (arr.indices).forEach{ i -> assertEquals(expectedResult[i], result[i]) }
    }
}