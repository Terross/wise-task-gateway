package ru.leti.wise.task.gateway.dto.task;

import ru.leti.wise.task.gateway.dto.graph.GraphInput;

import java.util.List;

public record SolutionGraphInput(
        String id,
        String taskId,
        String authorId,
        GraphInput graph,
        List<PluginStringInput> pluginsInput
) {}