package ru.leti.wise.task.gateway.dto.task;

public record SolutionImplementationInput(
        String id,
        String taskId,
        String authorId,
        String code
) {}