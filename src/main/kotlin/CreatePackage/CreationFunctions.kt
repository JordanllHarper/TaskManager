package CreatePackage

import java.time.LocalDate

class CreationFunctions() {

    fun createDateWithoutTime(stringDateChars: String): LocalDate {

        val day = ("" + stringDateChars[0] + stringDateChars[1]).toInt()
        val month = ("" + stringDateChars[3] + stringDateChars[4]).toInt()
        val year = ("" + stringDateChars[6] + stringDateChars[7] + stringDateChars[8] + stringDateChars[9]).toInt()

        return LocalDate.of(year, month, day)


//    val localDateTime = LocalDateTime()

    }

    fun createDashes(longestTaskInProjectSize: Int): String {
        var numDashes = "-------"
        for (num in 0..longestTaskInProjectSize) {
            numDashes += "-"
        }

        return numDashes
    }


}