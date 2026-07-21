package ru.leti.wise.task.gateway.dto.graph;

import java.util.List;

public record GraphInput(
        String id,
        int vertexCount,
        int edgeCount,
        boolean isDirect,
        List<VertexInput> vertexList,
        List<EdgeInput> edgeList,
        boolean isNamed,
        String name
) {}