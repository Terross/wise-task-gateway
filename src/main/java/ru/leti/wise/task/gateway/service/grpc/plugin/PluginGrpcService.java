package ru.leti.wise.task.gateway.service.grpc.plugin;

import com.google.protobuf.Empty;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.leti.graphql.model.*;
import ru.leti.wise.task.gateway.mapper.GraphMapper;
import ru.leti.wise.task.gateway.mapper.PluginMapper;
import ru.leti.wise.task.graph.GraphGrpc;
import ru.leti.wise.task.graph.GraphOuterClass.Graph;
import ru.leti.wise.task.plugin.PluginGrpc;
import ru.leti.wise.task.plugin.PluginOuterClass;

import java.util.List;


@Component
@Observed
@RequiredArgsConstructor
public class PluginGrpcService {

    private final PluginStubHolder pluginStubHolder;
    private final GraphMapper graphMapper;
    private final PluginMapper pluginMapper;

    public PluginOuterClass.ImplementationResult checkPluginImplementation(String id, String file) {
        var request = PluginGrpc.CheckPluginImplementationRequest.newBuilder()
                .setId(id)
                .setFile(file)
                .build();

        return pluginStubHolder.get().checkPluginImplementation(request).getImplementationResult();
    }

    public String checkPluginSolution(SolutionInput solution) {
        var request = PluginGrpc.CheckPluginSolutionRequest.newBuilder()
                .setSolution(pluginMapper.toSolution(solution))
                .build();

        return pluginStubHolder.get().checkPluginSolution(request).getResult();
    }


    public PluginOuterClass.Plugin createPlugin(PluginInput plugin) {
        var request = PluginGrpc.CreatePluginRequest.newBuilder()
                .setPlugin(pluginMapper.toPlugin(plugin))
                .build();

        return pluginStubHolder.get().createPlugin(request).getPlugin();
    }

    public String deletePlugin(String id) {
        var request = PluginGrpc.DeletePluginRequest.newBuilder()
                .setId(id)
                .build();

        return pluginStubHolder.get().deletePlugin(request).getId();
    }

    public List<PluginOuterClass.Plugin> getAllPlugins() {
        return pluginStubHolder.get().getAllPlugins(Empty.newBuilder().build()).getPluginList();
    }

    public PluginOuterClass.Plugin getPlugin(String id) {
        var request = PluginGrpc.GetPluginRequest.newBuilder()
                .setId(id)
                .build();

        return pluginStubHolder.get().getPlugin(request).getPlugin();
    }

    public PluginOuterClass.Plugin updatePlugin(PluginInput plugin) {
        var request = PluginGrpc.UpdatePluginRequest.newBuilder()
                .setPlugin(pluginMapper.toPlugin(plugin))
                .build();

        return pluginStubHolder.get().updatePlugin(request).getPlugin();
    }

    public String validatePlugin(String id) {
        var request = PluginGrpc.ValidatePluginRequest.newBuilder()
                .setId(id)
                .build();

        return pluginStubHolder.get().validatePlugin(request).getId();
    }
}
