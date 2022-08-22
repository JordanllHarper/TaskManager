import java.time.LocalDate

class CreateTask() {

    val creator = CreationFunctions()
    val taskSelector = TaskSelector()
    val projectSelector = TaskProjectSelectors()


    fun getTitleFromUser(): String {
        println("Give your task a title: ")
        return readln()
    }

    fun getPriorityFromUser(): String {
        println("How important is it from 1 - 4?: ")
        return readln()
    }

    fun getDueDateFromUser(): String {
        println("When is the task due? Enter in DD/MM/YYYY format")
        return readln()
    }

    fun getDueDate(): LocalDate? {
        while (true) {
            val dueDateInput = getDueDateFromUser().trim()

            if (dueDateInput.isNotBlank()) {
                try {
                    return creator.createDateWithoutTime(dueDateInput)
                } catch (e: NumberFormatException) {
                    println("Invalid entry, please try again")
                } catch (e: StringIndexOutOfBoundsException) {
                    println("Invalid entry format, please try again")
                } catch (e: Exception) {
                    println("The date entered is before today's date, please try again")
                }
            }
            return null

        }
    }

    fun getDateToDoFromUser(): String {
        println("When do you want to complete the task? Enter in DD/MM/YYYY format")
        return readln()
    }

    fun checkDateIsBeforeToday(date: LocalDate): Boolean {
        return date.isBefore(LocalDate.now())

    }

    fun getDateToDo(): LocalDate? {
        while (true) {
            val dateToDo = getDateToDoFromUser().trim()

            if (dateToDo.isNotBlank()) {
                try {

                    val dateFormatted = creator.createDateWithoutTime(dateToDo)
                    if (checkDateIsBeforeToday(dateFormatted)) {
                        throw Exception()
                    }
                    return dateFormatted
                } catch (e: NumberFormatException) {
                    println("Invalid entry, please try again")
                } catch (e: StringIndexOutOfBoundsException) {
                    println("Invalid entry format, please try again")
                } catch (e: Exception) {
                    println("The date entered is before today's date, please try again")
                }
            } else {
                return null
            }

        }


    }


    fun createTask(projectList: ArrayList<TaskProject>): Task {
        val taskBuilder = TaskBuilder()

        println("If you don't want to input anything, just leave blank and press enter")

        val titleInput = getTitleFromUser().trim()

        if (titleInput.isNotBlank()) {
            taskBuilder.addTitle(titleInput)
        }

        val priorityLevelInput = getPriorityFromUser().trim()

        if (priorityLevelInput.isNotBlank()) {
            taskBuilder.addPriorityLevel(PriorityLevelSelector().selectPriorityLevel(priorityLevelInput))
        }

        println("Which project would you like to put your task in?")
        val projectSelection = projectSelector.selectItem(projectList)
        val currentTaskIdAssignment = taskSelector.getNextId(projectSelection.tasksInProject)

        taskBuilder.addId(currentTaskIdAssignment)
        taskBuilder.addProjectId(projectSelection.projectID)

        val dueDate: LocalDate? = getDueDate()
        taskBuilder.addDueDate(dueDate)

        val dateToDo: LocalDate? = getDateToDo()
        taskBuilder.addDateToDo(dateToDo)

        return TaskFactory.createTask(taskBuilder)

    }


}