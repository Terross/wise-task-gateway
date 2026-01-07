package ru.leti.wise.task.gateway.controller;

import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import ru.leti.graphql.model.GetStatisticQueryResolver;
import ru.leti.graphql.model.StatisticRequestInput;
import ru.leti.graphql.model.StatisticResponse;
import ru.leti.wise.task.gateway.mapper.StatisticMapper;
import ru.leti.wise.task.gateway.service.grpc.statistic.StatisticsGrpcService;


@Slf4j
@Observed
@Controller
@RequiredArgsConstructor
public class StatisticsController implements GetStatisticQueryResolver {
    private final StatisticsGrpcService statisticsGrpcService;
    private final StatisticMapper statisticMapper;

    @Override
    @QueryMapping
    @PreAuthorize("hasAnyRole(\"USER\",\"AUTHOR\",\"ADMIN\")")
    public StatisticResponse getStatistic(@Argument StatisticRequestInput request) throws Exception {
        return statisticMapper.toStatistic(
                statisticsGrpcService.getStatistic(
                        statisticMapper.toStatistic(request)
                )
        );
    }
}
