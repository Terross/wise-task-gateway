package ru.leti.wise.task.gateway.dto.task;

public record Rule(
        boolean isColor,
        boolean isEdit,
        boolean isMove,
        boolean isDelete
) {}