import java.time.LocalDate
import java.time.format.DateTimeFormatter

interface TaskInterface {
    val taskID: Int
    var title: String
    var projectId: Int
    var priorityLevel: PriorityLevel


}

open class Task(
    override val taskID: Int = 1,
    override var title: String = "no_title",
    override var projectId: Int = 1,
    override var priorityLevel: PriorityLevel = PriorityLevel.P4,
    private val dueDate: LocalDate? = null,
    private var dateToDo: LocalDate? = null,

    val localDate: DateTimeFormatter? = DateTimeFormatter.ofPattern("dd MM yyyy")


) : TaskInterface {

    val dueDateformatted: String
        get() {
            return if (dueDate == null) {
                ("No date")
            } else {
                var dueDateString: String = dueDate.format(localDate).trim()
                for (character in dueDateString) {
                    if (character == ' ') {
                        dueDateString = dueDateString.replace(character, '/')
                    }
                }
                return dueDateString
            }
        }


    val dateToDoFormatted: String
        get() {
            return if (dateToDo == null) {
                ("No date to do")
            } else {
                dateToDo.toString()
            }
        }


    override fun toString(): String {
        return "Title: $title\nDue Date: $dueDateformatted\nPriority: $priorityLevel"
    }


}


class subTask(taskID: Int, title: String, projectId: Int, priorityLevel: PriorityLevel) :
    Task(taskID, title, projectId, priorityLevel) {
    private var sublevel: Int = 1
    fun printDetails() = println("Title: $title\nPriority: $priorityLevel\nLevel:$sublevel")
}