package ru.leti.wise.task.gateway.dto.graph;

public record Vertex(
        int id,
        int weight,
        String label,
        int xCoordinate,
        int yCoordinate,
        Color color
) {}