package ru.leti.wise.task.gateway.controller;

import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import ru.leti.graphql.model.*;
import ru.leti.wise.task.gateway.mapper.SolutionMapper;
import ru.leti.wise.task.gateway.mapper.TaskMapper;
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


    @Override
    @QueryMapping
    @PreAuthorize("isAnonymous()")
    public String deleteTask(@Argument String id) {
        taskGrpcService.deleteTask(id);
        return id;
    }

    @Override
    @QueryMapping
    @PreAuthorize("isAnonymous()")
    public List<Solution> getAllTaskSolutions(@Argument String userId, @Argument String taskId) {
        return solutionMapper.toSolutions(taskGrpcService.getAllTaskSolutions(taskId, userId));
    }

    @Override
    @QueryMapping
    @PreAuthorize("isAnonymous()")
    public List<Task> getAllTasks() {
        return taskMapper.toTasks(taskGrpcService.getAllTasks());
    }

    @Override
    @QueryMapping
    @PreAuthorize("isAnonymous()")
    public Task getTask(@Argument String id) {
        return taskMapper.toTask(taskGrpcService.getTask(id));
    }

    @Override
    @QueryMapping
    @PreAuthorize("isAnonymous()")
    public Solution getTaskSolution(@Argument String id) {
        return solutionMapper.toSolution(taskGrpcService.getTaskSolution(id));
    }

    @Override
    @QueryMapping
    @PreAuthorize("isAnonymous()")
    public List<Solution> getUserSolutionStatistic(@Argument String userId) {
        return solutionMapper.toSolutions(taskGrpcService.getUserSolutionStatistic(userId));
    }

    @Override
    @MutationMapping
    @PreAuthorize("isAnonymous()")
    public TaskGraph createTaskGraph(@Argument TaskGraphInput task) {
        return taskMapper.toTaskGraph(taskGrpcService.createTask(taskMapper.toTaskGraph(task)));
    }

    @Override
    @MutationMapping
    @PreAuthorize("isAnonymous()")
    public TaskImplementation createTaskImplementation(@Argument TaskImplementationInput task) {
        return taskMapper.toTaskImplementation(taskGrpcService.createTask(taskMapper.toTaskImplementation(task)));
    }

    @Override
    @MutationMapping
    @PreAuthorize("isAnonymous()")
    public TaskGraph updateTaskGraph(@Argument TaskGraphInput task) {
        return taskMapper.toTaskGraph(taskGrpcService.updateTask(taskMapper.toTaskGraph(task)));
    }

    @Override
    @MutationMapping
    @PreAuthorize("isAnonymous()")
    public TaskImplementation updateTaskImplementation(@Argument TaskImplementationInput task) {
        return taskMapper.toTaskImplementation(taskGrpcService.updateTask(taskMapper.toTaskImplementation(task)));
    }

    @Override
    @MutationMapping
    @PreAuthorize("isAnonymous()")
    public SolutionGraph solveTaskGraph(@Argument SolutionGraphInput solution) {
        return solutionMapper.toSolutionGraph(taskGrpcService.solveTask(solutionMapper.toSolutionGraph(solution)));
    }

    @Override
    @MutationMapping
    @PreAuthorize("isAnonymous()")
    public SolutionImplementation solveTaskImplementation(@Argument SolutionImplementationInput solution) {
        return solutionMapper.toSolutionImplementation(taskGrpcService.solveTask(solutionMapper.toSolutionImplementation(solution)));
    }
}
