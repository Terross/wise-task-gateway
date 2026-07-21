package ru.leti.wise.task.gateway.dto.graph;

import java.util.List;

public record Graph(
        String id,
        int vertexCount,
        int edgeCount,
        boolean isDirect,
        List<Vertex> vertexList,
        List<Edge> edgeList,
        boolean isNamed,
        String name
) {}