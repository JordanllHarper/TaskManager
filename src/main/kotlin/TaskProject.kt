interface TaskProjectInterface{
    var projectID : Int
    var title : String
    var tasksInProject : ArrayList<Task>

    fun getCountOfTasks() = tasksInProject.count()

}

object ProjectFactory{
    fun createProject(projectID: Int, title: String): TaskProject {
        return TaskProject(projectID, title)
    }
}

open class TaskProject(override var projectID: Int,
                       override var title: String) : TaskProjectInterface {

    override var tasksInProject: ArrayList<Task> = ArrayList()

    open fun viewTasksInProject() {
        if (tasksInProject.size == 0) {
            val dashCount = createDashes(10)
            println(dashCount)
            println("No tasks in project")
            println(dashCount)
        } else {
            val longestTask = getLongestTaskInProject()
            val dashCount = createDashes(longestTask.title.length)
            for (task: Task in tasksInProject) {
                println(dashCount)
                println(task)
                println(dashCount)
            }
        }
    }

    fun addTask(newTask : Task) = tasksInProject.add(newTask)

    fun removeTaskFromProject(taskToRemove : Task) = tasksInProject.remove(taskToRemove)

    fun getLongestTaskInProject() : Task{
        var currentLongestTask = tasksInProject.get(0)
        var currentLongestTaskLength = currentLongestTask.title.length
        for (task in tasksInProject){
            if (currentLongestTaskLength < task.title.length){
                currentLongestTask = task
                currentLongestTaskLength = task.title.length
            }
        }
        return currentLongestTask
    }

    fun createDashes(longestTaskInProjectSize : Int) : String{
        var numDashes = "-------"
        for (num in 0..longestTaskInProjectSize){
            numDashes += "-"
        }

        return numDashes
    }



}

class CompletedTasksProject(projectID: Int, title : String) : TaskProject(projectID, title){
    override fun viewTasksInProject() {
        if (tasksInProject.size == 0) {
            val dashCount = createDashes(10)
            println(dashCount)
            println("No tasks in project")
            println(dashCount)
        } else {
            val longestTask = getLongestTaskInProject()
            val dashCount = createDashes(longestTask.title.length)
            for (task: Task in tasksInProject) {
                println("$dashCount\n" +
                        "Title: ${task.title}\nDue date: ${task.dueDateformatted}\nDate to do: ${task.dateToDoFormatted}\nPriority: ${task.priorityLevel}" +
                        "\n$dashCount")
            }
        }
    }
}

