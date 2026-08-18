package ru.leti.wise.task.gateway.dto.task;

import java.util.List;

public record SolutionImplementation(
        String id,
        String taskId,
        String authorId,
        boolean isCorrect,
        List<GraphResult> implementationResult
) implements Solution {
}