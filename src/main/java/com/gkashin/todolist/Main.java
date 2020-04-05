package com.gkashin.todolist;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        ToDoList toDoList;

        // Create toDoList
        try {
            toDoList = new ToDoList();
        } catch (TaskException ex) {
            ex.printErrorMsg();
            return;
        }

        Scanner scan = new Scanner(System.in);
        System.out.println("What would you like to do?\nEnter 'add {name}', 'read', 'mark_done {name}', 'read_current', 'delete {name}' or 'close'");

        while (true) {
            // Scan input
            String s = scan.nextLine();

            String command = s;
            String taskName = "";

            // Split input to 'command' and 'taskName'
            if (s.contains(" ")) {
                command = s.substring(0, s.indexOf(' '));
                taskName = s.substring(s.indexOf(' ') + 1);
            }

            try {
                switch (command) {
                    case "add":
                        toDoList.addTask(new Task(taskName, false));
                        break;
                    case "read":
                        toDoList.print();
                        break;
                    case "mark_done":
                        toDoList.markDone(taskName);
                        break;
                    case "delete":
                        toDoList.deleteTask(taskName);
                        break;
                    case "read_current":
                        toDoList.printCurrentTasks();
                        break;
                    case "close":
                        toDoList.writeTasks();
                        System.out.println("Goodbye!");
                        System.exit(0);
                    default:
                        System.out.println("Sorry. Our system doesn't support command '" + command + "'");
                        System.out.println("Try something like: 'add {name}', 'read', 'mark_done {name}', 'read_current', 'delete {name}' or 'close' :)");
                }
            } catch (TaskException ex) {
                ex.printErrorMsg();
            }
        }
    }
}