import java.time.LocalDate

fun createTestTask(id : Int, title : String, date : LocalDate, projectId : Int, priorityLevel: PriorityLevel): Task {
    val taskBuilder = TaskBuilder()
    taskBuilder.addId(id)
    taskBuilder.addTitle(title)
    taskBuilder.addDate(date)
    taskBuilder.addProjectId(projectId)
    taskBuilder.addPriorityLevel(priorityLevel)
    return taskBuilder.returnTask()
}

fun main() {
    val projectList : ArrayList<TaskProject> = arrayListOf(TaskProject(1, "Inbox"))
    val completedTasks = CompletedTasksProject(-1, "Completed tasks")

    val personal = TaskProject(2, "Personal")

    val task1 = createTestTask(1, "Walk dogs", LocalDate.of(2022, 8, 28), personal.projectID, PriorityLevel.P2)
    val task2 = createTestTask(2, "Get shopping", LocalDate.of(2022, 8, 26), personal.projectID, PriorityLevel.P1)
    val task3 = createTestTask(3, "File forms", LocalDate.of(2022, 9, 5), personal.projectID, PriorityLevel.P3)

    personal.addTask(task1)
    personal.addTask(task2)
    personal.addTask(task3)


    val work = TaskProject(3, "Work")
    val task4 = createTestTask(1, "Finish function", LocalDate.of(2022, 9, 2), personal.projectID, PriorityLevel.P1)
    val task5 = createTestTask(2, "File reports", LocalDate.of(2022, 8, 21), personal.projectID, PriorityLevel.P1)
    val task6 = createTestTask(3, "Meeting with manager", LocalDate.of(2022, 11, 5), personal.projectID, PriorityLevel.P3)

    work.addTask(task4)
    work.addTask(task5)
    work.addTask(task6)

    projectList.add(personal)
    projectList.add(work)



    val taskManagerInstance = TaskManager(projectList, completedTasks)

    taskManagerInstance.userMenu()
}