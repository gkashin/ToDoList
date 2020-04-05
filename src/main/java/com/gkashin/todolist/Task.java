package com.gkashin.todolist;

import java.util.Objects;

public final class Task {
    private String name;
    private Boolean isDone;

    public Task(String name, Boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public String getName() {
        return name;
    }

    public Boolean getDone() {
        return isDone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(name, task.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}