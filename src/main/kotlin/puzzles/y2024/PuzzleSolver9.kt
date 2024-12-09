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
            if (fragmentedSpaces.isNotEmpty()) {
                fragmentedSpaces.removeFirst().let { freeSpace ->
                    if (freeSpace.position <= diskFile.diskSpace.position)
                        DiskFile(DiskSpace(freeSpace.position, diskFile.diskSpace.size), diskFile.fileIndex)
                    else diskFile
                }
            } else diskFile
        }.sumOf { it.checkSum() }
    }

    fun puzzle92(): Long {
        val freeDiskSpacesSet = freeDiskSpaces.toSortedSet(compareBy { it.position })
        return diskFiles.reversed().map { diskFile ->
            val freeSpace = freeDiskSpacesSet.find { freeSpace -> freeSpace.size >= diskFile.diskSpace.size }
            if (freeSpace != null && freeSpace.position < diskFile.diskSpace.position) {
                freeDiskSpacesSet.remove(freeSpace)
                val newSpace = DiskSpace(
                    position = freeSpace.position + diskFile.diskSpace.size,
                    size = freeSpace.size - diskFile.diskSpace.size
                )
                freeDiskSpacesSet.add(newSpace)
                DiskFile(DiskSpace(freeSpace.position, diskFile.diskSpace.size), diskFile.fileIndex)
            } else diskFile
        }.sumOf { it.checkSum() }
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