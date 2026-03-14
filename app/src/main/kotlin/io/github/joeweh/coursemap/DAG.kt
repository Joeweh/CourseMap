package io.github.joeweh.coursemap

import java.util.LinkedList

class DAG(
    private val prerequisites: MutableMap<Course, MutableSet<Course>> = mutableMapOf(),
    private val dependents: MutableMap<Course, MutableSet<Course>> = mutableMapOf()
) {
    fun addCourse(course: Course) {
        prerequisites.putIfAbsent(course, mutableSetOf())
        dependents.putIfAbsent(course, mutableSetOf())
    }

    fun addEdge(prerequisite: Course, dependent: Course) {
        prerequisites.getOrPut(dependent) { mutableSetOf() }.add(prerequisite)
        dependents.getOrPut(prerequisite) { mutableSetOf() }.add(dependent)
    }

    private fun courseExists(course: Course) =
        prerequisites.containsKey(course) && dependents.containsKey(course)

    private fun getAdjacencyList(adjacencyList: Map<Course, Set<Course>>, course: Course): Set<Course> {
        if (!courseExists(course)) throw IllegalArgumentException("Course not found: $course")
        return adjacencyList[course]!!
    }

    fun getPrerequisites(course: Course) = getAdjacencyList(prerequisites, course)
    fun getDependents(course: Course) = getAdjacencyList(dependents, course)

    fun getPrerequisitesRecursive(course: Course): List<Set<Course>> {
        if (!courseExists(course)) throw IllegalArgumentException("Course not found: $course")

        val result = mutableListOf<Set<Course>>()
        val visited = mutableSetOf<Course>()
        val queue: LinkedList<Course> = LinkedList(getPrerequisites(course))

        while (queue.isNotEmpty()) {
            val levelSize = queue.size
            val levelCourses = mutableSetOf<Course>()

            repeat(levelSize) {
                val current = queue.poll()
                if (current !in visited) {
                    visited.add(current)
                    levelCourses.add(current)
                    queue.addAll(getPrerequisites(current))
                }
            }

            result.add(levelCourses)
        }

        return result
    }

    fun toDot(completed: Set<Course>): String {
        val sb = StringBuilder()
        sb.append("digraph {\n    rankdir=LR;\n")

        for (course in prerequisites.keys) {
            sb.append("    \"$course\" [label=\"$course\\n${course.title}\"")
            if (course in completed) {
                sb.append(" style=filled fillcolor=green")
            } else if (prerequisites[course]!!.all { it in completed }) {
                sb.append(" style=filled fillcolor=yellow")
            }
            sb.append("];\n")
        }

        for (course in prerequisites.keys) {
            for (prerequisite in prerequisites[course]!!) {
                sb.append("    \"$prerequisite\" -> \"$course\";\n")
            }
        }

        sb.append("}\n")
        return sb.toString()
    }
}
