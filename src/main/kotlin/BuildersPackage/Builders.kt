import Enums.PriorityLevel
import java.time.LocalDate

interface Builders<T> {
    var id: Int
    var title: String
    fun addId(newId: Int)
    fun addTitle(newTitle: String)
    fun returnT(): T
}

class TaskBuilder() : Builders<Task> {

    override var id: Int = 1
    override var title: String = "no_title"
    var projectId = 1 //inbox
    var priorityLevel = PriorityLevel.P4
    var dueDate: LocalDate? = null
    var dateToDo: LocalDate? = null

    override fun addId(newId: Int) {
        this.id = newId
    }

    override fun addTitle(newTitle: String) {
        this.title = newTitle
        returnT()
    }


    fun addProjectId(newProjectID: Int) {
        this.projectId = newProjectID
        returnT()
    }

    fun addPriorityLevel(newPriorityLevel: PriorityLevel) {
        this.priorityLevel = newPriorityLevel
        returnT()
    }

    fun addDueDate(newDate: LocalDate?) {
        this.dueDate = newDate
        returnT()
    }

    fun addDateToDo(newDate: LocalDate?) {
        this.dateToDo = newDate
        returnT()
    }

    override fun returnT(): Task {
        return Task(id, title, projectId, priorityLevel, dueDate, dateToDo)
    }
}

class TaskProjectBuilder : Builders<TaskProject> {
    override var id: Int = 1
    override var title: String = "no_title"

    override fun addId(newId: Int) {
        id = newId
    }

    override fun addTitle(newTitle: String) {
        title = newTitle
    }

    override fun returnT(): TaskProject {
        return TaskProject(id, title)
    }


}



