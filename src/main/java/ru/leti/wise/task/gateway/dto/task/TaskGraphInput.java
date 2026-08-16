package ru.leti.wise.task.gateway.dto.task;

import ru.leti.wise.task.gateway.dto.graph.GraphInput;

public record TaskGraphInput(
        String id,
        String name,
        String description,
        String category,
        TaskType taskType,
        String authorId,
        boolean isPublic,
        boolean isHiddenMistake,
        GraphInput graph,
        RuleInput rule,
        java.util.List<PluginInfoInput> condition
) {}