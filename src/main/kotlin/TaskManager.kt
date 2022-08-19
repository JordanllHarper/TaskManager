import java.lang.Exception
import java.lang.IndexOutOfBoundsException
import java.lang.NumberFormatException
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.InputMismatchException

class TaskManager(private var projectList: ArrayList<TaskProject>, private var completedTasks: TaskProject) {

    private val viewTaskSelectionNum = 1
    private val addTaskSelectionNum = 2
    private val deleteTaskSelectionNum = 3
    private val addProjectSelectionNum = 4
    private val deleteProjectSelectionNum = 5
    private val viewCompletedTasksSelectionNum = 6
    private val exitSelectionNum = 7

    private fun selectProject(projectIdWanted: Int, projectList: ArrayList<TaskProject>): TaskProject {
        for (project in projectList) {
            if (projectIdWanted == project.projectID) {
                return project
            }
        }
        throw Exception()
    }

    private fun selectTask(taskIdWanted: Int, project: TaskProject): Task? {
        for (task in project.tasksInProject) {
            if (taskIdWanted == task.taskID) {
                return task
            }
        }
        return null
    }

    private fun selectTaskWithValidation(project: TaskProject): Task {
        println("Select a Task: ")

        while (true) {
            project.viewTasksInProject()
            val userInput = readln().trim()

            try {
                val userInputToInt = userInput.toInt()

                val testTask = selectTask(userInputToInt, project)

                if (testTask == null) {
                    println("Invalid input, please try again")
                } else {
                    return testTask
                }
            } catch (e: InputMismatchException) {
                println("Invalid input, please try again")
            } catch (e: IndexOutOfBoundsException) {
                println("Invalid input, please try again")
            } catch (e: NumberFormatException) {
                println("Invalid task select. Please try again:")
            }
        }


    }

    private fun selectProjectWithValidation(): TaskProject {
        while (true) {
            getProjectPrompt()
            val userInput = readln().trim()

            try {
                val userInputToInt = userInput.toInt()
                val testProject = selectProject(userInputToInt, projectList)
                return testProject
            } catch (e: InputMismatchException) {
                println("Invalid project select. Please try again:")
            } catch (e: IndexOutOfBoundsException) {
                println("Invalid project select. Please try again:")
            } catch (e: NumberFormatException) {
                println("Invalid project select. Please try again:")
            } catch (e: Exception) {
                println("Invalid project select. Please try again:")
            }
        }


    }

    fun userMenu() {
        var programRun = true
        while (programRun) {
            println(
                "Main menu:\n" +
                        "Press [$viewTaskSelectionNum] to view tasks in a project\n" +
                        "Press [$addTaskSelectionNum] to add a task\n" +
                        "Press [$deleteTaskSelectionNum] to complete/delete a task from a project\n" +
                        "Press [$addProjectSelectionNum] to add a new project\n" +
                        "Press [$deleteProjectSelectionNum] to delete a project\n" +
                        "Press [$viewCompletedTasksSelectionNum] to view your completed tasks\n" +
                        "Press [$exitSelectionNum] to exit"
            )
            when (readln()) {
                "$viewTaskSelectionNum" -> {
                    val selection = selectProjectWithValidation()
                    selection.viewTasksInProject()
                }

                "$addTaskSelectionNum" -> {
                    val newTask = createTask()
                    appendTaskToProject(newTask)
                    println("Task added")
                }

                "$deleteTaskSelectionNum" -> {
                    completeTask()
                    println("Task completed!")
                }

                "$addProjectSelectionNum" -> {
                    projectList.add(createProject())
                    println("Project added")
                }

                "$deleteProjectSelectionNum" -> deleteProject()


                "$viewCompletedTasksSelectionNum" -> viewAllCompletedTasks()

                "$exitSelectionNum" -> {
                    println("Exiting")
                    programRun = false
                }
            }
        }
    }

    private fun createTask(): Task {
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
            taskBuilder.addPriorityLevel(determinePriorityLevel(priorityLevelInput))
        }




        println("Which project would you like to put your task in?")
        val projectSelection = selectProjectWithValidation()
        val currentTaskIdAssignment = getNextAvailableTaskId(projectSelection)
        taskBuilder.addId(currentTaskIdAssignment)
        taskBuilder.addProjectId(projectSelection.projectID)

