package com.gkashin.todolist;

public enum TaskExceptionCodes {
    TASK_LIST_IS_EMPTY(0, "Your task list is empty"),
    CURRENT_TASKS_ARE_EMPTY(1, "You have no current tasks"),
    TASK_NOT_FOUND(2, "There is no such task in your list"),
    TASK_EXISTS(3, "You already have this task"),
    TASK_LIST_OVERFLOW(4, "There are too many data in the file\nFile must contain no more than 100_000 tasks\nPlease, delete some tasks and try again :)");

    private final int id;
    private final String msg;

    TaskExceptionCodes(int id, String msg) {
        this.id = id;
        this.msg = msg;
    }

    public String getErrorMsg() {
        return msg;
    }
}
