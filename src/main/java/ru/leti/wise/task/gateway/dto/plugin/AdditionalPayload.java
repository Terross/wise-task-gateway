package ru.leti.wise.task.gateway.dto.plugin;

import ru.leti.wise.task.gateway.dto.graph.GraphInput;

public record AdditionalPayload(
        AdditionPayloadType discriminator,
        GraphInput otherGraph,
        String handwrittenAnswer
) {}