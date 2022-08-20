import java.lang.Exception
import java.lang.IndexOutOfBoundsException
import java.lang.NumberFormatException
import java.util.*
import kotlin.collections.ArrayList

class SelectFunctions() {
    val printer = PrintFunctions()

    fun determinePriorityLevel(stringPriorityLevel: String): PriorityLevel {
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

    fun selectTaskWithValidation(project: TaskProject): Task {
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

    fun selectProjectWithValidation(projectList: ArrayList<TaskProject>): TaskProject {
        while (true) {
            printer.getProjectPrompt(projectList)
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

    fun getNextAvailableTaskId(project: TaskProject): Int {
        return if (project.getCountOfTasks() == 0) {
            1
        } else {
            project.tasksInProject.get(project.tasksInProject.size - 1).taskID + 1
        }
    }


    fun getNextAvailableProjectId(projectList: ArrayList<TaskProject>): Int {
        return projectList.get(projectList.size - 1).projectID + 1
    }
}