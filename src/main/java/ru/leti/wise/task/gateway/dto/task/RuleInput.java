package ru.leti.wise.task.gateway.dto.task;

public record RuleInput(
        boolean isColor,
        boolean isEdit,
        boolean isMove,
        boolean isDelete
) {}