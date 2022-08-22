class CreateProject {
    val selector = SelectFunctions()

    fun getTitleFromInput(): String {
        println("Give your project a name: ")
        return readln()
    }

    fun createProject(projectList: ArrayList<TaskProject>): TaskProject {
        val projectBuilder = TaskProjectBuilder()

        val projectTitleInput = getTitleFromInput()

        if (projectTitleInput.isNotBlank()) {
            projectBuilder.addTitle(readln().trim())
        }

        projectBuilder.addId(selector.getNextAvailableProjectId(projectList))
        return TaskProjectFactory.createProject(projectBuilder)
    }
}