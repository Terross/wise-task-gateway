package ru.leti.wise.task.gateway.controller;

import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import ru.leti.graphql.model.*;
import ru.leti.wise.task.gateway.mapper.PluginMapper;
import ru.leti.wise.task.gateway.service.grpc.plugin.PluginGrpcService;

import java.util.List;

@Observed
@Controller
@RequiredArgsConstructor
public class PluginController implements GetAllPluginsQueryResolver, GetPluginQueryResolver,
        CreatePluginMutationResolver, UpdatePluginMutationResolver, DeletePluginMutationResolver,
        CheckPluginSolutionMutationResolver, CheckPluginImplementationMutationResolver, ValidatePluginMutationResolver {

    private final PluginGrpcService pluginGrpcService;
    private final PluginMapper pluginMapper;

    @Override
    @PreAuthorize("pluginGrpcService.isOwnerPlugin(authentication.principal.profile.id,#id) or hasRole(\"ROLE_ADMIN\")")
    @MutationMapping
    public ImplementationResult checkPluginImplementation(@Argument String id, @Argument String file) {
        return pluginMapper.toImplementationResult(pluginGrpcService.checkPluginImplementation(id, file));
    }

    @Override
    @PreAuthorize("pluginGrpcService.isOwnerPlugin(authentication.principal.profile.id,#solution.getPluginId()) or hasRole(\"ROLE_ADMIN\")")
    @MutationMapping
    public String checkPluginSolution(@Argument SolutionInput solution) {
        return pluginGrpcService.checkPluginSolution(solution);
    }

    @Override
    @PreAuthorize("hasAnyRole(\"ROLE_AUTHOR\",\"ROLE_ADMIN\")")
    @MutationMapping
    public Plugin createPlugin(@Argument PluginInput plugin) {
        return pluginMapper.toPlugin(pluginGrpcService.createPlugin(plugin));
    }

    @Override
    @PreAuthorize("(hasRole(\"ROLE_AUTHOR\") and pluginGrpcService.isOwnerPlugin(authentication.principal.profile.id,#id)) or hasRole(\"ADMIN\")")
    @MutationMapping
    public String deletePlugin(@Argument String id) {
        return pluginGrpcService.deletePlugin(id);
    }

    @Override
    @PreAuthorize("hasAnyRole(\"ROLE_AUTHOR\",\"ROLE_ADMIN\")")
    @QueryMapping
    public List<Plugin> getAllPlugins() {
        return pluginMapper.toResponsePlugins(pluginGrpcService.getAllPlugins());
    }

    @Override
    @PreAuthorize("hasAnyRole(\"ROLE_AUTHOR\",\"ROLE_ADMIN\")")
    @QueryMapping
    public Plugin getPlugin(@Argument String id) {
        return pluginMapper.toPlugin(pluginGrpcService.getPlugin(id));
    }

    @Override
    @PreAuthorize(
            "(hasRole(\"ROLE_AUTHOR\") and pluginGrpcService.isOwnerPlugin(authentication.principal.profile.id,#plugin.getId()))" +
            " or hasRole(\"ROLE_ADMIN\")"
    )
    @MutationMapping
    public Plugin updatePlugin(@Argument PluginInput plugin) {
        return pluginMapper.toPlugin(pluginGrpcService.updatePlugin(plugin));
    }

    @Override
    @PreAuthorize(
            "(hasRole(\"ROLE_AUTHOR\") and pluginGrpcService.isOwnerPlugin(authentication.principal.profile.id,#id))" +
                    " or hasRole(\"ROLE_ADMIN\")")
    @MutationMapping
    public String validatePlugin(@Argument String id) {
        return pluginGrpcService.validatePlugin(id);
    }
}
