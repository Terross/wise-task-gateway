package ru.leti.wise.task.gateway.dto.task;

public record TaskImplementationInput(
        String id,
        String name,
        String description,
        String category,
        TaskType taskType,
        String authorId,
        boolean isPublic,
        String pluginId
) {}