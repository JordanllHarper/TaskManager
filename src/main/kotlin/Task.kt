import java.time.LocalDate
import java.time.format.DateTimeFormatter

interface TaskInterface {
    val taskID: Int
    var title: String
    var projectId: Int
    var priorityLevel: PriorityLevel


}

object TaskFactory {
    fun createTask(taskBuilder: TaskBuilder): Task {
        return taskBuilder.returnT()
    }
}

open class Task(
    override val taskID: Int = 1,
    override var title: String = "no_title",
    override var projectId: Int = 1,
    override var priorityLevel: PriorityLevel = PriorityLevel.P4,
    var dueDate: LocalDate? = null,
    var dateToDo: LocalDate? = null,

    val localDate: DateTimeFormatter? = DateTimeFormatter.ofPattern("dd MM yyyy")


) : TaskInterface {


    fun getDateFormatted(dateToFormat: LocalDate?): String {
        return if (dateToFormat == null) {
            ("No date")
        } else {
            var dueDateString: String = dateToFormat.format(localDate)
            for (character in dueDateString) {
                if (character == ' ') {
                    dueDateString = dueDateString.replace(character, '/')
                }
            }
            dueDateString
        }
    }


    override fun toString(): String {

        return "[${taskID}] -- $title\n    |-> Due Date: ${getDateFormatted(dueDate)}\n" +
                "    |-> Date to do: ${getDateFormatted(dateToDo)}\n" +
                "    |-> Priority: $priorityLevel"

    }


}


class subTask(taskID: Int, title: String, projectId: Int, priorityLevel: PriorityLevel) :
    Task(taskID, title, projectId, priorityLevel) {
    private var sublevel: Int = 1
    fun printDetails() = println("Title: $title\nPriority: $priorityLevel\nLevel:$sublevel")
}