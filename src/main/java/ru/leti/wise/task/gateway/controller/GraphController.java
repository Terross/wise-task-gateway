package ru.leti.wise.task.gateway.controller;

import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import ru.leti.graphql.model.*;
import ru.leti.wise.task.gateway.mapper.GraphMapper;
import ru.leti.wise.task.gateway.service.grpc.graph.GraphGrpcService;

import java.util.List;

@Observed
@Controller
@RequiredArgsConstructor
public class GraphController implements CreateGraphMutationResolver, GenerateGraphMutationResolver,
        GetGraphByIdQueryResolver, GetGraphLibraryQueryResolver, DeleteGraphMutationResolver {

    private final GraphMapper graphMapper;
    private final GraphGrpcService graphGrpcService;

    @Override
    @PreAuthorize("hasAnyRole(\"STUDENT\",\"CAPTAIN\",\"TEACHER\",\"ADMIN\")")
    @MutationMapping
    public Graph createGraph(@Argument GraphInput graph) {
        return graphMapper.toGraph(graphGrpcService.createGraph(graph));
    }

    @Override
    @PreAuthorize("hasAnyRole(\"STUDENT\",\"CAPTAIN\",\"TEACHER\",\"ADMIN\")")
    @MutationMapping
    public Graph generateGraph(@Argument GenerateGraphRequest generateGraphRequest) {
        return graphMapper.toGraph(graphGrpcService.generateGraph(generateGraphRequest));
    }

    @Override
    @PreAuthorize("hasAnyRole(\"STUDENT\",\"CAPTAIN\",\"TEACHER\",\"ADMIN\")")
    @QueryMapping
    public Graph getGraphById(@Argument String id) {
        return graphMapper.toGraph(graphGrpcService.getGraphById(id));
    }


    @Override
    @PreAuthorize("hasAnyRole(\"STUDENT\",\"CAPTAIN\",\"TEACHER\",\"ADMIN\")")
    @QueryMapping
    public List<Graph> getGraphLibrary() {
        return graphMapper.toGraphs(graphGrpcService.getGraphLibrary());
    }

    @Override
    @PreAuthorize("hasAnyRole(\"STUDENT\",\"CAPTAIN\",\"TEACHER\",\"ADMIN\")")
    @MutationMapping
    public String deleteGraph(@Argument String id) {
        return graphGrpcService.deleteGraph(id);
    }
}
