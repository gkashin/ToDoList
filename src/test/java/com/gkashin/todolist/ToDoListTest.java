package com.gkashin.todolist;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ToDoListTest {

    private ToDoList toDoList;

    @Before
    public void setUp() throws TaskException {
        toDoList = new ToDoList();
    }

    // MARK: - Test Adding
    @Test(expected = TaskException.class)
    public void testAddingExisting() throws TaskException {
        Task task = new Task("buy milk", false);
        ToDoList toDoList = new ToDoList();

        toDoList.addTask(task);
        toDoList.addTask(task);
    }

    @Test(expected = TaskException.class)
    public void testOverflow() throws TaskException {
        for (int i = 0; i < 100002; ++i) {
            Task task = new Task("milk" + String.valueOf(i), false);
            toDoList.addTask(task);
        }
    }

    // MARK: - Test Deletion
    @Test(expected = TaskException.class)
    public void testDeletionFromEmpty() throws TaskException {
        toDoList.deleteTask("task");
    }

    @Test(expected = TaskException.class)
    public void testDeletionNotExisting() throws TaskException {
        toDoList.addTask(new Task("task", false));
        toDoList.deleteTask("task1");
    }

    @Test
    public void testDeletion() throws TaskException {
        Task task = new Task("task", false);
        toDoList.addTask(task);
        toDoList.deleteTask(task.getName());

        assertEquals(toDoList.getTasks().contains(task), false);
    }

    // MARK: - Test Marking Done
    @Test
    public void testMarkingDone() throws TaskException {
        Task task = new Task("task", false);
        toDoList.addTask(task);
        toDoList.markDone(task.getName());

        assertEquals(toDoList.getCurrentTasks().contains(task), false);
    }

    @Test(expected = TaskException.class)
    public void testMarkingDoneNotExisting() throws TaskException {
        Task task = new Task("task", false);
        toDoList.addTask(task);
        toDoList.markDone("task1");
    }
}