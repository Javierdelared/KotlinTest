package puzzles.utils

data class Vector2D(val pos: Point2D, val dir: Directions) {
    fun move() = moveTimes(1)
    fun moveTimes(times: Int): Vector2D = Vector2D(pos.moveTimes(dir, times), dir)

    fun turnRight90() = Vector2D(pos, Directions.turnRight90(dir))
}

