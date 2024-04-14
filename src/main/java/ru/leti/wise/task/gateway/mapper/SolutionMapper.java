package ru.leti.wise.task.gateway.mapper;

import org.mapstruct.*;
import ru.leti.graphql.model.*;
import ru.leti.wise.task.task.TaskOuterClass;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {GraphMapper.class, PluginMapper.class})
public interface SolutionMapper {

    @Named("toSolution")
    default Solution toSolution(TaskOuterClass.Solution solution) {
        if (solution.hasSolutionGraph()) {
            return toSolutionGraph(solution);
        } else if (solution.hasSolutionImplementation()) {
            return toSolutionImplementation(solution);
        }
        throw new RuntimeException("123"); //TODO: поправить ошибки, которые никогда не возникнут
    }

    @IterableMapping(qualifiedByName = "toSolution")
    List<Solution> toSolutions(List<TaskOuterClass.Solution> solution);

    @Mapping(target = "solutionGraph.graph", source = "solution.graph")
    @Mapping(target = "isCorrect", ignore = true)
    @Mapping(target = "solutionImplementation", ignore = true)
    TaskOuterClass.Solution toSolutionGraph(SolutionGraphInput solution);

    @Mapping(target = "solutionImplementation.code", source = "solution.code")
    @Mapping(target = "isCorrect", ignore = true)
    @Mapping(target = "solutionGraph", ignore = true)
    TaskOuterClass.Solution toSolutionImplementation(SolutionImplementationInput solution);

    @Mapping(target = ".", source = "solution.solutionGraph")
    SolutionGraph toSolutionGraph(TaskOuterClass.Solution solution);

    @Mapping(target = ".", source = "solution.solutionImplementation")
    SolutionImplementation toSolutionImplementation(TaskOuterClass.Solution solution);

}
