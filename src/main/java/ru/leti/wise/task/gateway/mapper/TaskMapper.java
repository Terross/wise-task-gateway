package ru.leti.wise.task.gateway.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import ru.leti.graphql.model.*;
import ru.leti.wise.task.task.TaskOuterClass;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {GraphMapper.class, PluginMapper.class})
public interface TaskMapper {

    Task toTask(TaskOuterClass.Task task);
    List<Task> toTasks(List<TaskOuterClass.Task> tasks);
    TaskOuterClass.Task toTask(TaskInput task);
    TaskSolution toSolution(TaskOuterClass.SolutionResponse solution);
    List<TaskSolution> toSolutions(List<TaskOuterClass.SolutionResponse> solution);
    TaskOuterClass.SolutionRequest toSolution(TaskSolutionInput solution);

    default TaskOuterClass.Category toCategory(Category category) {
        return TaskOuterClass.Category.valueOf(category.name());
    }

    default Category toCategory(TaskOuterClass.Category category) {
        return Category.valueOf(category.name());
    }
}
