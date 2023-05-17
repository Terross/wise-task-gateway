package ru.leti.wise.task.gateway.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import ru.leti.graphql.model.*;
import ru.leti.wise.task.graph.GraphGrpc;
import ru.leti.wise.task.graph.GraphOuterClass;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface GraphMapper {

    GraphOuterClass.Graph toGraph(GraphInput graph);

    GraphOuterClass.Vertex toVertex(VertexInput vertex);

    GraphOuterClass.Edge toEdge(EdgeInput edge);

    Graph toGraph(GraphOuterClass.Graph graph);

    Vertex toVertex(GraphOuterClass.Vertex vertex);

    Edge toEdge(GraphOuterClass.Edge edge);

    GraphGrpc.GenerateGraphRequest toGenerateGraphRequest(GenerateGraphRequest generateGraphRequest);

    default GraphOuterClass.Color toColor(Color color) {
        return GraphOuterClass.Color.valueOf(color.name());
    }

    default Color toColor(GraphOuterClass.Color color) {
        return Color.valueOf(color.name());
    }
}
