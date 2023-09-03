package ru.leti.wise.task.gateway.controller;

import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import ru.leti.graphql.model.*;
import ru.leti.wise.task.gateway.mapper.TaskMapper;
import ru.leti.wise.task.gateway.service.grpc.task.TaskGrpcService;

import java.util.List;

@Observed
@Controller
@RequiredArgsConstructor
public class TaskController implements GetTaskQueryResolver, GetAllTasksQueryResolver, GetTaskSolutionQueryResolver,
        GetAllTaskSolutionsQueryResolver, GetUserSolutionStatisticQueryResolver, DeleteTaskMutationResolver,
        CreateTaskMutationResolver, UpdateTaskMutationResolver, SolveTaskMutationResolver {

    private final TaskMapper taskMapper;
    private final TaskGrpcService taskGrpcService;

    @Override
    public Task createTask(TaskInput task) {
        return taskMapper.toTask(taskGrpcService.createTask(taskMapper.toTask(task)));
    }

    @Override
    public String deleteTask(String id) {
        taskGrpcService.deleteTask(id);
        return id;
    }

    @Override
    public List<TaskSolution> getAllTaskSolutions(String userId, String taskId) {
        return taskMapper.toSolutions(taskGrpcService.getAllTaskSolutions(taskId, userId));
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
    public TaskSolution getTaskSolution(String id) {
        return taskMapper.toSolution(taskGrpcService.getTaskSolution(id));
    }

    @Override
    public List<TaskSolution> getUserSolutionStatistic(String userId) {
        return taskMapper.toSolutions(taskGrpcService.getUserSolutionStatistic(userId));
    }

    @Override
    public TaskSolution solveTask(TaskSolutionInput solution) {
        return taskMapper.toSolution(taskGrpcService.solveTask(taskMapper.toSolution(solution)));
    }

    @Override
    public Task updateTask(TaskInput task) {
        return taskMapper.toTask(taskGrpcService.updateTask(taskMapper.toTask(task)));
    }
}
