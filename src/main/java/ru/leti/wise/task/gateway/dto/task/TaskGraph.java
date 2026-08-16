package ru.leti.wise.task.gateway.dto.task;

import lombok.Getter;
import lombok.Setter;
import ru.leti.wise.task.gateway.dto.graph.Graph;

import java.util.List;

@Setter
@Getter
public class TaskGraph extends Task {
    private boolean isHiddenMistake;
    private Graph graph;
    private Rule rule;
    private List<PluginInfo> condition;
}
