

open class TaskManager(private var projectList: ArrayList<TaskProject>, private var completedTasks: TaskProject) {
    private val viewTaskSelectionNum = 1
    private val addTaskSelectionNum = 2
    private val editTaskSelectionNum = 3
    private val deleteTaskSelectionNum = 4
    private val addProjectSelectionNum = 5
    private val deleteProjectSelectionNum = 6
    private val viewCompletedTasksSelectionNum = 7
    private val exitSelectionNum = 8
    
    private val deleterCompleter = DeleteCompleteFunctions()
    private val editor = EditFunctions()

    fun start() {
        var programRun = true

        val menu = Menu(
            viewTaskSelectionNum,
            addTaskSelectionNum,
            editTaskSelectionNum,
            deleteTaskSelectionNum,
            addProjectSelectionNum,
            deleteProjectSelectionNum,
            viewCompletedTasksSelectionNum,
            exitSelectionNum
        )
        while (programRun) {
            when (menu.userMenu()) {
                viewTaskSelectionNum -> {
                    val selection = TaskProjectSelectors().selectItem(projectList)
                    selection.viewTasksInProject()
                }

                addTaskSelectionNum -> {
                    val newTask = CreateTask().createTask(projectList)
                    appendTaskToProject(newTask)
                    println("Task added")
                }

                editTaskSelectionNum -> {
                    println("Which project is the task you want to edit in?")
                    val projectSelection = TaskProjectSelectors().selectItem(projectList)
                    editor.editTask(projectSelection)
                }

                deleteTaskSelectionNum -> {
                    deleterCompleter.deleteItem(projectList, completedTasks)
                    println("Task completed!")
                }


                addProjectSelectionNum -> {
                    val newProject = CreateProject().createProject(projectList)
                    projectList.add(newProject)
                    println("Project added")
                }

                deleteProjectSelectionNum -> deleterCompleter.deleteItem(projectList)
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