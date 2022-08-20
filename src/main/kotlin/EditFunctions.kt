class EditFunctions {
    val selector = SelectFunctions()
    val creator = CreateFunctions()

    fun editTask(project: TaskProject) {

        println("Which task do you want to edit?")
        val task = selector.selectTaskWithValidation(project)

        println("Which attribute do you want to edit?")
        val attributeToEdit = selector.selectAttributeToEdit(task)
        when (attributeToEdit) {
            TaskAttributes.TITLE -> {
                println("Type in the new title")
                task.title = readln()
            }

            TaskAttributes.PRIORITYLEVEL -> {
                println("Type the new priority level")
                task.priorityLevel = selector.determinePriorityLevel(readln())
            }

            TaskAttributes.DUEDATE -> {
                println("Type the new due date in DD/MM/YYYY format")
                val newDueDate = creator.createDate()
                task.dueDate = newDueDate
            }

            TaskAttributes.DATETODO -> {
                val newDateToDo = creator.createDate()
                task.dateToDo = newDateToDo
            }
        }

    }
}