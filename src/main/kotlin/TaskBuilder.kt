import java.time.LocalDate

class TaskBuilder() {

    var taskID = 1
    var title = "no_title"
    var projectId = 1 //inbox
    var priorityLevel = PriorityLevel.P4
    var dueDate: LocalDate? = null
    var dateToDo: LocalDate? = null

    fun addId(newId: Int) {
        this.taskID = newId
    }

    fun addTitle(newTitle: String) {
        this.title = newTitle
        returnTask()
    }

    fun addProjectId(newProjectID: Int) {
        this.projectId = newProjectID
        returnTask()
    }

    fun addPriorityLevel(newPriorityLevel: PriorityLevel) {
        this.priorityLevel = newPriorityLevel
        returnTask()
    }

    fun addDueDate(newDate: LocalDate) {
        this.dueDate = newDate
        returnTask()
    }

    fun addDateToDo(newDate: LocalDate) {
        this.dateToDo = newDate
        returnTask()
    }

    fun returnTask(): Task {
        return Task(taskID, title, projectId, priorityLevel, dueDate)
    }


}