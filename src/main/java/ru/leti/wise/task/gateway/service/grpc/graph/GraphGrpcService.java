package ru.leti.wise.task.gateway.service.grpc.graph;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.leti.graphql.model.CreateGraphRequest;
import ru.leti.graphql.model.GenerateGraphRequest;
import ru.leti.graphql.model.GraphInput;
import ru.leti.wise.task.gateway.mapper.GraphMapper;
import ru.leti.wise.task.graph.GraphGrpc;
import ru.leti.wise.task.graph.GraphOuterClass.Graph;

import java.util.UUID;


@Component
@RequiredArgsConstructor
public class GraphGrpcService {

    private final GraphStubHolder graphStubHolder;
    private final GraphMapper graphMapper;


    public Graph createGraph(GraphInput graph) {
        var request = GraphGrpc.CreateGraphRequest.newBuilder()
                .setGraph(graphMapper.toGraph(graph))
                .build();

        return graphStubHolder.get().createGraph(request).getGraph();
    }

    public Graph generateGraph(GenerateGraphRequest generateGraphRequest) {
        var request = graphMapper.toGenerateGraphRequest(generateGraphRequest);
        return graphStubHolder.get().generateRandomGraph(request).getGraph();
    }

    public Graph getGraphById(String id) {
        var request = GraphGrpc.GetGraphByIdRequest.newBuilder()
                .setId(id)
                .build();

        return graphStubHolder.get().getGraphById(request).getGraph();
    }
}
