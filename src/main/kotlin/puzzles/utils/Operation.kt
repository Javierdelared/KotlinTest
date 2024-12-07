package puzzles.utils

enum class Operators(val action: (a: Long, b: Long) -> Long ) {
    ADD({ a, b -> a + b }),
    MULTIPLY({ a, b -> a * b }),
    SUBTRACT({ a, b -> a - b }),
    DIVIDE({ a, b -> a / b }),
    CONCAT({ a, b -> (a.toString() + b.toString()).toLong() })
}