package puzzles.y2024

import java.io.File

class PuzzleSolver9 {

    private val diskFiles = mutableListOf<DiskFile>()
    private val freeDiskSpaces = mutableListOf<DiskSpace>()
    init {
        var fileIndex = 0
        var diskPosition = 0L
        DISK_MAP.map { c -> c.code - '0'.code }.forEachIndexed { position, space ->
            val diskSpace = DiskSpace(diskPosition, space)
            diskPosition += space
            if(position % 2 == 0) {
                diskFiles.add(DiskFile(diskSpace, fileIndex++))
            } else {
                freeDiskSpaces.add(diskSpace)
            }
        }
    }

    fun puzzle91(): Long {
        val fragmentedFiles = diskFiles.map { diskFile -> diskFile.fragmentFile() }.flatten()
        val fragmentedSpaces = freeDiskSpaces.map { diskSpace -> diskSpace.fragmentSpace() }.flatten()

        return fragmentedFiles.reversed().map { diskFile ->
            fragmentedSpaces.takeIf {
                it.isNotEmpty()
            }?.first().takeIf { freeSpace ->
                isValidSpace(freeSpace, diskFile)
            }?.let { freeSpace ->
                fragmentedSpaces.removeFirst()
                DiskFile(DiskSpace(freeSpace.position, 1), diskFile.fileIndex)
            } ?: diskFile
        }.sumOf { it.checkSum() }
    }

    fun puzzle92(): Long {
        var freeDiskSpacesList = freeDiskSpaces
        return diskFiles.reversed().map { diskFile ->
            freeDiskSpacesList.find { freeSpace ->
                freeSpace.size >= diskFile.diskSpace.size
            }.takeIf { freeSpace ->
                isValidSpace(freeSpace, diskFile)
            }?.let { freeSpace ->
                freeDiskSpacesList = removeFreeSpace(freeDiskSpacesList, freeSpace, diskFile)
                DiskFile(DiskSpace(freeSpace.position, diskFile.diskSpace.size), diskFile.fileIndex)
            } ?: diskFile
        }.sumOf { it.checkSum() }
    }

    private fun isValidSpace(freeSpace: DiskSpace?, diskFile: DiskFile) =
        freeSpace != null && freeSpace.position < diskFile.diskSpace.position

    private fun removeFreeSpace(
        freeDiskSpacesList: MutableList<DiskSpace>,
        freeSpace: DiskSpace,
        diskFile: DiskFile
    ) = if (freeSpace.size == diskFile.diskSpace.size) {
            freeDiskSpacesList.remove(freeSpace)
            freeDiskSpacesList
        } else {
            val newSpace = DiskSpace(
                position = freeSpace.position + diskFile.diskSpace.size,
                size = freeSpace.size - diskFile.diskSpace.size
            )
            freeDiskSpacesList.indexOf(freeSpace).let {
                (freeDiskSpacesList.subList(0, it) + mutableListOf(newSpace) +
                    freeDiskSpacesList.subList(it + 1, freeDiskSpacesList.size)).toMutableList()
            }
        }

    companion object {
        private val DISK_MAP = File("src/main/resources/2024/advent_file_9.txt")
            .inputStream().readBytes().toString(Charsets.UTF_8)
    }
}

data class DiskFile(val diskSpace: DiskSpace, val fileIndex: Int) {
    fun fragmentFile() = diskSpace.fragmentSpace().map { diskSpace ->
        DiskFile(diskSpace, fileIndex)
    }
    fun checkSum() = ((2 * diskSpace.position + diskSpace.size - 1) * diskSpace.size) / 2 * fileIndex

}

data class DiskSpace(val position: Long, val size: Int) {
    fun fragmentSpace() = (0..<size).map {
        DiskSpace(position + it, 1)
    }
}