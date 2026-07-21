package ru.leti.wise.task.gateway.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.grpc.client.ImportGrpcClients;
import ru.leti.wise.task.graph.GraphServiceGrpc;
import ru.leti.wise.task.plugin.PluginServiceGrpc;
import ru.leti.wise.task.profile.ProfileServiceGrpc;
import ru.leti.wise.task.task.TaskServiceGrpc;
import ru.leti.wise.task.event.StatisticsServiceGrpc;

@Configuration
@ImportGrpcClients(
        target = "graph-service",
        types = GraphServiceGrpc.GraphServiceBlockingStub.class
)
@ImportGrpcClients(
        target = "plugin-service",
        types = PluginServiceGrpc.PluginServiceBlockingStub.class
)
@ImportGrpcClients(
        target = "profile-service",
        types = ProfileServiceGrpc.ProfileServiceBlockingStub.class
)
@ImportGrpcClients(
        target = "task-service",
        types = TaskServiceGrpc.TaskServiceBlockingStub.class
)
@ImportGrpcClients(
        target = "statistics-service",
        types = StatisticsServiceGrpc.StatisticsServiceBlockingStub.class
)
public class GrpcConfiguration {
}
