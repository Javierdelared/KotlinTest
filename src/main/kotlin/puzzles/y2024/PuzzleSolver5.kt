package puzzles.y2024

import org.slf4j.LoggerFactory
import java.io.File

class PuzzleSolver5 {

    private val logger =  LoggerFactory.getLogger(javaClass)

    private var rules = mutableMapOf<Int, MutableSet<Int>>()
    private var updates = mutableListOf<List<Int>>()
    init {
         File("src/main/resources/2024/advent_file_5.txt")
            .useLines { lns ->
                lns.toList().map { l ->
                    if (l.contains(RULE_SEPARATOR)) {
                        val rule = split(l, RULE_SEPARATOR)
                        if (rules.containsKey(rule[0])) {
                            rules[rule[0]]?.add(rule[1])
                        } else {
                            rules[rule[0]] = mutableSetOf(rule[1])
                        }
                    }
                    if (l.contains(UPDATE_SEPARATOR)) {
                        updates.add(split(l, UPDATE_SEPARATOR))
                    }
                }
            }
    }

    fun puzzle51() = updates.filter { update ->
        isValidUpdate(update)
    }.sumOf { update ->
        getMiddlePage(update)
    }

    fun puzzle52() = updates.filter { update ->
        !isValidUpdate(update)
    }.sumOf { update ->
        getMiddlePage(orderUpdate(update))
    }

    private fun isValidUpdate(update: List<Int>) : Boolean {
        update.forEachIndexed { i, pageBefore ->
            rules[pageBefore]?.forEach { pageAfter ->
                if (update.subList(0, i).contains(pageAfter)) {
                    logger.debug("Invalid update {}", update)
                    return false
                }
            }
        }
        logger.debug("Valid update {}", update)
        return true
    }

    private fun getMiddlePage(update: List<Int>) : Int {
        assert(update.size % 2 != 0) { "Update $update has no middle page" }
        return update[(update.size - 1) / 2]
    }

    private fun orderUpdate(
        update: List<Int>
    ): List<Int> {
        val mapPagePosition = update.mapIndexed { i, p -> p to i }.toMap().toMutableMap()
        logger.debug("Ordering update {}", update)
        while (orderUpdate(mapPagePosition)) { logger.debug("Reordering update...") }
        return mapPagePosition.entries.sortedBy { e -> e.value }.map { e -> e.key }.toList().also {
            logger.debug("Ordered update: {}", it)
        }
    }

    private fun orderUpdate(
        mapPagePosition: MutableMap<Int, Int>
    ): Boolean {
        var hasChange = false
        rules.filter { (firstPage, _) ->
            mapPagePosition.keys.contains(firstPage)
        }.forEach { (firstPage, secondPageList) ->
            secondPageList.filter { secondPage ->
                mapPagePosition.keys.contains(secondPage)
            }.forEach { secondPage ->
                if (applyRule(mapPagePosition, firstPage, secondPage)) hasChange = true
            }
        }
        return hasChange
    }

    private fun applyRule(
        mapPagePosition: MutableMap<Int, Int>,
        firstPage: Int,
        secondPage: Int
    ) : Boolean {
        val firstPos = mapPagePosition[firstPage]!!
        val secondPos = mapPagePosition[secondPage]!!
        return if (firstPos > secondPos) {
            logger.debug("Swapping positions $firstPos, $secondPos according to rule $firstPage|$secondPage")
            mapPagePosition[firstPage] = secondPos
            mapPagePosition[secondPage] = firstPos
            true
        } else false
    }

    companion object {
        private const val RULE_SEPARATOR = "|"
        private const val UPDATE_SEPARATOR = ","
        private fun split(l: String, separator: String) = l.split(separator).map(String::toInt)
    }
}