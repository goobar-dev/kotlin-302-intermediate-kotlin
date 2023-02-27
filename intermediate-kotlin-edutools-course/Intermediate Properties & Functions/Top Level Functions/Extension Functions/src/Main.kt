/**
 * Review the Description Tool Window for more
 * exercise details
 */

data class TaskEntity(val id: String, val expiration: Long, val title: String)
data class Task(val id: String, val title: String)

private fun TaskEntity.toTask() = Task(this.id, this.title)

fun main() {
    val entity = TaskEntity("123abc", 98477443L, "Local Parks Survey")
    val task = entity.toTask()
}
