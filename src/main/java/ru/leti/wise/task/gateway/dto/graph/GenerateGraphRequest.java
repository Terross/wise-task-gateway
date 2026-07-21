package ru.leti.wise.task.gateway.dto.graph;

public record GenerateGraphRequest(
        int vertexCount,
        int edgeCount,
        boolean isDirect,
        boolean isSaved
) {}