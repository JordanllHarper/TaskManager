import java.lang.IndexOutOfBoundsException
import java.util.*
import kotlin.collections.ArrayList

class DeleteCompleteFunctions {

    val selector = SelectFunctions()
    val printer = PrintFunctions()
    val creator = CreateFunctions()

    fun completeTask(projectList: ArrayList<TaskProject>, completedTasks: TaskProject) {
        println("Which project is the task in?")

        while (true) {
            val projectSelectionInput = selector.selectProjectWithValidation(projectList)

            if (projectSelectionInput.getCountOfTasks() == 0) {
                println(creator.createDashes(20))
                println("No tasks in project")
                println(creator.createDashes(20))
                break
            } else {
                println("Which task do you want to complete?")
                val taskToComplete = selector.selectTaskWithValidation(projectSelectionInput)

                projectSelectionInput.removeTaskFromProject(taskToComplete)

                completedTasks.addTask(taskToComplete)

                break
            }


        }


    }

    fun deleteProject(projectList: ArrayList<TaskProject>) {
        var validInput = false
        println("Which project do you want to delete? Type the ID or type any other number to exit!\nNote: you cannot delete the inbox project")


        while (!validInput) {
            println("Which project do you wish to delete?")
            printer.getProjectPrompt(projectList)
            val projectSelection = selector.selectProjectWithValidation(projectList)

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