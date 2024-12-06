package puzzles.utils

import java.io.File

object Utils {
    fun getMatrix(fileName: String) = File(fileName).useLines { lns -> lns.toList() }

    fun mapMatrix(fileName: String) =
        getMatrix(fileName).mapIndexed { y, l ->
            l.mapIndexed { x, c ->
                Point2D(x, y) to c
            }
        }.flatten().toMap()
    fun actOnMatrix(fileName: String, action: (pos: Point2D, c: Char) -> Unit) =
        getMatrix(fileName).forEachIndexed { y, l ->
                l.forEachIndexed { x, c ->
                    action(Point2D(x, y), c)
                }
            }
}