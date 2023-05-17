package ru.leti.wise.task.gateway.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import ru.leti.graphql.model.Plugin;
import ru.leti.graphql.model.PluginInput;
import ru.leti.graphql.model.PluginType;
import ru.leti.graphql.model.SolutionInput;
import ru.leti.wise.task.plugin.PluginOuterClass;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PluginMapper {

    Plugin toPlugin(PluginOuterClass.Plugin plugin);

    PluginOuterClass.Plugin toPlugin(PluginInput plugin);

    List<PluginOuterClass.Plugin> toProtoPlugins(List<PluginInput> plugins);
    List<Plugin> toResponsePlugins(List<PluginOuterClass.Plugin> plugins);

    PluginOuterClass.Solution toSolution(SolutionInput solution);

    default PluginOuterClass.PluginType toPluginType(PluginType pluginType) {
        return PluginOuterClass.PluginType.valueOf(pluginType.name());
    }

    default PluginType toPluginType(PluginOuterClass.PluginType pluginType) {
        return PluginType.valueOf(pluginType.name());
    }
}
