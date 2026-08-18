package ru.leti.wise.task.gateway.dto.task;

import ru.leti.wise.task.gateway.dto.graph.Graph;

import java.util.List;

public record TaskGraph(
        String id,
        String name,
        String description,
        String category,
        TaskType taskType,
        String authorId,
        boolean isPublic,
        boolean isHiddenMistake,
        Graph graph,
        Rule rule,
        List<PluginInfo> condition
) implements Task {
}
