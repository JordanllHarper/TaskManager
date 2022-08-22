package SelectPackage

import Task
import Enums.TaskAttributes
import TaskProject
import java.lang.Exception
import kotlin.collections.ArrayList

class SelectFunctions() {


    fun selectProject(projectIdWanted: Int, projectList: ArrayList<TaskProject>): TaskProject {

        for (project in projectList) {
            if (projectIdWanted == project.projectID) {
                return project
            }
        }
        throw Exception()
    }

    fun selectTask(taskIdWanted: Int, taskList: ArrayList<Task>): Task? {
        for (task in taskList) {
            if (taskIdWanted == task.taskID) {
                return task
            }
        }
        return null
    }


    fun getUserInput(item: String): String {
        println("Select a $item")
        return readln()
    }


    fun getNextAvailableProjectId(projectList: ArrayList<TaskProject>): Int {
        return projectList.get(projectList.size - 1).projectID + 1
    }

    fun selectAttributeToEdit(task: Task): TaskAttributes {
        println("Which attribute would you like to edit?")
        while (true) {
            println(task)
            when (readln().trim().lowercase()) {
                "title" -> return TaskAttributes.TITLE
                "priority level" -> return TaskAttributes.PRIORITYLEVEL
                "due date" -> return TaskAttributes.DUEDATE
                "date to do" -> return TaskAttributes.DATETODO
            }
            println("Invalid select, please try again")
        }

    }
}