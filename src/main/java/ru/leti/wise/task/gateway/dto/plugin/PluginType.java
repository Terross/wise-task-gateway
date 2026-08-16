package ru.leti.wise.task.gateway.dto.plugin;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PluginType {
    GRAPH_PROPERTY("GRAPH_PROPERTY"),
    GRAPH_CHARACTERISTIC("GRAPH_CHARACTERISTIC"),
    GRAPH_NEW_GRAPH("GRAPH_NEW_GRAPH"),
    GRAPH_STRING("GRAPH_STRING");

    private final String value;
}