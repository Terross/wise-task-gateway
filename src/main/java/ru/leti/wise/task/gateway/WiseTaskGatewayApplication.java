package ru.leti.wise.task.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.grpc.client.ImportGrpcClients;
import ru.leti.wise.task.graph.GraphServiceGrpc;

@SpringBootApplication
@ConfigurationPropertiesScan
@ImportGrpcClients()
public class WiseTaskGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(WiseTaskGatewayApplication.class, args);
    }

}
