import java.lang.IndexOutOfBoundsException

import java.util.InputMismatchException

open class TaskManager(private var projectList: ArrayList<TaskProject>, private var completedTasks: TaskProject) {
    private val viewTaskSelectionNum = 1
    private val addTaskSelectionNum = 2
    private val deleteTaskSelectionNum = 3
    private val addProjectSelectionNum = 4
    private val deleteProjectSelectionNum = 5
    private val viewCompletedTasksSelectionNum = 6
    private val exitSelectionNum = 7

    private val creator = CreateFunctions()
    private val selector = SelectFunctions()
    private val printer = PrintFunctions()

    fun start() {
        var programRun = true

        val menu = Menu(
            viewTaskSelectionNum,
            addTaskSelectionNum,
            deleteTaskSelectionNum,
            addProjectSelectionNum,
            deleteProjectSelectionNum,
            viewCompletedTasksSelectionNum,
            exitSelectionNum
        )
        while (programRun) {
            when (menu.userMenu()) {
                viewTaskSelectionNum -> {
                    val selection = selector.selectProjectWithValidation(projectList)
                    selection.viewTasksInProject()
                }

                addTaskSelectionNum -> {
                    val newTask = creator.createTask(projectList)
                    appendTaskToProject(newTask)
                    println("Task added")
                }

                deleteTaskSelectionNum -> {
                    completeTask()
                    println("Task completed!")
                }

                addProjectSelectionNum -> {
                    projectList.add(creator.createProject(projectList))
                    println("Project added")
                }

                deleteProjectSelectionNum -> deleteProject()
                viewCompletedTasksSelectionNum -> viewAllCompletedTasks()
                exitSelectionNum -> {
                    println("Exiting")
                    programRun = false
                }
            }
        }
    }


    private fun completeTask() {
        println("Which project is the task in?")

        while (true) {
            val projectSelectionInput = selector.selectProjectWithValidation(projectList)

            println("Which task do you want to complete?")
            val taskToComplete = selector.selectTaskWithValidation(projectSelectionInput)

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

    fun appendTaskToProject(task: Task) {
        for (project in projectList) {
            if (project.projectID == task.projectId) {
                project.addTask(task)
            }
        }
    }




    fun viewAllCompletedTasks() = completedTasks.viewTasksInProject()








}