package ru.leti.wise.task.gateway.dto.task;

import lombok.Getter;
import lombok.Setter;
import ru.leti.wise.task.gateway.dto.graph.Graph;

import java.util.List;

@Getter
@Setter
public class SolutionGraph extends Solution {
    private Graph graph;
    private List<PluginResult> pluginResults;
}