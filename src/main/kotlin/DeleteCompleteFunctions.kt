import java.lang.IndexOutOfBoundsException
import java.util.*
import kotlin.collections.ArrayList

class DeleteCompleteFunctions {

    val taskProjectSelector = TaskProjectSelectors()
    val taskSelector = TaskSelector()
    val printer = PrintFunctions()
    val creator = CreationFunctions()

    fun deleteItem(projectList: ArrayList<TaskProject>, completedTasks: TaskProject) {
        println("Which project is the task in?")

        while (true) {
            val projectSelectionInput = taskProjectSelector.selectItem(projectList)

            if (projectSelectionInput.getCountOfTasks() == 0) {
                println(creator.createDashes(20))
                println("No tasks in project")
                println(creator.createDashes(20))
                break
            } else {
                println("Which task do you want to complete?")
                val taskToComplete = taskSelector.selectItem(projectSelectionInput.tasksInProject)

                projectSelectionInput.removeTaskFromProject(taskToComplete)

                completedTasks.addTask(taskToComplete)
                break
            }


        }


    }

    fun deleteItem(projectList: ArrayList<TaskProject>) {
        var validInput = false
        println("Which project do you want to delete? Type the ID or type any other number to exit!\nNote: you cannot delete the inbox project")


        while (!validInput) {
            println("Which project do you wish to delete?")
            printer.getProjectPrompt(projectList)
            val projectSelection = taskProjectSelector.selectItem(projectList)

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
}