package io.github.joeweh.coursemap

import java.util.Objects

class Course(
    val department: Department,
    val courseCode: Int,
    val title: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Course) return false
        return courseCode == other.courseCode && department == other.department
    }

    override fun hashCode() = Objects.hash(department, courseCode)

    override fun toString() = "$department $courseCode"
}
