# CourseMap

Visualizes CS and Math course prerequisites as a directed acyclic graph (DAG).

## Running

```bash
./gradlew run
```

This generates `out/course-map.dot`.

## Viewing the output

Install [Graphviz](https://graphviz.org/download/), then run:

```bash
dot -Tpng out/course-map.dot -o out/course-map.png
```

## Color key

| Color  | Meaning                   |
|--------|---------------------------|
| Green  | Completed                 |
| Yellow | Available to take now     |
| White  | Prerequisites not yet met |
