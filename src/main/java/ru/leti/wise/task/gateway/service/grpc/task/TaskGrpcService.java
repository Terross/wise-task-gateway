package ru.leti.wise.task.gateway.service.grpc.task;

import com.google.protobuf.Empty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.leti.wise.task.task.TaskGrpc;
import ru.leti.wise.task.task.TaskOuterClass.SolutionRequest;
import ru.leti.wise.task.task.TaskOuterClass.SolutionResponse;
import ru.leti.wise.task.task.TaskOuterClass.Task;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TaskGrpcService {

    private final TaskStubHolder taskStubHolder;

    public Task getTask(String id) {
        var request = TaskGrpc.GetTaskRequest.newBuilder()
                .setId(id)
                .build();
        return taskStubHolder.get().getTask(request).getTask();
    }

    public List<Task> getAllTasks() {
        var request = Empty.newBuilder().build();

        return taskStubHolder.get().getAllTask(request).getTaskList();
    }

    public void deleteTask(String id) {
        var request = TaskGrpc.DeleteTaskRequest.newBuilder()
                .setId(id)
                .build();

        taskStubHolder.get().deleteTask(request);
    }

    public Task createTask(Task task) {
        var request = TaskGrpc.CreateTaskRequest.newBuilder()
                .setTask(task)
                .build();

        return taskStubHolder.get().createTask(request).getTask();
    }

    public Task updateTask(Task task) {
        var request = TaskGrpc.UpdateTaskRequest.newBuilder()
                .setTask(task)
                .build();

        return taskStubHolder.get().updateTask(request).getTask();
    }

    public SolutionResponse solveTask(SolutionRequest solutionRequest) {
        var request = TaskGrpc.SolveTaskRequest.newBuilder()
                .setSolution(solutionRequest)
                .build();

        return taskStubHolder.get().solveTask(request).getSolution();
    }

    public SolutionResponse getTaskSolution(String id) {
        var request = TaskGrpc.GetTaskSolutionRequest.newBuilder()
                .setId(id)
                .build();

        return taskStubHolder.get().getTaskSolution(request).getSolution();
    }

    public List<SolutionResponse> getAllTaskSolutions(String taskId, String authorId) {
        var request = TaskGrpc.GetAllTaskSolutionsRequest.newBuilder()
                .setTaskId(taskId)
                .setAuthorId(authorId)
                .build();

        return taskStubHolder.get().getAllTaskSolutions(request).getSolutionList();
    }

    public List<SolutionResponse> getUserSolutionStatistic(String authorId) {
        var request = TaskGrpc.GetUserSolutionStatisticRequest.newBuilder()
                .setAuthorId(authorId)
                .build();

        return taskStubHolder.get().getUserSolutionStatistic(request).getSolutionList();
    }
}
