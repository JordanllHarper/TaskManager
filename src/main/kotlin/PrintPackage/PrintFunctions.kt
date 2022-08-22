package PrintPackage

import Task
import TaskProject

class PrintFunctions() {

    fun getProjectPrompt(projectList: ArrayList<TaskProject>) {
        println("Select a project: ")
        printAllProjects(projectList)
    }

    private fun printAllProjects(projectList: ArrayList<TaskProject>) {

        for (project in projectList) {
            println(project)
        }
    }

    fun getTaskPrompt(taskList: ArrayList<Task>) {
        for (task in taskList) {
            println(task)
        }
    }
}