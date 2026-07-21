package ru.leti.wise.task.gateway.dto.graph;

public record EdgeInput(
        int source,
        int target,
        Integer weight,
        String label,
        Color color
) {}