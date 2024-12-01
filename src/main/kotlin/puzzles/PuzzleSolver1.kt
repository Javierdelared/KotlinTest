package puzzles

import java.io.File

class PuzzleSolver1 {
    fun puzzle11(): Int {
        val regexDigits = "[1-9]".toRegex()
        File("src/main/resources/advent_file_1.txt").useLines {
            return it.toList().sumOf { l ->
                (regexDigits.find(l)!!.value + regexDigits.find(l.reversed())!!.value).toInt()
            }
        }
    }

    fun puzzle12(): Int {
        val mapOfDigits = mapOf("one" to "1", "two" to "2", "three" to "3", "four" to "4", "five" to "5",
            "six" to "6", "seven" to "7", "eight" to "8", "nine" to "9")
        val regexDigits = "[1-9]|one|two|three|four|five|six|seven|eight|nine".toRegex()
        val greedyRegexDigits = ".*([0-9]|one|two|three|four|five|six|seven|eight|nine)".toRegex()
        File("src/main/resources/advent_file_1.txt").useLines {
            return it.toList().sumOf { l ->
                        val first = regexDigits.find(l)!!.value
                        val last = greedyRegexDigits.find(l)!!.groups[1]!!.value
                        (mapOfDigits.getOrElse(first) { first } + mapOfDigits.getOrElse(last) { last }).toInt()
            }
        }
    }

    fun rotate(matrix: Array<IntArray>) {
        val max = matrix.size -1
        val mid = matrix.size/2
        (0..mid).forEach { x ->
            (0..<mid).forEach { y ->
                val c1 = matrix[y][x]
                matrix[y][x] = matrix[max-x][y]
                matrix[max-x][y] = matrix[max-y][max-x]
                matrix[max-y][max-x] = matrix[x][max-y]
                matrix[x][max-y] = c1
            }
        }
    }

    fun maxNumberOfBalloons(text: String): Int {
        val balloonCharIndex = mutableMapOf('b' to 0, 'a' to 1, 'l' to 2, 'o' to 3, 'n' to 4)
        val counter = IntArray(5) { 0 }
        text.forEach { c -> if (balloonCharIndex[c] != null) counter[balloonCharIndex[c]!!]++ }
        return (0..4).minOf { i -> if (i == 2 || i == 3) counter[i] / 2 else counter[i] }
    }
    fun isValidSudoku(board: Array<CharArray>): Boolean {
        val processedCells = mutableSetOf<Char>()
        (0..8).forEach {i ->
            // Check Rows
            (0..8).forEach { j -> if(repeatedCell(board[i][j], processedCells)) return false }
            processedCells.clear()
            // Check Columns
            (0..8).forEach { j -> if(repeatedCell(board[j][i], processedCells)) return false }
            processedCells.clear()
            // Check Sub-boxes
            val x1 = (i % 3) * 3
            val y1 = (i / 3) * 3
            (0..8).forEach { j -> if(repeatedCell(board[y1 + j / 3][x1 + j % 3], processedCells)) return false }
            processedCells.clear()
        }
        return true
    }
    private fun repeatedCell(cell: Char, processedCells: MutableSet<Char>): Boolean = cell != '.' && !processedCells.add(cell)

    fun groupAnagrams(strs: Array<String>): List<List<String>> = strs.groupBy { str -> str.groupingBy { it }.eachCount() }.values.toList()
    fun rangeSum(nums: IntArray, n: Int, left: Int, right: Int): Int {
        // Build sub-array starting positions (0, n-1, n-1 + n-2,  n-1 + n-2 + n-3...)
        val sp = IntArray(n)
        (0..<n-1).forEach { i -> sp[i+1] = sp[i] + n - i }
        // Build continuous array
        val arr = IntArray(n * (n + 1) / 2)
        // Add last triangle to array
        arr[sp[n-1]] = nums[n-1]
        // Add the previous triangles as the next triangle with an added position
        // plus the element added in the current triangle
        for (i in n-2 downTo 0) {
            arr[sp[i]] = nums[i]
            (1..<n-i).forEach { j -> arr[sp[i] + j] = arr[sp[i+1] + j - 1] + nums[i] }
        }
        // Sort the array
        val sortedArr = arr.sorted()
        nums.map { num -> num*num }.sorted().toIntArray()
        // Add the elements of the array from the left to the right positions
        // To avoid integer overflow, the MOD is subtracted when the accumulator is going to surpass the MOD
        val mod = 1_000_000_007
        return (left-1..<right).fold(0) { acc, i -> if (acc + sortedArr[i] > mod) acc + sortedArr[i] - mod else acc + sortedArr[i] }
    }

    fun sortedSquares(nums: IntArray): IntArray {
        var left = 0; var right = nums.size - 1
        val result = IntArray(nums.size)
        var index = right
        while(left<=right) {
            result[index] = if (nums[right]*nums[right] > nums[left]*nums[left]) nums[right]*nums[right--] else nums[left]*nums[left++]
            index--
        }
        return result
    }

    fun minimumPushes(word: String): Int {
        var keyPushes = 1; var counter = 0; var totalPushes = 0
        word.groupingBy{ it } .eachCount().values.sortedWith { a, b -> if (a < b) 1 else -1 }.forEach { i: Int ->
            totalPushes += i * keyPushes
            if(++counter == 8) {
                keyPushes++
                counter = 0
            }
        }
        return totalPushes
    }

    fun threeSum(nums: IntArray): List<List<Int>> {
        val sortedNums = nums.sorted()
        val result = mutableSetOf<List<Int>>()
        (0..sortedNums.size-3).forEach {
            var l = it + 1; var r = sortedNums.size - 1
            while (l < r) {
                when (0.compareTo(sortedNums[it] + sortedNums[l] + sortedNums[r])) {
                    0 -> {
                        result.add(listOf(sortedNums[it], sortedNums[l], sortedNums[r]))
                        l++
                        r--
                    }
                    1 -> l++
                    -1 -> r--
                }
            }
        }
        return result.toList()
    }
}