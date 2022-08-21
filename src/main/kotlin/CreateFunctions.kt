import java.lang.Exception
import java.lang.NumberFormatException
import java.time.DateTimeException
import java.time.LocalDate
import java.util.Date

class CreateFunctions() {
    val selector = SelectFunctions()
    val parser = ParseFunctions()


    fun createTask(projectList: ArrayList<TaskProject>): Task {
        val taskBuilder = TaskBuilder()

        println("If you don't want to input anything, just leave blank and press enter")

        println("Give your task a title: ")
        val title = readln().trim()

        if (title.isNotBlank()) {
            taskBuilder.addTitle(title)
        }

        println("How important is it from 1 - 4?: ")
        val priorityLevelInput = readln().trim()
        if (priorityLevelInput.isNotBlank()) {
            taskBuilder.addPriorityLevel(selector.determinePriorityLevel(priorityLevelInput))
        }




        println("Which project would you like to put your task in?")
        val projectSelection = selector.selectProjectWithValidation(projectList)
        val currentTaskIdAssignment = selector.getNextAvailableTaskId(projectSelection)
        taskBuilder.addId(currentTaskIdAssignment)
        taskBuilder.addProjectId(projectSelection.projectID)

        println("When is the task due? Enter in DD/MM/YYYY format")
        taskBuilder.addDueDate(createDate())

        println("When do you want to complete the task? Enter in DD/MM/YYYY format")
        taskBuilder.addDateToDo(createDate())

        return taskBuilder.returnTask()

    }

    fun createProject(projectList: ArrayList<TaskProject>): TaskProject {
        println("Give your project a name: ")
        val name = readln()
        return ProjectFactory.TaskProjectFactory(selector.getNextAvailableProjectId(projectList), name)
    }

    fun createDateWithoutTime(stringDateChars: String): LocalDate {


        val day = ("" + stringDateChars[0] + stringDateChars[1]).toInt()
        val month = ("" + stringDateChars[2] + stringDateChars[3]).toInt()
        val year = ("" + stringDateChars[4] + stringDateChars[5] + stringDateChars[6] + stringDateChars[7]).toInt()

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

    fun createDate(): LocalDate? {
        while (true) {
            val dateInput = readln().trim()

            if (dateInput.isNotBlank()) {
                try {
                    val dateObj = createDateWithoutTime(parser.parseDate(dateInput))
                    if (dateObj.isBefore(LocalDate.now())){
                        throw Exception()
                    } else{
                        return dateObj

                    }
                } catch (e: NumberFormatException) {
                    println("Invalid entry, please try again")
                } catch (e: StringIndexOutOfBoundsException) {
                    println("Invalid entry format, please try again")
                } catch (e: Exception){
                    println("The date entered is before today's date, please try again")
                }
            } else {
                return null
            }
        }
    }


}