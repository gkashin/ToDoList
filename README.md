# ToDoList
Test task for JetBrains Internship 2020 using *JSONSimple* and *JUnit* libraries

## Description
The Application stores a list of tasks in the json-file and allows:
* Read all tasks from the list
* Add a new task
* Mark the task as completed
* Delete task from the list
* Read the tasks to be completed

Format of the JSON-file looks as follows:
```
[
  { 
    "task1": {
        "name": {name},
        "isDone": {true/false}
    }
  },
  {
    "task2": {
        "name": {name},
        "isDone": {true/false}
    }
  }
]
```

## Getting Started
To run from the command line:
```
cd {Project_Folder}/out/artifacts/ToDoList_jar
java -jar ToDoList.jar
```

## Example
![](https://b.radikal.ru/b28/2004/32/ac695f81b417.png)
