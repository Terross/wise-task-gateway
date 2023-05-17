package ru.leti.wise.task.gateway.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import ru.leti.graphql.model.*;
import ru.leti.wise.task.gateway.mapper.GraphMapper;
import ru.leti.wise.task.gateway.service.grpc.graph.GraphGrpcService;

@Controller
@RequiredArgsConstructor
public class GraphController implements CreateGraphMutationResolver, GenerateGraphMutationResolver,
        GetGraphByIdQueryResolver {

    private final GraphMapper graphMapper;
    private final GraphGrpcService graphGrpcService;

    @Override
    @MutationMapping
    public Graph createGraph(@Argument CreateGraphRequest createGraphRequest) {
        return graphMapper.toGraph(graphGrpcService.createGraph(createGraphRequest.getGraph()));
    }

    @Override
    @MutationMapping
    public Graph generateGraph(@Argument GenerateGraphRequest generateGraphRequest) {
        return graphMapper.toGraph(graphGrpcService.generateGraph(generateGraphRequest));
    }

    @Override
    @MutationMapping
    public Graph getGraphById(@Argument String id) {
        return graphMapper.toGraph(graphGrpcService.getGraphById(id));
    }

}