        println("When do you want to complete the task? Enter in DD/MM/YYYY format")
        while (true) {
            val dateInput = readln().trim()

            if (dateInput.isNotBlank()) {
                try {
                    taskBuilder.addDate(createDateWithoutTime(parseDate(dateInput)))
                    break
                } catch (e: NumberFormatException) {
                    println("Invalid entry, please try again")
                }
            } else {
                break
            }
        }

        return taskBuilder.returnTask()

    }

    private fun createProject(): TaskProject {
        println("Give your project a name: ")
        val name = readln()
        return ProjectFactory.createProject(getNextAvailableProjectId(), name)
    }

    private fun completeTask() {
        println("Which project is the task in?")

        while (true) {
            val projectSelectionInput = selectProjectWithValidation()

            println("Which task do you want to complete?")
            val taskToComplete = selectTaskWithValidation(projectSelectionInput)

            projectSelectionInput.removeTaskFromProject(taskToComplete)

            completedTasks.addTask(taskToComplete)

            break

        }


    }

    fun deleteProject() {
        var validInput = false
        println("Which project do you want to delete? Type the ID or type any other number to exit!\nNote: you cannot delete the inbox project")


        while (!validInput) {
            println("Which project do you wish to delete?")
            getProjectPrompt()
            val projectSelection = selectProjectWithValidation()

            if (projectSelection.projectID == 1) {
                println("Going to main menu...")
                validInput = true
            } else {
                try {
                    projectList.remove(projectSelection)
                    validInput = true
                    println("Project deleted")
                } catch (e: IndexOutOfBoundsException) {
                    println("Going to main menu...")
                    validInput = true
                } catch (e: InputMismatchException) {
                    println("Please enter a project ID")
                }
            }
        }
    }

    fun appendTaskToProject(task: Task) {
        for (project in projectList) {
            if (project.projectID == task.projectId) {
                project.addTask(task)
            }
        }
    }

    private fun determinePriorityLevel(stringPriorityLevel: String): PriorityLevel {
        return when (stringPriorityLevel) {
            "1" -> PriorityLevel.P1
            "2" -> PriorityLevel.P2
            "3" -> PriorityLevel.P3
            "4" -> PriorityLevel.P4

            else -> {
                println("Invalid input, defaulting to Priority 4")
                return PriorityLevel.P4
            }
        }
    }

    private fun printAllProjects(projectList: ArrayList<TaskProject>) {
        for (TaskProject in projectList) {
            println("ID: ${TaskProject.projectID}\n${TaskProject.title}")
        }
    }

    fun viewAllCompletedTasks() = completedTasks.viewTasksInProject()

    fun parseDate(stringDateToParse: String): String {
        //DD/MM/YYYY

        return "" + stringDateToParse[0] + stringDateToParse[1] + stringDateToParse[3] + stringDateToParse[4] + stringDateToParse[6] + stringDateToParse[7] + stringDateToParse[8] + stringDateToParse[9]


    }

    fun createDateWithTime(stringDateUnformatted: String, hour: Int, minute: Int, second: Int): LocalDateTime {

        val stringDateChars: String = parseDate(stringDateUnformatted)

        val day = ("" + stringDateChars[0] + stringDateChars[1]).toInt()
        val month = ("" + stringDateChars[2] + stringDateChars[3]).toInt()
        val year = ("" + stringDateChars[4] + stringDateChars[5] + stringDateChars[6] + stringDateChars[7]).toInt()

        return LocalDateTime.of(year, month, day, hour, minute, second)


//    val localDateTime = LocalDateTime()

    }

    fun createDateWithoutTime(stringDateChars: String): LocalDate {


        val day = ("" + stringDateChars[0] + stringDateChars[1]).toInt()
        val month = ("" + stringDateChars[2] + stringDateChars[3]).toInt()
        val year = ("" + stringDateChars[4] + stringDateChars[5] + stringDateChars[6] + stringDateChars[7]).toInt()

        return LocalDate.of(year, month, day)


//    val localDateTime = LocalDateTime()

    }

    fun getProjectPrompt() {
        println("Select a project: ")
        printAllProjects(projectList)
    }


    fun getNextAvailableTaskId(project: TaskProject): Int {
        return project.tasksInProject.get(project.tasksInProject.size - 1).taskID + 1
    }

    fun getNextAvailableProjectId(): Int {
        return projectList.get(projectList.size - 1).projectID + 1
    }

}