package org.vld.fp.sfpp.ch07

/**
 * Task to be done
 */
data class Task(val name: String)

/**
 * [Task] result
 */
typealias Result = String

/**
 * Handler to do the [Task]
 */
interface Handler {
    /**
     * Handler name
     */
    val name: String
    /**
     * Supported tasks
     */
    val supportedTasks: List<Task>
    /**
     * Handle the task if it is supported or return null if the task is not supported
     */
    fun handle(task: Task): Result? = if (task in supportedTasks) "$name finished ${task.name}" else null
}

/**
 * Analyst [Handler]
 */
class Analyst(override val name: String, override val supportedTasks: List<Task>) : Handler

/**
 * Architect [Handler]
 */
class Architect(override val name: String, override val supportedTasks: List<Task>) : Handler

/**
 * Developer [Handler]
 */
class Developer(override val name: String, override val supportedTasks: List<Task>) : Handler

fun main(args: Array<String>) {
    // list of tasks to be done
    val tasks = listOf(
            Task("Gather business requirements"), Task("Prepare user stories"),
            Task("Define system architecture"), Task("Prepare technical design"),
            Task("Develop software system"), Task("Test software system"),
            Task("Deploy software system")
    )

    // team of an analyst, an architect and a developer
    val analyst = Analyst("Analyst", listOf(Task("Gather business requirements"), Task("Prepare user stories")))
    val architect = Architect("Architect", listOf(Task("Define system architecture"), Task("Prepare technical design")))
    val developer = Developer("Developer", listOf(Task("Develop software system"), Task("Test software system")))

    // build the team to do a task and produce a result
    fun handleTask(task: Task): Result? =
            analyst.handle(task)
            ?: developer.handle(task)
            ?: architect.handle(task)
            // default task handler
            ?: "Nobody han handle ${task.name}"

    // instruct the team to do the list of tasks and produce the list of results
    val results = tasks.map(::handleTask)
    results.forEach(::println)
}
