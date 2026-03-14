package io.github.joeweh.coursemap

import java.nio.file.Files
import java.nio.file.Path

fun main() {
    val dag = DAG()

    val cs1800 = Course(Department.CS, 1800, "Discrete Structures")
    val cs2500 = Course(Department.CS, 2500, "Fundamentals of CS 1")
    val cs2510 = Course(Department.CS, 2510, "Fundamentals of CS 2")
    val cs3500 = Course(Department.CS, 3500, "Object-Oriented Design")
    val cs3000 = Course(Department.CS, 3000, "Algorithms and Data")
    val cs4400 = Course(Department.CS, 4400, "Programming Languages")
    val cs4500 = Course(Department.CS, 4500, "Software Development")

    dag.addCourse(cs1800)
    dag.addCourse(cs2500)
    dag.addCourse(cs2510)
    dag.addCourse(cs3500)
    dag.addCourse(cs3000)
    dag.addCourse(cs4400)
    dag.addCourse(cs4500)

    dag.addEdge(cs2500, cs2510)
    dag.addEdge(cs2510, cs3500)
    dag.addEdge(cs2510, cs3000)
    dag.addEdge(cs1800, cs3000)
    dag.addEdge(cs3500, cs4400)
    dag.addEdge(cs3500, cs4500)
    dag.addEdge(cs3000, cs4500)

    val math1341 = Course(Department.MATH, 1341, "Calculus 1")
    val math1342 = Course(Department.MATH, 1342, "Calculus 2")
    val math2321 = Course(Department.MATH, 2321, "Calculus 3")
    val math2331 = Course(Department.MATH, 2331, "Linear Algebra")
    val math3081 = Course(Department.MATH, 3081, "Probability and Statistics")

    dag.addCourse(math1341)
    dag.addCourse(math1342)
    dag.addCourse(math2321)
    dag.addCourse(math2331)
    dag.addCourse(math3081)

    dag.addEdge(math1341, math1342)
    dag.addEdge(math1342, math2321)
    dag.addEdge(math1342, math2331)
    dag.addEdge(math1342, math3081)

    val completed = setOf(cs2500, cs2510)

    val outDir = Path.of("out")
    Files.createDirectories(outDir)
    Files.writeString(outDir.resolve("course-map.dot"), dag.toDot(completed))
}
