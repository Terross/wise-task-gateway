package ru.leti.wise.task.gateway.dto.task;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class Task {
    private String id;
    private String name;
    private String description;
    private String category;
    private TaskType taskType;
    private String authorId;
    private boolean isPublic;
}