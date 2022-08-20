class PrintFunctions() {

    fun getProjectPrompt(projectList: ArrayList<TaskProject>) {
        println("Select a project: ")
        printAllProjects(projectList)
    }

    private fun printAllProjects(projectList: ArrayList<TaskProject>) {
        for (TaskProject in projectList) {
            println("ID: ${TaskProject.projectID}\n${TaskProject.title}")
        }
    }
}