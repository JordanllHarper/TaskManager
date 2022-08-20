interface TaskProjectInterface {
    var projectID: Int
    var title: String
    var tasksInProject: ArrayList<Task>

    fun getCountOfTasks() = tasksInProject.count()

}

object ProjectFactory {
    fun TaskProjectFactory(projectID: Int, title: String): TaskProject {
        return TaskProject(projectID, title)
    }
}

open class TaskProject(
    override var projectID: Int,
    override var title: String
) : TaskProjectInterface {

    val creator = CreateFunctions()

    override var tasksInProject: ArrayList<Task> = ArrayList()

    open fun viewTasksInProject() {
        if (tasksInProject.size == 0) {
            val dashCount = creator.createDashes(10)
            println(dashCount)
            println("No tasks in project")
            println(dashCount)
        } else {
            val longestTask = getLongestTaskInProject()
            val dashCount = creator.createDashes(longestTask.title.length)
            for (task: Task in tasksInProject) {
                println(dashCount)
                println(task)
                println(dashCount)
            }
        }
    }

    fun addTask(newTask: Task) = tasksInProject.add(newTask)

    fun removeTaskFromProject(taskToRemove: Task) = tasksInProject.remove(taskToRemove)

    fun getLongestTaskInProject(): Task {
        var currentLongestTask = tasksInProject.get(0)
        var currentLongestTaskLength = currentLongestTask.title.length
        for (task in tasksInProject) {
            if (currentLongestTaskLength < task.title.length) {
                currentLongestTask = task
                currentLongestTaskLength = task.title.length
            }
        }
        return currentLongestTask
    }



    override fun toString(): String {

        creator.createDashes(10)
        return "${creator.createDashes(10)}\nID: $projectID\nTitle: $title\nNumber of tasks: ${getCountOfTasks()}\n" +
                creator.createDashes(10)

    }


}

class CompletedTasksProject(projectID: Int, title: String) : TaskProject(projectID, title) {
    override fun viewTasksInProject() {
        if (tasksInProject.size == 0) {
            val dashCount = creator.createDashes(10)
            println(dashCount)
            println("No tasks in project")
            println(dashCount)
        } else {
            val longestTask = getLongestTaskInProject()
            val dashCount = creator.createDashes(longestTask.title.length)
            for (task: Task in tasksInProject) {
                println(dashCount)
                println(task)
                println(dashCount)
            }
        }
    }
}

