package puzzles.y2024

import java.io.File

class PuzzleSolver3 {

    fun puzzle31() = REGEX_MUL.findAll(INSTRUCTIONS).sumOf { matchResult -> multiplyMatch(matchResult) }

    fun puzzle32() : Int {
        val validRanges = findValidRanges()
        return REGEX_MUL.findAll(INSTRUCTIONS).sumOf { matchResult ->
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
        REGEX_DO.findAll(INSTRUCTIONS).forEach { matchResult ->
            isDoIndex = if (matchResult.groups[1]!!.value == "don't()") {
                isDoIndex?.let { validRanges.add(it+1..<matchResult.range.first) }
                null
            } else {
                isDoIndex ?: matchResult.range.last
            }
        }
        isDoIndex?.let { validRanges.add(it+1..INSTRUCTIONS.length) }
        return validRanges
    }

    companion object {
        private var INSTRUCTIONS = File("src/main/resources/2024/advent_file_3.txt")
            .inputStream().readBytes().toString(Charsets.UTF_8)

        private val REGEX_MUL = Regex("mul\\(([1-9][0-9]{0,2}),([1-9][0-9]{0,2})\\)")
        private val REGEX_DO = Regex("(do\\(\\)|don't\\(\\))")
    }
}