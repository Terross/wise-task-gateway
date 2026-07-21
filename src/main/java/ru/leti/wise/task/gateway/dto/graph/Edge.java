package ru.leti.wise.task.gateway.dto.graph;

public record Edge(
        int source,
        int target,
        int weight,
        String label,
        Color color
) {}