package MenuPackage

import CreatePackage.CreationFunctions

class Menu(
    private val viewTaskSelectionNum: Int,
    private val addTaskSelectionNum: Int,
    private val editTaskSelectionNum: Int,
    private val deleteTaskSelectionNum: Int,
    private val addProjectSelectionNum: Int,
    private val deleteProjectSelectionNum: Int,
    private val viewCompletedTasksSelectionNum: Int,
    private val exitSelectionNum: Int
) {


    val creator = CreationFunctions()

    fun userMenu(): Int {


        println(
            creator.createDashes(20) +
                    "\nPress [$viewTaskSelectionNum] to view tasks in a project\n" +
                    "Press [$addTaskSelectionNum] to add a task\n" +
                    "Press [$editTaskSelectionNum] to edit a task\n" +
                    "Press [$deleteTaskSelectionNum] to complete/delete a task from a project\n" +
                    "Press [$addProjectSelectionNum] to add a new project\n" +
                    "Press [$deleteProjectSelectionNum] to delete a project\n" +
                    "Press [$viewCompletedTasksSelectionNum] to view your completed tasks\n" +

                    "Press [$exitSelectionNum] to exit\n${creator.createDashes(20)}"

        )


        when (readln()) {
            "$viewTaskSelectionNum" -> return viewTaskSelectionNum
            "$addTaskSelectionNum" -> return addTaskSelectionNum
            "$editTaskSelectionNum" -> return editTaskSelectionNum
            "$deleteTaskSelectionNum" -> return deleteTaskSelectionNum
            "$addProjectSelectionNum" -> return addProjectSelectionNum
            "$deleteProjectSelectionNum" -> return deleteProjectSelectionNum
            "$viewCompletedTasksSelectionNum" -> return viewCompletedTasksSelectionNum
            "$exitSelectionNum" -> return exitSelectionNum
        }
        return -1
    }
}
