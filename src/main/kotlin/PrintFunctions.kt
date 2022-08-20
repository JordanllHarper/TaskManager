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
}