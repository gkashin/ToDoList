package com.gkashin.todolist;

public final class TaskException extends Exception {
    private String errorMsg;

    public TaskException(TaskExceptionCodes code) {
        this.errorMsg = code.getErrorMsg();
    }

    public void printErrorMsg() {
        System.out.println(errorMsg);
    }
}