package ru.leti.wise.task.gateway.dto.task;

public record TaskImplementation(
        String id,
        String name,
        String description,
        String category,
        TaskType taskType,
        String authorId,
        boolean isPublic,
        String pluginId
) implements Task {
}