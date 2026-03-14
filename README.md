# CourseMap

Visualizes CS and Math course prerequisites as a directed acyclic graph (DAG).

## Running

```bash
./gradlew run
```

This generates `app/out/course-map.dot`.

## Viewing the output

Install [Graphviz](https://graphviz.org/download/), then run:

```bash
dot -Tpng app/out/course-map.dot -o app/out/dag.png
```

## Color key

| Color  | Meaning                   |
|--------|---------------------------|
| Green  | Completed                 |
| Yellow | Available to take now     |
| White  | Prerequisites not yet met |
