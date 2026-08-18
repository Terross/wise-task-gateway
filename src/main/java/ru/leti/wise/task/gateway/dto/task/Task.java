package ru.leti.wise.task.gateway.dto.task;

import lombok.Getter;
import lombok.Setter;


public interface Task {
    String id();
    String name();
    String description();
    String category();
    TaskType taskType();
    String authorId();
    boolean isPublic();
}