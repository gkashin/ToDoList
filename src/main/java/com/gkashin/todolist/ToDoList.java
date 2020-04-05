package com.gkashin.todolist;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

public final class ToDoList {
    // All the tasks
    private HashSet<Task> tasks = new HashSet<Task>();
    // Only not completed tasks
    private HashSet<Task> currentTasks = new HashSet<Task>();

    public ToDoList() throws TaskException {
        JSONParser jsonParser = new JSONParser();

        // Read tasks from the file
        try (FileReader reader = new FileReader("todo-list.json")) {
            Object obj = jsonParser.parse(reader);
            JSONArray tasks = (JSONArray) obj;

            if (tasks.size() > 100_000) {
                throw new TaskException(TaskExceptionCodes.TASK_LIST_OVERFLOW);
            }

            // Save each task
            tasks.forEach(taskObject -> readTask((JSONObject) taskObject));
        } catch (IOException | ParseException e) {
            // If file is empty or doesn't exist, just return
            return;
        }
    }

    private void readTask(JSONObject taskObject) {
        JSONObject task = (JSONObject) taskObject.get("task");
        String name = (String) task.get("name");
        Boolean isDone = (Boolean) task.get("isDone");

        Task next = new Task(name, isDone);
        // Not completed tasks add to 'currentTasks'
        if (!isDone) {
            currentTasks.add(next);
        }
        // All the tasks add to 'tasks'
        tasks.add(next);
    }

    public void print() throws TaskException {
        if (tasks.isEmpty()) {
            throw new TaskException(TaskExceptionCodes.TASK_LIST_IS_EMPTY);
        } else {
            tasks.forEach(task -> System.out.println(task.getName() + ": " + (task.getDone() ? "done" : "not done")));
            System.out.println();
        }
    }

    public void addTask(Task task) throws TaskException {
        if (tasks.size() > 100_000) {
            throw new TaskException(TaskExceptionCodes.TASK_LIST_OVERFLOW);
        } else if (tasks.contains(task)) {
            throw new TaskException(TaskExceptionCodes.TASK_EXISTS);
        } else {
            // Add task to both 'tasks' and 'currentTasks' because it is not completed yet
            tasks.add(task);
            currentTasks.add(task);
            System.out.println("You have successfully added task '" + task.getName() + "'!\n");
        }
    }

    public void deleteTask(String name) throws TaskException {
        if (tasks.isEmpty()) {
            throw new TaskException(TaskExceptionCodes.TASK_LIST_IS_EMPTY);
        } else {
            Task task = new Task(name, false);
            if (tasks.contains(task)) {
                // Delete task from both 'tasks' and 'currentTasks' if it exists
                tasks.remove(task);
                currentTasks.remove(task);
                System.out.println("You have successfully deleted task '" + task.getName() + "'!\n");
            } else {
                throw new TaskException(TaskExceptionCodes.TASK_NOT_FOUND);
            }
        }
    }

    public void markDone(String name) throws TaskException {
        if (tasks.isEmpty()) {
            throw new TaskException(TaskExceptionCodes.TASK_LIST_IS_EMPTY);
        } else {
            Task task = new Task(name, true);
            if (tasks.contains(task)) {
                // Remove task with false 'isDone' field from 'tasks' and add task with true 'isDone' field
                tasks.remove(task);
                tasks.add(task);
                // Also delete task from 'currentTasks' since it is already completed
                currentTasks.remove(task);
                System.out.println("You have successfully marked task '" + task.getName() + "' as done!\n");
            } else {
                throw new TaskException(TaskExceptionCodes.TASK_NOT_FOUND);
            }
        }
    }

    public void writeTasks() {
        JSONArray taskList = new JSONArray();

        // Add tasks to the JSONArray
        for (Task task : tasks) {
            JSONObject taskDetails = new JSONObject();
            JSONObject taskObject = new JSONObject();

            taskDetails.put("name", task.getName());
            taskDetails.put("isDone", task.getDone());

            taskObject.put("task", taskDetails);

            taskList.add(taskObject);
        }

        // Write tasks to the file
        try (FileWriter writer = new FileWriter("todo-list.json")) {
            System.out.println("Saving your tasks...");
            writer.write(taskList.toJSONString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printCurrentTasks() throws TaskException {
        if (currentTasks.isEmpty()) {
            throw new TaskException(TaskExceptionCodes.CURRENT_TASKS_ARE_EMPTY);
        } else {
            currentTasks.forEach(task -> System.out.println(task.getName()));
            System.out.println();
        }
    }

    public HashSet<Task> getCurrentTasks() {
        return currentTasks;
    }
    public HashSet<Task> getTasks() {
        return tasks;
    }
}
