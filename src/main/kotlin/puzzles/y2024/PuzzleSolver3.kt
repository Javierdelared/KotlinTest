package puzzles.y2024

import java.io.File

class PuzzleSolver3 {

    fun puzzle31() = regexMul.findAll(instructions).sumOf { matchResult -> multiplyMatch(matchResult) }

    fun puzzle32() : Int {
        val validRanges = findValidRanges()
        return regexMul.findAll(instructions).sumOf { matchResult ->
            if (validRanges.any { range -> range.contains(matchResult.range.first) }) {
                multiplyMatch(matchResult)
            } else 0
        }
    }

    private fun multiplyMatch(matchResult: MatchResult) =
        matchResult.groups[1]!!.value.toInt() * matchResult.groups[2]!!.value.toInt()

    private fun findValidRanges() : List<IntRange> {
        val validRanges = mutableListOf<IntRange>()
        var isDoIndex : Int? = 0
        regexDo.findAll(instructions).forEach { matchResult ->
            isDoIndex = if (matchResult.groups[1]!!.value == "don't()") {
                isDoIndex?.let { validRanges.add(it+1..<matchResult.range.first) }
                null
            } else {
                isDoIndex ?: matchResult.range.last
            }
        }
        isDoIndex?.let { validRanges.add(it+1..instructions.length) }
        return validRanges
    }

    companion object {
        private var instructions : String = File("src/main/resources/2024/advent_file_3.txt")
            .inputStream().readBytes().toString(Charsets.UTF_8)

        private val regexMul = Regex("mul\\(([1-9][0-9]{0,2}),([1-9][0-9]{0,2})\\)")
        private val regexDo = Regex("(do\\(\\)|don't\\(\\))")
    }
}