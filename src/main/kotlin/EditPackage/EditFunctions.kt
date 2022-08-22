package EditPackage

import CreatePackage.CreationFunctions
import PriorityLevelSelector
import SelectPackage.SelectFunctions
import Enums.TaskAttributes
import TaskProject
import TaskSelector

class EditFunctions {
    val selector = SelectFunctions()
    val creator = CreationFunctions()

    fun editTask(project: TaskProject) {

        println("Which task do you want to edit?")
        val task = TaskSelector().selectItem(project.tasksInProject)

        println("Which attribute do you want to edit?")
        val attributeToEdit = selector.selectAttributeToEdit(task)
        when (attributeToEdit) {
            TaskAttributes.TITLE -> {
                println("Type in the new title")
                task.title = readln()
            }

            TaskAttributes.PRIORITYLEVEL -> {
                println("Type the new priority level")
                task.priorityLevel = PriorityLevelSelector().selectPriorityLevel(readln())
            }

            TaskAttributes.DUEDATE -> {
                println("Type the new due date in DD/MM/YYYY format")
                val newDueDate = creator.createDateWithoutTime(readln())
                task.dueDate = newDueDate
            }

            TaskAttributes.DATETODO -> {
                val newDateToDo = creator.createDateWithoutTime(readln())
                task.dateToDo = newDateToDo
            }
        }

    }
}