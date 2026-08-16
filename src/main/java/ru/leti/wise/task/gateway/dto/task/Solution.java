package ru.leti.wise.task.gateway.dto.task;

import lombok.Getter;

@Getter
public abstract class Solution {
    private String id;
    private String taskId;
    private String authorId;
    private boolean isCorrect;
}