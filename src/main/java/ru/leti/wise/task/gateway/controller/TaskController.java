package ru.leti.wise.task.gateway.controller;

import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import ru.leti.graphql.model.*;
import ru.leti.wise.task.gateway.mapper.GraphMapper;
import ru.leti.wise.task.gateway.mapper.SolutionMapper;
import ru.leti.wise.task.gateway.mapper.TaskMapper;
import ru.leti.wise.task.gateway.security.user.UserCredentials;
import ru.leti.wise.task.gateway.service.TaskService;
import ru.leti.wise.task.gateway.service.grpc.graph.GraphGrpcService;
import ru.leti.wise.task.gateway.service.grpc.task.TaskGrpcService;

import java.util.List;

@Observed
@Controller
@RequiredArgsConstructor
public class TaskController implements GetTaskQueryResolver, GetAllTasksQueryResolver, GetTaskSolutionQueryResolver,
        GetAllTaskSolutionsQueryResolver, GetUserSolutionStatisticQueryResolver, DeleteTaskMutationResolver,
        CreateTaskGraphMutationResolver, CreateTaskImplementationMutationResolver, UpdateTaskGraphMutationResolver,
        UpdateTaskImplementationMutationResolver, SolveTaskGraphMutationResolver, SolveTaskImplementationMutationResolver {

    private final TaskMapper taskMapper;
    private final SolutionMapper solutionMapper;
    private final TaskGrpcService taskGrpcService;
    private final TaskService taskService;
    private final GraphGrpcService graphGrpcService;
    private final GraphMapper graphMapper;

    @Override
    @MutationMapping
    @PreAuthorize(
            "hasRole(\"AUTHOR\") and " +
            "@taskGrpcService.getTask(#id).getAuthorId().equals(authentication.principal.profile.id) or" +
            " hasRole(\"ADMIN\")")
    public String deleteTask(@Argument String id) {
        taskGrpcService.deleteTask(id);
        return id;
    }

    @Override
    @QueryMapping
    @PreAuthorize(value = "hasRole(\"AUTHOR\") and " +
            "@taskGrpcService.getTask(#taskId).getAuthorId().equals(authentication.principal.profile.id) or" +
            " hasRole(\"ADMIN\")")
    public List<Solution> getAllTaskSolutions(@Argument String userId, @Argument String taskId) {
        return solutionMapper.toSolutions(taskGrpcService.getAllTaskSolutions(taskId, userId));
    }

    @Override
    @QueryMapping
    @PreAuthorize("hasAnyRole(\"USER\", \"AUTHOR\",\"ADMIN\")")
    public List<Task> getAllTasks() {
        return taskMapper.toTasks(taskGrpcService.getAllTasks());
    }

    @Override
    @QueryMapping
    @PreAuthorize("hasAnyRole(\"USER\", \"AUTHOR\",\"ADMIN\")")
    public Task getTask(@Argument String id) {
        var taskResponse = taskGrpcService.getTask(id);
        var taskGraph = taskMapper.toTaskGraph(taskResponse);
        if (taskGraph.getGraph().getId() != null) {
            taskGraph.setGraph(graphMapper.toGraph(graphGrpcService.getGraphById(taskGraph.getGraph().getId())));
            return taskGraph;
        }
        return taskMapper.toTaskImplementation(taskResponse);
    }

    @Override
    @QueryMapping
    @PreAuthorize(value = "hasRole(\"USER\") and " +
            "@taskGrpcService.getTaskSolution(#id).getAuthorId().equals(authentication.principal.id)" +
            " or hasRole(\"AUTHOR\") and " +
            "@taskGrpcService.getTask(taskGrpcService.getTaskSolution(#id).getTaskId())" +
            ".getAuthorId().equals(authentication.principal.id) or" +
            " hasRole(\"ADMIN\")")
    public Solution getTaskSolution(@Argument String id) {
        var solution = taskGrpcService.getTaskSolution(id);
        if (solution.hasSolutionGraph()) {
            return taskService.buildSolutionWithGraph(solution);
        }
        return solutionMapper.toSolution(solution);
    }

    @Override
    @QueryMapping
    @PreAuthorize("hasAnyRole(\"AUTHOR\",\"ADMIN\")")
    public List<Solution> getUserSolutionStatistic(@Argument String userId) {
        return solutionMapper.toSolutions(taskGrpcService.getUserSolutionStatistic(userId));
    }

    @Override
    @MutationMapping
    @PreAuthorize("hasAnyRole(\"AUTHOR\",\"ADMIN\")")
    public TaskGraph createTaskGraph(@Argument TaskGraphInput task) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userId = ((UserCredentials) auth.getPrincipal()).getId();
        return taskMapper.toTaskGraph(taskGrpcService.createTask(taskMapper.toTaskGraph(task, userId)));
    }

    @Override
    @MutationMapping
    @PreAuthorize("hasAnyRole(\"AUTHOR\",\"ADMIN\")")
    public TaskImplementation createTaskImplementation(@Argument TaskImplementationInput task) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userId = ((UserCredentials) auth.getPrincipal()).getId();
        return taskMapper.toTaskImplementation(taskGrpcService.createTask(taskMapper.toTaskImplementation(task, userId)));
    }

    @Override
    @MutationMapping
    @PreAuthorize("hasRole(\"AUTHOR\") " +
            "and @taskGrpcService.getTask(#task.getId()).getAuthorId().equals(authentication.principal.id) " +
            "or hasRole(\"ADMIN\")")
    public TaskGraph updateTaskGraph(@Argument TaskGraphInput task) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userId = ((UserCredentials) auth.getPrincipal()).getId();
        return taskMapper.toTaskGraph(
                taskGrpcService.updateTask(
                        taskMapper.toTaskGraph(task, userId)));
    }

    @Override
    @MutationMapping
    @PreAuthorize("hasRole(\"AUTHOR\") " +
            "and @taskGrpcService.getTask(#task.getId()).getAuthorId().equals(authentication.principal.id) " +
            "or hasRole(\"ADMIN\")")
    public TaskImplementation updateTaskImplementation(@Argument TaskImplementationInput task) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userId = ((UserCredentials) auth.getPrincipal()).getId();
        return taskMapper.toTaskImplementation(taskGrpcService.updateTask(taskMapper.toTaskImplementation(task, userId)));
    }

    @Override
    @MutationMapping
    @PreAuthorize("hasAnyRole(\"USER\", \"AUTHOR\",\"ADMIN\")")
    public SolutionGraph solveTaskGraph(@Argument SolutionGraphInput solution) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userId = ((UserCredentials) auth.getPrincipal()).getId();
        return solutionMapper.toSolutionGraph(taskGrpcService.solveTask(solutionMapper.toSolutionGraph(solution, userId)));
    }

    @Override
    @MutationMapping
    @PreAuthorize("hasAnyRole(\"USER\", \"AUTHOR\",\"ADMIN\")")
    public SolutionImplementation solveTaskImplementation(@Argument SolutionImplementationInput solution) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userId = ((UserCredentials) auth.getPrincipal()).getId();
        return solutionMapper.toSolutionImplementation(taskGrpcService.solveTask(solutionMapper.toSolutionImplementation(solution, userId)));
    }
}
