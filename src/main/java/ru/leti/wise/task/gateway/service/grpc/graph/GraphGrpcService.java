package ru.leti.wise.task.gateway.service.grpc.graph;

import com.google.protobuf.Empty;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.leti.graphql.model.GenerateGraphRequest;
import ru.leti.graphql.model.GraphInput;
import ru.leti.wise.task.gateway.mapper.GraphMapper;
import ru.leti.wise.task.graph.GraphGrpc;
import ru.leti.wise.task.graph.GraphOuterClass.Graph;

import java.util.List;
import java.util.UUID;


@Component
@Observed
@RequiredArgsConstructor
public class GraphGrpcService {

    private final GraphStubHolder graphStubHolder;
    private final GraphMapper graphMapper;


    public Graph createGraph(GraphInput graph, String userId) {
        Graph baseGraph = graphMapper.toGraph(graph, userId);

        var request = GraphGrpc.CreateGraphRequest.newBuilder()
                .setGraph(baseGraph)
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

    public List<Graph> getGraphLibrary() {
        return graphStubHolder.get().getGraphLibrary(Empty.newBuilder().build()).getGraphListList();
    }

    public String deleteGraph(String id) {
        var request = GraphGrpc.RemoveGraphRequest.newBuilder()
                .setId(id)
                .build();

        return graphStubHolder.get().removeGraph(request).getId();
    }

    public boolean isOwnerGraph(String userId, String graphId){
        var request = GraphGrpc.IsOwnerGraphRequest.newBuilder()
                .setGraphId(graphId)
                .setUserId(userId)
                .build();
        return graphStubHolder.get().isOwnerGraph(request).getResult();
    }
}
