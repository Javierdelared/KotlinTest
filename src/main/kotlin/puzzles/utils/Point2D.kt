package puzzles.utils

data class Point2D(val x : Int, val y: Int) {
    fun move(d: Directions) = moveTimes(d, 1)
    fun move(m: Point2D) = moveTimes(m, 1)
    fun moveTimes(d: Directions, times: Int) = moveTimes(d.value, times)
    fun moveTimes(m: Point2D, times: Int) = Point2D(this.x + m.x * times, this.y + m.y * times)
    fun isInRange(limit: Point2D) = this.x >= 0 && this.y >= 0 && this.x <= limit.x && this.y <= limit.y
}

enum class Directions(val value : Point2D) {
    N(Point2D(0, -1)),
    NE(Point2D(1, -1)),
    E(Point2D(1, 0)),
    SE(Point2D(1, 1)),
    S(Point2D(0, 1)),
    SW(Point2D(-1, 1)),
    W(Point2D(-1, 0)),
    NW(Point2D(-1, -1));

    companion object {
        fun turnRight90(d: Directions) = when(d) {
            N -> E
            E -> S
            S -> W
            W -> N
            else -> throw NotImplementedError()
        }

        val ORTHOGONAL_DIRECTIONS = listOf(N, E, S, W)
        val DIAGONAL_DIRECTIONS = listOf(NW, NE, SE, SW)
    }
}

