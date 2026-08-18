package ru.leti.wise.task.gateway.dto.task;

import ru.leti.wise.task.gateway.dto.graph.Graph;

import java.util.List;

public record SolutionGraph(
        String id,
        String taskId,
        String authorId,
        boolean isCorrect,
        Graph graph,
        List<PluginResult> pluginResults
) implements Solution {
}