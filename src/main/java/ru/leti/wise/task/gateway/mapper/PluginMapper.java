package ru.leti.wise.task.gateway.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import ru.leti.graphql.model.*;
import ru.leti.wise.task.plugin.PluginOuterClass;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, uses = GraphMapper.class)
public interface PluginMapper {

    Plugin toPlugin(PluginOuterClass.Plugin plugin);

    PluginOuterClass.Plugin toPlugin(PluginInput plugin);

    List<PluginOuterClass.Plugin> toProtoPlugins(List<PluginInput> plugins);

    List<Plugin> toResponsePlugins(List<PluginOuterClass.Plugin> plugins);

    ImplementationResult toImplementationResult(PluginOuterClass.ImplementationResult implementationResult);

    @Mapping(target = "graph", source = "solution.payload.graph")
    @Mapping(target = "otherGraph", source = "solution.additionalPayload.otherGraph")
    @Mapping(target = "handwrittenAnswer", source = "solution.additionalPayload.handwrittenAnswer")
    PluginOuterClass.Solution toSolution(SolutionInput solution);


    default PluginOuterClass.PluginType toPluginType(PluginType pluginType) {
        return PluginOuterClass.PluginType.valueOf(pluginType.name());
    }

    default PluginType toPluginType(PluginOuterClass.PluginType pluginType) {
        return PluginType.valueOf(pluginType.name());
    }
}
