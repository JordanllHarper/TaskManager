import Enums.PriorityLevel
import PrintPackage.PrintFunctions
import SelectPackage.SelectFunctions
import java.lang.Exception
import java.lang.IndexOutOfBoundsException
import java.lang.NumberFormatException
import java.util.*
import kotlin.collections.ArrayList

interface Selector<T> {
    fun selectItem(list: ArrayList<T>): T
    fun getNextId(list: ArrayList<T>): Int
}

class TaskProjectSelectors : Selector<TaskProject> {

    val printer = PrintFunctions()
    val selector = SelectFunctions()

    override fun selectItem(list: ArrayList<TaskProject>): TaskProject {
        while (true) {
            printer.getProjectPrompt(list)
            val userInput = selector.getUserInput("project")

            try {
                val userInputToInt = userInput.toInt()
                return selector.selectProject(userInputToInt, list)
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

    override fun getNextId(list: ArrayList<TaskProject>): Int {

        return if (list.size == 0) {
            1
        } else {
            list.get(list.size - 1).projectID + 1
        }

    }

}

class TaskSelector : Selector<Task> {

    val selector = SelectFunctions()
    val printer = PrintFunctions()

    override fun selectItem(list: ArrayList<Task>): Task {

        while (true) {
            printer.getTaskPrompt(list)
            val userInput = selector.getUserInput("project")

            try {
                val userInputToInt = userInput.toInt()

                val testTask = selector.selectTask(userInputToInt, list)

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

    override fun getNextId(list: ArrayList<Task>): Int {
        return if (list.size == 0) {
            1
        } else {
            list.get(list.size - 1).taskID + 1
        }
    }


}

class PriorityLevelSelector {
    fun selectPriorityLevel(stringPriorityLevel: String): PriorityLevel {
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
}

