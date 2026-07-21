package ru.leti.wise.task.gateway.dto.graph;

public record VertexInput(
        int id,
        Integer weight,
        String label,
        int xCoordinate,
        int yCoordinate,
        Color color
) {}