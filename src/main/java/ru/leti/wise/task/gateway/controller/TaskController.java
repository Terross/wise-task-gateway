package ru.leti.wise.task.gateway.controller;

import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
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
    public List<Solution> getAllTaskSolutions(String userId, String taskId) {
        return solutionMapper.toSolutions(taskGrpcService.getAllTaskSolutions(taskId, userId));
    }

    @Override
    public List<Task> getAllTasks() {
        return taskMapper.toTasks(taskGrpcService.getAllTasks());
    }

    @Override
    public Task getTask(String id) {
        return taskMapper.toTask(taskGrpcService.getTask(id));
    }

    @Override
    public Solution getTaskSolution(String id) {
        return solutionMapper.toSolution(taskGrpcService.getTaskSolution(id));
    }

    @Override
    public List<Solution> getUserSolutionStatistic(String userId) {
        return solutionMapper.toSolutions(taskGrpcService.getUserSolutionStatistic(userId));
    }

    @Override
    public TaskGraph createTaskGraph(TaskGraphInput task) {
        return taskMapper.toTaskGraph(taskGrpcService.createTask(taskMapper.toTaskGraph(task)));
    }

    @Override
    public TaskImplementation createTaskImplementation(TaskImplementationInput task) {
        return taskMapper.toTaskImplementation(taskGrpcService.createTask(taskMapper.toTaskImplementation(task)));
    }

    @Override
    public TaskGraph updateTaskGraph(TaskGraphInput task) {
        return taskMapper.toTaskGraph(taskGrpcService.updateTask(taskMapper.toTaskGraph(task)));
    }

    @Override
    public TaskImplementation updateTaskImplementation(TaskImplementationInput task) {
        return taskMapper.toTaskImplementation(taskGrpcService.updateTask(taskMapper.toTaskImplementation(task)));
    }

    @Override
    public SolutionGraph solveTaskGraph(SolutionGraphInput solution) {
        return solutionMapper.toSolutionGraph(taskGrpcService.solveTask(solutionMapper.toSolutionGraph(solution)));
    }

    @Override
    public SolutionImplementation solveTaskImplementation(SolutionImplementationInput solution) {
        return solutionMapper.toSolutionImplementation(taskGrpcService.solveTask(solutionMapper.toSolutionImplementation(solution)));
    }
}
