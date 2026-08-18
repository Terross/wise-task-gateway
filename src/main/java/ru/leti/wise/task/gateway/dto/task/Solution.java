package ru.leti.wise.task.gateway.dto.task;


public interface Solution {
    String id();
    String taskId();
    String authorId();
    boolean isCorrect();
}