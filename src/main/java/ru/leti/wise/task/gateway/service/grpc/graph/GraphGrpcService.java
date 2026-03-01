package ru.leti.wise.task.gateway.service.grpc.graph;

import com.google.protobuf.Empty;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.leti.graphql.model.GenerateGraphRequest;
import ru.leti.graphql.model.GraphInput;
import ru.leti.wise.task.gateway.mapper.GraphMapper;
import ru.leti.wise.task.graph.GraphGrpc;
import ru.leti.wise.task.graph.GraphOuterClass.Graph;

import java.util.List;

@Slf4j
@Component
@Observed
@RequiredArgsConstructor
public class GraphGrpcService {

    private final GraphStubHolder graphStubHolder;
    private final GraphMapper graphMapper;

    public Graph createGraph(GraphInput graph, String userId) {
        Graph baseGraph = graphMapper.toGraph(graph, userId);
        log.info("Mapped GraphQL input: isNamed={}, name={}", baseGraph.getIsNamed(), baseGraph.getName());

        GraphGrpc.CreateGraphRequest request = GraphGrpc.CreateGraphRequest.newBuilder()
                .setGraph(baseGraph)
                .build();

        log.info("Sending gRPC request: isNamed={}, name={}",
                request.getGraph().getIsNamed(),
                request.getGraph().getName());

        Graph result = graphStubHolder.get()
                .createGraph(reactor.core.publisher.Mono.just(request))
                .block()
                .getGraph();

        log.info("Received gRPC response: isNamed={}, name={}",
                result.getIsNamed(), result.getName());

        return result;
    }

    public Graph generateGraph(GenerateGraphRequest generateGraphRequest) {
        var request = graphMapper.toGenerateGraphRequest(generateGraphRequest);

        return graphStubHolder.get()
                .generateRandomGraph(reactor.core.publisher.Mono.just(request))
                .block()
                .getGraph();
    }

    public Graph getGraphById(String id) {
        var request = GraphGrpc.GetGraphByIdRequest.newBuilder()
                .setId(id)
                .build();

        return graphStubHolder.get()
                .getGraphById(reactor.core.publisher.Mono.just(request))
                .block()
                .getGraph();
    }

    public List<Graph> getGraphLibrary() {
        return graphStubHolder.get()
                .getGraphLibrary(reactor.core.publisher.Mono.just(Empty.newBuilder().build()))
                .block()
                .getGraphListList();
    }

    public String deleteGraph(String id) {
        var request = GraphGrpc.RemoveGraphRequest.newBuilder()
                .setId(id)
                .build();

        return graphStubHolder.get()
                .removeGraph(reactor.core.publisher.Mono.just(request))
                .block()
                .getId();
    }

    public boolean isOwnerGraph(String userId, String graphId) {
        var request = GraphGrpc.IsOwnerGraphRequest.newBuilder()
                .setGraphId(graphId)
                .setUserId(userId)
                .build();

        return graphStubHolder.get()
                .isOwnerGraph(reactor.core.publisher.Mono.just(request))
                .block()
                .getResult();
    }
}