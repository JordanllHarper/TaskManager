

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
    private val deleterCompleter = DeleteCompleteFunctions()

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
                    deleterCompleter.completeTask(projectList, completedTasks)
                    println("Task completed!")
                }

                addProjectSelectionNum -> {
                    projectList.add(creator.createProject(projectList))
                    println("Project added")
                }

                deleteProjectSelectionNum -> deleterCompleter.deleteProject(projectList)
                viewCompletedTasksSelectionNum -> viewAllCompletedTasks()
                exitSelectionNum -> {
                    println("Exiting")
                    programRun = false
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